package org.bmj.userinsights.upload.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.awsbucket.dto.AWSFileDetail;
import org.bmj.userinsights.awsbucket.service.IAwsBucketService;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.FileUtility;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.dto.InsightAttachmentDto;
import org.bmj.userinsights.entity.InsightAttachment;
import org.bmj.userinsights.insight.dto.AttachmentDto;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.service.IInsightService;
import org.bmj.userinsights.server.BMJSessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * This class handle all activities related to upload/delete/replace attachments.
 * @author nilesh.kambli
 *
 */
@SessionAttributes( value = {"mInsightDTO"})
@Controller
public class FileUploadController {
	private static final  Logger log = Logger.getLogger(FileUploadController.class);
	@Autowired
    ServletContext context; 
	
	@Autowired
	private IInsightService insightService;

	@Autowired
	private IAwsBucketService awsBucketService;
	
	ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	
	
	
	/**
	 * Replace duplicate file name attachment with latest uploaded version.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/replaceAttachment", method = RequestMethod.GET)
	public @ResponseBody AttachmentDto replaceAttachment(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("mInsightDTO") InsightDto insightDTO) throws Exception {
		AttachmentDto attachmentDTO = new AttachmentDto();
		String insightId = request.getParameter("uid");
		// Maintain a list to send back the files info. to the client side
		List<InsightAttachmentDto> uploadedFiles = new ArrayList<InsightAttachmentDto>();
		// Getting uploaded files from the request object
		HttpSession session = request.getSession();
		InsightAttachmentDto oldInsightAttachmentDTO = new InsightAttachmentDto();// Old version of file with same name
		InsightAttachmentDto newInsightAttachmentDTO = new InsightAttachmentDto();// New version of file file with same name.
		try {
			uploadedFiles = processDuplicateFileName(attachmentDTO, insightId,
					uploadedFiles, session, oldInsightAttachmentDTO,
					newInsightAttachmentDTO);
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
		} catch (Exception e) {
			uploadedFiles = setOldAttachments(insightId, uploadedFiles, session);
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO.setErrMessage(messagesProperties
					.getString("error.message.update.file"));
			CommonUtils.errorLoggging(log, e,
					messagesProperties.getString("error_update"));
			return attachmentDTO;
		}

		return attachmentDTO;

	}

	/**
	 * Do process like add,delete operation of file references 
	 *  at server location,AWS bucket location and database.
	 *  It returns latest status of attached files for the insight.
	 * @param attachmentDTO
	 * @param insightId
	 * @param uploadedFiles
	 * @param session
	 * @param oldInsightAttachmentDTO
	 * @param newInsightAttachmentDTO
	 * @return
	 * @throws Exception
	 */
	private List<InsightAttachmentDto> processDuplicateFileName(
			AttachmentDto attachmentDTO, String insightId,
			List<InsightAttachmentDto> uploadedFiles, HttpSession session,
			InsightAttachmentDto oldInsightAttachmentDTO,
			InsightAttachmentDto newInsightAttachmentDTO) throws Exception {
		if (session.getAttribute("FIleToBeReplace") != null) {
			oldInsightAttachmentDTO = (InsightAttachmentDto) session
					.getAttribute("FIleToBeReplace");
		}
		
		if (session.getAttribute("newReplacedFIle") != null) {
			newInsightAttachmentDTO = (InsightAttachmentDto) session
					.getAttribute("newReplacedFIle");
		}
		
		if(insightId!=null && insightId.equals("0")){// Replace file in New Insight
			// Delete references of old file from session 
			
			//Delete references of old file from Temp location 
			
			uploadedFiles = deleteAttachment(uploadedFiles, insightId,
					oldInsightAttachmentDTO.getId()+"", oldInsightAttachmentDTO.getFileId(), session);
			uploadedFiles.add(newInsightAttachmentDTO);// Add new Version of File.
			
		}else{//Replace file in Old Insight. 
			//Delete old reference from database.
			insightService.deleteAttachment(oldInsightAttachmentDTO.getId()+"");
			//Delete old reference from database.
			awsBucketService.deleteFileFromBucket(insightId,oldInsightAttachmentDTO.getFileId());
			
			insightService.saveAttachment(newInsightAttachmentDTO);// Save
			if (!insightId.equals("0")) {
				awsBucketService.uploadFileToBucket(insightId, newInsightAttachmentDTO.getFileId(), newInsightAttachmentDTO.getFileNameWihPath());
			}		
			uploadedFiles = insightService.getAttachmentList(insightId);
		}
		attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
		return uploadedFiles;
	}


