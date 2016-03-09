package org.bmj.userinsights.insight.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bmj.userinsights.awsbucket.service.IAwsBucketService;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.AdminUserDto;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.FoundViaDto;
import org.bmj.userinsights.dto.GeographiesDto;
import org.bmj.userinsights.dto.InsightAttachmentDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.MainUserTypeDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.dto.ProjectDto;
import org.bmj.userinsights.dto.TagDto;
import org.bmj.userinsights.entity.FoundVia;
import org.bmj.userinsights.entity.Geographies;
import org.bmj.userinsights.entity.MainUserType;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.entity.Project;
import org.bmj.userinsights.entity.Tag;
import org.bmj.userinsights.insight.Dao.IInsightDao;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.dto.InsightWeblinkDto;
import org.bmj.userinsights.server.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
/**
 * This service class two service injections 
 * 1) AwsBucketService -- to call AWS related actions
 * 2) InsightDao  -- to call DB related actions
 */
public class InsightService implements IInsightService{
	
	@Autowired
	private IAwsBucketService awsBucketService;
	
	/**
	 * get reference to InsightService implementation.
	 * @return
	 * @throws Exception
	 */
	private IInsightDao getDaoRefDelete() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IInsightDao)ctx.getBean("refInsightDaoDeleteImpl");
	}

	/**
	 * get reference to InsightService implementation.
	 * @return
	 * @throws Exception
	 */
	private IInsightDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IInsightDao)ctx.getBean("refInsightDaoImpl");
	}

	/**
	 * Get insight details and related objects and populate in to view insight page
	 * @param insightId
	 */
	@Override
	public List<InsightDto> getInsightDetails(String insightId)
			throws Exception {
		
		return getDaoRef().getInsightDetails(insightId);
	}

	/**
	 * Save insight Details
	 * @param insightDTO
	 */
	@Override
	public Integer saveInsightDetails(InsightDto insightDTO)
			throws Exception {
		 Integer idInsight=getDaoRef().saveInsightDetails(insightDTO);
		 //If insight is new then move file from "temp" folder to new generated "id" folder.
		 if(insightDTO.getInsightDetailsDto().getId()==null || (insightDTO.getInsightDetailsDto().getId()!=null && insightDTO.getInsightDetailsDto().getId()==0)){// New Insight
			 List<InsightAttachmentDto> lstInsightAttachmentDTO =getDaoRef().getAttachmentList(idInsight+"");
			 for (InsightAttachmentDto insightAttachmentDTO : lstInsightAttachmentDTO) {
					String uploadFolder=insightAttachmentDTO.getFilePath();
					String fileId=insightAttachmentDTO.getFileId();
					String fileName=insightAttachmentDTO.getFileName();
					String fileExt=CommonUtils.getFileExtenstion(fileName);
					
					String targetFilePath=(new StringBuffer(uploadFolder)
					.append(File.separator)
					.append(fileId)
					.append(fileExt)
					.toString());
					
					String sourceFilePath=(new StringBuffer(uploadFolder.substring(0, uploadFolder.lastIndexOf("/")))
					.append(File.separator)
					.append(InsightsConstants.TEMP_FOLDER_NAME)
					.append(File.separator)
					.append(fileId)
					.append(fileExt)
					.toString());
					
					awsBucketService.uploadFileToBucket(idInsight+"", fileId, sourceFilePath);
			 }
		 }
		 return idInsight;
		 
	}
	
	/**
	 * return autocomplete list of product
	 */
	@Override
	public List<ProductDto> getAutoCompleteProductList(String prodName)
			throws Exception {
		return getDaoRef().getAutoCompleteProductList(prodName);
	}
	/**
	 * return autocomplete list of Project
	 */
	@Override
	public List<ProjectDto> getAutoCompleteProjectList(String projName)
			throws Exception {
		return getDaoRef().getAutoCompleteProjectList(projName);
	}
	/**
	 * return autocomplete list of Tag
	 */
	@Override
	public List<TagDto> getAutoCompleteTagList(String tagName) throws Exception {
		return getDaoRef().getAutoCompleteTagList(tagName);
	}
	/**
	 * return autocomplete list of insight title.
	 */
	@Override
	public List<InsightDto> getAutoCompleteInsightsList(String insightName)
			throws Exception {
		return getDaoRef().getAutoCompleteInsightsList(insightName);
	}
	/**
	 * Get DTO list of Found Via Master data.
	 */
	@Override
	public List<FoundViaDto> getFoundViaDetails() throws Exception {
		return getDaoRef().getFoundViaDetails();
	}
	/**
	 * Get DTO list of MainUSerType Master data.
	 */
	@Override
	public List<MainUserTypeDto> getMainUserTypeDetails() throws Exception {
		return getDaoRef().getMainUserTypeDetails();
	}
	/**
	 * Get DTO list of Geographies Master data.
	 */
	@Override
	public List<GeographiesDto> getGeographiesDetails() throws Exception {
		return getDaoRef().getGeographiesDetails();
	}
	/**
	 * get code list map value for the advance search 
	 */
	public Map<Integer, SelectValuesDto> getCodeListMap(String codelistName,String applicationId) throws Exception{		
		return getDaoRef().getCodeListMap(codelistName,applicationId);
	}
	/**
	 * Save single attachment detail into database
	 */
	@Override
	public Integer saveAttachment(InsightAttachmentDto insightAttachmentDTO)
			throws Exception {
		return getDaoRef().saveAttachment(insightAttachmentDTO);
	}
	/**
	 * Get  attachment list for insight  from database
	 */
	@Override
	public List<InsightAttachmentDto> getAttachmentList(String insightId)
			throws Exception {
		return getDaoRef().getAttachmentList(insightId);
	}
	/**
	 * Delete attachment from database.
	 */
	@Override
	public void deleteAttachment(String insightId) throws Exception {
		getDaoRef().deleteAttachment(insightId);
		
	}
	/**
	 * get value from config table base on key.
	 */
	@Override
	public String getConfigValue(String key) throws Exception {		
		return getDaoRef().getConfigValue(key);
	}
	/**
	 * Get  attachment details for insight from database.
	 */
	@Override
	public InsightAttachmentDto getAttachment(String insightId)
			throws Exception {
		return getDaoRef().getAttachment(insightId);
	}
	/**
	 * Get DTOL list of WebLinks data for insightID.
	 */
	@Override
	public List<InsightWeblinkDto> getWebLinkList(String insightId)
			throws Exception {
		return getDaoRef().getWebLinkList(insightId);
	}
	/**
	 * get AutoComplete Search TextList
	 */
	@Override
	public List<InsightDetailsDto> getAutoCompleteSearchTextList(String searchText)
			throws Exception {		
		return getDaoRef().getAutoCompleteSearchTextList(searchText);
	}
	/**
	 *  get AutoComplete Search TextList
	 */
	@Override
	public List<AdminUserDto> getadminUserDetails(Integer addedUserId)
			throws Exception {
		
		return getDaoRef(). getadminUserDetails(addedUserId);
	}
	/**
	 * Get master list  foundVia entity from database.
	 * @return
	 */
	@Override
	public List<FoundVia> getFoundVia() throws Exception {
		return getDaoRef(). getFoundVia();
	}
	/**
	 * Get master list of MainUserType entity from database.
	 * @return
	 */
	@Override
	public List<MainUserType> getAllMainUserTypes() throws Exception {
		return getDaoRef(). getAllMainUserTypes();
	}
	/**
	 * Get master list of Geography entity from database.
	 * @return
	 */
	@Override
	public List<Geographies> getAllGeographis() throws Exception {
		return getDaoRef(). getAllGeographis();
	}
	/**
	 * Get master list of Tag entity from database.
	 * @return
	 */
	@Override
	public List<Tag> getAllTags() throws Exception {
		return getDaoRef(). getAllTags();
	}
	/**
	 * Get master list of Project entity from database.
	 * @return
	 */
	@Override
	public List<Project> getAllProjects() throws Exception {
		return getDaoRef(). getAllProjects();
	}
	/**
	 * Get master list of Products entity from database.
	 * @return
	 */
	@Override
	public List<Product> getAllProducts() throws Exception {
		return getDaoRef(). getAllProducts();
	}
	
	/**
	 * This method will retrieve the dropdown values for user insight types and severity
	 */
	public List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception{
		return (List<SelectValuesDto>)getDaoRef().getSelectValuesDtoLst(codelistName, applicationId);
		
	}
	/**
	 * Get value from "InsightConfig" table which is based on "name" column.
	 * This method will return the config value for respective config key
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getConfigValueByKey(String key) throws Exception {
		return getDaoRef().getConfigValueByKey(key);
	}
	
	/**
	 * Set delete value to null for insight
	 * @return
	 * @throws Exception
	 */
	@Override
	public void saveOrUndoOperation(String insightId) throws Exception {
		getDaoRef().saveOrUndoOperation(insightId);
		
	}

	/**
	 * Get insight details for performing delete operation
	 * @param insightId
	 */
	@Override
	public List<InsightDto> getInsightDetailForDelete(String insightId)
			throws Exception {
		return getDaoRefDelete().getInsightDetailForDelete(insightId);
	}

    /**
	 * Performing delete operation
	 * @param insightId
	 */	
	@Override
	public void deleteInsight(String insightId) throws Exception {
		 getDaoRefDelete().deleteInsight(insightId);
		
	}
	

}