	/**
	 * This method gets call when user does drag & drop file on UI.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody AttachmentDto upload(MultipartHttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("mInsightDTO") InsightDto insightDTO) throws Exception {
		AttachmentDto attachmentDTO = new AttachmentDto();
		List<InsightAttachmentDto> lstAttchNewInsight = new ArrayList<InsightAttachmentDto>();
		String insightId = request.getParameter("uid");
		// Maintain a list to send back the files info. to the client side
		List<InsightAttachmentDto> uploadedFiles = new ArrayList<InsightAttachmentDto>();
		// Getting uploaded files from the request object
		Map<String, MultipartFile> fileMap = request.getFileMap();
		HttpSession session = request.getSession();
		String bucketName="";

		try {
			bucketName=awsBucketService.getAWSBucketConfig().getBucketName();
			String baseFolderPath = getBaseFolderPath();
			Integer userId=getUserId(request);
			// Iterate through the map
			for (MultipartFile multipartFile : fileMap.values()) {
				String fileId = (new StringBuffer(
						InsightsConstants.BMJ_APP_NAME).append(CommonUtils
						.getCurrentDateString()).toString());

				if (validate(multipartFile, attachmentDTO,insightDTO,session,insightId, baseFolderPath, multipartFile, fileId,bucketName,userId)) {

					
					InsightAttachmentDto insightAttachmentDTO = doFileOperations(
							userId, insightId, baseFolderPath, multipartFile,
							fileId,bucketName);
					
					if (insightId.equals("0")) {// New Insight

						if (session.getAttribute("newUploadList") != null) {// User
																			// is
																			// uploading
																			// more
																			// than
																			// one
																			// time
																			// before
																			// click
																			// on
																			// save.
							List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session
									.getAttribute("newUploadList");
							lstSessAttchNewInsight.add(insightAttachmentDTO);
							session.setAttribute("newUploadList",
									lstSessAttchNewInsight);
							uploadedFiles = lstSessAttchNewInsight;
						} else {// User is uploading first time before click on
								// save.
							lstAttchNewInsight.add(insightAttachmentDTO);
							session.setAttribute("newUploadList",
									lstAttchNewInsight);
							uploadedFiles = lstAttchNewInsight;
						}

					} else {// Edit Insight
						insightService.saveAttachment(insightAttachmentDTO);// Save
																			// directly
																			// in
																			// session.
						// Get list of attached file from Database again and
						// returns to UI layer.
						uploadedFiles = insightService
								.getAttachmentList(insightId);
					}

				} else {// If validation fails.
						// Set old attachments and send with error messages.
					uploadedFiles = setOldAttachments(insightId, uploadedFiles,
							session);
					attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);

				}

			}
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
			return attachmentDTO;
		} catch (Exception e) {
			uploadedFiles = setOldAttachments(insightId, uploadedFiles, session);
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO
					.setErrMessage(messagesProperties.getString("error.message.update.file"));
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_update"));
			return attachmentDTO;
		}
	}
	/**
	 * Copy uploaded file to server's temporary location.
	 * And create DTO obkect for uploaded file for future reference.
	 * Do file upload operations in AWS bucket in case of Old Assignment.
	 * @param request
	 * @param insightId
	 * @param baseFolderPath
	 * @param multipartFile
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	private InsightAttachmentDto doFileOperations(
			Integer userId, String insightId,
			String baseFolderPath, MultipartFile multipartFile, String fileId,String baseBucketPath)
			throws Exception {
		String fileNameWithPath = saveFileToTempLocation(insightId,
				baseFolderPath, multipartFile, fileId);

		// Set attachment DTO.
		InsightAttachmentDto insightAttachmentDTO = setAttachmentObj(
				insightId, baseFolderPath, multipartFile, fileId,baseBucketPath,userId);
		if (!insightId.equals("0")) {
			awsBucketService.uploadFileToBucket(insightId, fileId, fileNameWithPath);
		}
		return insightAttachmentDTO;
	}
	/**
	 * Save file to temporary location of server which will transfer to AWS Bucket.
	 * @param insightId
	 * @param baseFolderPath
	 * @param multipartFile
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	private String saveFileToTempLocation(String insightId,
			String baseFolderPath, MultipartFile multipartFile, String fileId)
			throws Exception {
		String folderPathInsight = getInsightFolderPath(baseFolderPath,	insightId);
		
		String targetFileName = new StringBuffer(fileId)
		.append(CommonUtils.getFileExtenstion(multipartFile.getOriginalFilename()))
		.toString();// Name of file with extension
		
		String fileNameWithPath = (new StringBuffer(folderPathInsight).append(
				File.separator).append(targetFileName).toString());
		
		// Save the file to local disk
		FileUtility.saveFileToLocalDisk(multipartFile, folderPathInsight,fileNameWithPath);
		return fileNameWithPath;
	}
	/**
	 * Set old attachments need to be sent for insight in case of exception or validation fail case.
	 * Validation for uploaded file. 
	 * @return
	 */
	private List<InsightAttachmentDto> setOldAttachments(String insightId,
			List<InsightAttachmentDto> uploadedFiles, HttpSession session)
			throws Exception {
		// Done changes to retain previous state of attachment list.
		if (insightId.equals("0")) {// New Insight
			if (session.getAttribute("newUploadList") != null) {// User is uploading more than one time before click on save.
				List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session
						.getAttribute("newUploadList");
				uploadedFiles = lstSessAttchNewInsight;
			} 
		}else{
			uploadedFiles = insightService.getAttachmentList(insightId);
		}
		return uploadedFiles;
	}
	/**
	 * Validation for attached file.
	 * @param multipartFile
	 * @param attachmentDTO
	 * @param insightDTO 
	 * @param userId 
	 * @param bucketName 
	 * @param fileId 
	 * @param multipartFile2 
	 * @param baseFolderPath 
	 * @param insightId 
	 * @param request 
	 * @return
	 * @throws Exception 
	 */
	private boolean validate(MultipartFile multipartFile,AttachmentDto attachmentDTO, InsightDto insightDTO, HttpSession session, String insightId, String baseFolderPath, MultipartFile multipartFile2, String fileId, String bucketName, Integer userId) throws Exception {
		
		Double fileSize=new Double(multipartFile.getSize()).doubleValue();
		if(fileSize>InsightsConstants.maxFileSizeInBytes){
			attachmentDTO.setErrMessage(messagesProperties.getString("error.message.filesize"));
			return false;
		}else if(!isValidExtension(multipartFile.getOriginalFilename())){
			attachmentDTO.setErrMessage(messagesProperties.getString("error.message.fileformat"));
			return false;
		}else if(!isValidFileNameLength(multipartFile.getOriginalFilename())){
			attachmentDTO.setErrMessage(messagesProperties.getString("error.message.fileName"));
			return false;			
		}else if(isFileNameAlreadyExist(multipartFile.getOriginalFilename(),insightDTO,session)){
			attachmentDTO.setFileAlreadyExist("true");// Flag at client side that duplicate file name exist.
			
			
			String fileNameWithPath = saveFileToTempLocation(insightId,
					baseFolderPath, multipartFile, fileId);

			// Set attachment DTO for new file which is new  version.
			InsightAttachmentDto insightAttachmentDTO = setAttachmentObj(
					insightId, baseFolderPath, multipartFile, fileId,bucketName,userId);
			
			String folderPathInsight = getInsightFolderPath(baseFolderPath,	insightId);

			// Save new version of file to location of server. 
			FileUtility.saveFileToLocalDisk(multipartFile, folderPathInsight,fileNameWithPath);
			insightAttachmentDTO.setFileNameWihPath(fileNameWithPath);// Set path of server location where file is stored.
			session.setAttribute("newReplacedFIle", insightAttachmentDTO); // Keep new version of file in session.
			return false;
		}
		return true;
	}
	/**
	 * It checks uploaded file name is already exist with this insight.
	 * @param fileName
	 * @param insightDTO
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private boolean isFileNameAlreadyExist(String fileName,InsightDto insightDTO,HttpSession session) throws IllegalAccessException, InvocationTargetException{
		List<InsightAttachment> uploadedFile = new ArrayList<InsightAttachment>();
		String insightId=insightDTO.getInsightDetailsDto().getId()+"";

		if (insightId.equals("0")) {// New Insight
			if (session.getAttribute("newUploadList") != null) {// User is uploading one or more than one attachments for New Insight.
				List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session
						.getAttribute("newUploadList");
				for(InsightAttachmentDto insightAttachmentDto : lstSessAttchNewInsight){//loop through attachment which are not saved in database yet.	
					if(insightAttachmentDto.getFileName().equalsIgnoreCase(fileName)){// If file name already exist.
						session.setAttribute("FIleToBeReplace", insightAttachmentDto);// Place the Attachment DTO for already exist file name in session.
						return true;
					}
					
				}
				
			} 
		}else{// Existing Insight
			Set<InsightAttachment> setInsightAttachment=insightDTO.getInsightDetailsDto().getAttachments();
			List<InsightAttachmentDto> uploadedFiles = new ArrayList<InsightAttachmentDto>();
			
			//uploadedFile = (List<InsightAttachment>) insightDTO.getInsightDetailsDto().getAttachments();
			for(InsightAttachmentDto insightAttachment : insightDTO.getAttachmentDTO().getLstInsightAttachmentDTO()){	// Loop through already saved Attachments.
				if(insightAttachment.getFileName().equalsIgnoreCase(fileName)){// If file name already exist.
					InsightAttachmentDto insightAttachmentDto=new InsightAttachmentDto();
					CommonUtils.copyProperties(insightAttachmentDto, insightAttachment);
					session.setAttribute("FIleToBeReplace", insightAttachmentDto);// Place the Attachment DTO for already exist file name in session.
					return true;
				}
				
			}
		}
		
		return false;
	}
	/**
	 * Check if uploaded file name is correct and valid length.
	 * @param fileName
	 * @return
	 */
	private boolean isValidFileName(String fileName){
	    String pattern= "^[a-zA-Z0-9.]*$"; 
        if(fileName.matches(pattern)){
            return true;
        }
		return false;
	}	
	
	/**
	 * Check if uploaded file name is valid length.
	 * @param fileName
	 * @return
	 */	
	private boolean isValidFileNameLength(String fileName){
    	String file=fileName.substring(0, fileName.indexOf("."));
    	if(file.length()<(InsightsConstants.MAX_FILE_NAME_LENGTH)+1){
    		 return true;
    	}
		return false;
    }
	
	/**
	 * Check if uploaded file name has valid extension.
	 * @param fileName
	 * @return
	 */
	private boolean isValidExtension(String fileName){
		for(String validExtension : InsightsConstants.getValidFileExtension()){		
			if(fileName.endsWith(validExtension)){
				return true;
			}
		}
		return false;
	}
	/**
	 * It gets call when user clicks on delete icon on attachment list.  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAttachment", method = RequestMethod.GET)
	public @ResponseBody AttachmentDto delete(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("mInsightDTO") InsightDto insightDTO) throws Exception {
		AttachmentDto attachmentDTO = new AttachmentDto();
		List<InsightAttachmentDto> uploadedFiles = new ArrayList<InsightAttachmentDto>();
		String insightId = request.getParameter("insightId");
		String attachmentId = request.getParameter("attachmentId");//
		String fileId = request.getParameter("fileId");//
		HttpSession session = request.getSession();
		try {
			uploadedFiles = deleteAttachment(uploadedFiles, insightId,
					attachmentId, fileId, session);
			attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
			return attachmentDTO;
		} catch (Exception e) {
			uploadedFiles = setOldAttachments(insightId, uploadedFiles, session);
			attachmentDTO.setLstInsightAttachmentDTO(uploadedFiles);
			attachmentDTO.setErrMessage(messagesProperties.getString("error.message.delete.file"));
			insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(uploadedFiles);
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_delete"));
			return attachmentDTO;
		}

	}
	/**
	 * It does actual deletion of file from database , AWS bucket and server location.
	 * @param uploadedFiles
	 * @param insightId
	 * @param attachmentId
	 * @param fileId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private List<InsightAttachmentDto> deleteAttachment(
			List<InsightAttachmentDto> uploadedFiles, String insightId,
			String attachmentId, String fileId, HttpSession session)
			throws Exception {
		if (insightId.equals("0")) {// New Insight

			if (session != null
					&& session.getAttribute("newUploadList") != null) {// User
																		// is
																		// uploading
																		// more
																		// than
																		// one
																		// time
																		// before
																		// click
																		// on
																		// save.
				
				
				
				String baseFolderPath = getBaseFolderPath();
				String folderPathInsight = getInsightFolderPath(baseFolderPath,	insightId);

				
				
				
				List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session
						.getAttribute("newUploadList");
				for (InsightAttachmentDto insightAttachmentDTO : lstSessAttchNewInsight) {
					if (insightAttachmentDTO.getFileId().equals(fileId)) {
						String targetFileName = new StringBuffer(insightAttachmentDTO.getFileId()).append(CommonUtils.getFileExtenstion(insightAttachmentDTO.getFileName())).toString();
						String fileNameWithPath = (new StringBuffer(folderPathInsight).append(File.separator).append(targetFileName).toString());
						FileUtility.deleteFile(fileNameWithPath);// Delete from temporary location.
						lstSessAttchNewInsight.remove(insightAttachmentDTO);
						break;
					}
				}
				session.setAttribute("newUploadList",
						lstSessAttchNewInsight);
				uploadedFiles = lstSessAttchNewInsight;
			}
		} else {// Edit Insight
			insightService.deleteAttachment(attachmentId);
			awsBucketService.deleteFileFromBucket(insightId,fileId);
			// Get list of attached file from Database again and returns to
			// UI layer.
			uploadedFiles = insightService.getAttachmentList(insightId);
			
		}
		return uploadedFiles;
	}
	
	
	
	
	/**
	 * This method gets call to download selected file when user
	 * clicks on download icon.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String idInsight = request.getParameter("insightId");
		String idAttachment = request.getParameter("id");
		String idFile = request.getParameter("fileId");
		String uploadFolder = null;
		String fileId = null;
		String fileName = null;
		String fileExt = null;
		String targetPath = null;
		Path path = null;
		byte[] data = null;
		String contentType="";
		try {
			if (idInsight!=null && idInsight.equals("0")) {// New Insight
				HttpSession session = request.getSession();
				if (session != null
						&& session.getAttribute("newUploadList") != null) {// User
																			// is
																			// uploading
																			// more
																			// than
																			// one
																			// time
																			// before
																			// click
																			// on
																			// save.
					List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session
							.getAttribute("newUploadList");
					for (InsightAttachmentDto insightAttachmentDTO : lstSessAttchNewInsight) {
						if (insightAttachmentDTO.getFileId().equals(idFile)) {
							uploadFolder = insightAttachmentDTO.getFilePath();
							fileId = insightAttachmentDTO.getFileId();
							fileName = insightAttachmentDTO.getFileName();
							fileExt = CommonUtils.getFileExtenstion(fileName);
							break;
						}
					}
					targetPath = (new StringBuffer(uploadFolder).append(File.separator)
							.append(fileId).append(fileExt).toString());
					path = Paths.get(targetPath);
					data = Files.readAllBytes(path);
					contentType=context.getMimeType(targetPath);
					
				}
			} else {// Edit Insight
				InsightAttachmentDto insightAttachmentDTO = insightService
						.getAttachment(idAttachment);
				fileId = insightAttachmentDTO.getFileId();
				fileName = insightAttachmentDTO.getFileName();
				fileExt = CommonUtils.getFileExtenstion(fileName);
				AWSFileDetail aFileDetail=awsBucketService.getFileFromAWSBucket(idInsight, fileId);
				data = aFileDetail.getDataAsByteArray();
				contentType=context.getMimeType(aFileDetail.getAttachementType());
			}
			
			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ fileName + "\"");
			FileCopyUtils.copy(data, response.getOutputStream());
		} catch (IOException e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_download"));
		}
	}
	
	/**
	 * Populate attachment DTO which need to be save with currently uploaded file details.
	 * @param insightId
	 * @param baseFolderPath
	 * @param multipartFile
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	private InsightAttachmentDto setAttachmentObj(String insightId,
			String baseFolderPath, MultipartFile multipartFile, String fileId,String baseBucketPath,Integer userId)
			throws Exception {
		InsightAttachmentDto insightAttachmentDTO = new InsightAttachmentDto();
		insightAttachmentDTO.setAddedUser(userId);
		insightAttachmentDTO.setFileName(multipartFile.getOriginalFilename());
		insightAttachmentDTO.setFileId(fileId);
		insightAttachmentDTO.setFileSize(new Double(multipartFile.getSize()));
		insightAttachmentDTO.setFilePath(getInsightFolderPath(baseFolderPath,
				insightId));
		insightAttachmentDTO.setFileExternalPath(getAWSUploadedPath(baseBucketPath,	insightId));
		insightAttachmentDTO.setFileExternalAdditionalInfo(getAWSUploadedPath(baseBucketPath,insightId));
		insightAttachmentDTO.setInsightId(new Integer(insightId));
		return insightAttachmentDTO;
	}
	/**
	 * Get Path of AWS folder which is associated with Insight (AWS Base Folder\insightid)
	 * @return
	 * @throws Exception
	 */
	private String getAWSUploadedPath(String baseBucketPath, String insightId){
		if(insightId==null || (insightId!=null && insightId.equals("0"))){//New Insight Case
			insightId=InsightsConstants.TEMP_FOLDER_NAME;
		}
		return (new StringBuffer(baseBucketPath).append("/").append(insightId).toString());
	}
	
	/**
	 * Get base folder path for file upload functionality from database.
	 * @return
	 * @throws Exception
	 */
	private String getBaseFolderPath() throws Exception {
		String baseFolder = insightService
				.getConfigValueByKey(InsightsConstants.DOC_ROOT)
				+ insightService
						.getConfigValueByKey(InsightsConstants.FILE_STORAGE_FOLDER);
		return baseFolder;
	}

	/**
	 * Get Path of folder which is associated with current Insight (base path+
	 * @param baseFolderPath
	 * @param insightId
	 * @return
	 * @throws Exception
	 */
	private String getInsightFolderPath(String baseFolderPath, String insightId)
			throws Exception {
		if(insightId==null || (insightId!=null && insightId.equals("0"))){//New Insight Case
			insightId=InsightsConstants.TEMP_FOLDER_NAME;
		}
		return (new StringBuffer(baseFolderPath).append(insightId).toString());
	}
	/**
	 * Get current user id from session token.
	 */
	private Integer getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("BMJSessionToken") != null) {
			BMJSessionToken bmjSessionToken = (BMJSessionToken) session.getAttribute("BMJSessionToken");
			return bmjSessionToken.getUserId();
		}
		return null;
	}
	
	/**
	 * This method will be called when click on Download All Attachments link in viewinsight page
	 * Generate the zip on the fly which includes all the attachments for insight
	 * @param insightDTO
	 * @param request
	 * @param response
	 * @param insightId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadAttachmentsZip", method = RequestMethod.GET)
	public void downloadAttachmentsZip(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("insightId") String insightId) throws Exception {

		log.info("starting of the downloadAttachmentsZip method in FileUploadController");
		List<InsightDto> insightDtoList = insightService
				.getInsightDetails(insightId);// to get insight details
		log.debug("insightDtoList size in downloadAttachmentsZip method "+insightDtoList.size());
		InsightDto insightObj = insightDtoList.get(0);

		if (!insightId.equals("0")) {
			awsBucketService.createAttachmentsZip(insightId, insightObj,
					response);
		}
		log.info("ending of the downloadAttachmentsZip method in FileUploadController");
	}

	
}
