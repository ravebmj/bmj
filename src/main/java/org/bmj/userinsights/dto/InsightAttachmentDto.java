package org.bmj.userinsights.dto;

import java.io.Serializable;

import org.bmj.userinsights.common.CommonUtils;
/**
 * DTO for InsightAttachment entity.
 * @author nilesh.kambli
 *
 */
public class InsightAttachmentDto  implements Serializable{

	private static final long serialVersionUID = -3671483112362976379L;
	private Integer id;
	private String fileName;
	private String fileId;
	private Double fileSize;
	private String filePath;
	private String fileExternalPath;
	private String fileExternalAdditionalInfo;
	private Integer addedUser;
	
	
	private Integer insightId;
	private String sizeInKB;
	private String fileExt;
	private String fileNameWihPath;
	
	public InsightAttachmentDto(){}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileSize
	 */
	public Double getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileExternalPath
	 */
	public String getFileExternalPath() {
		return fileExternalPath;
	}

	/**
	 * @param fileExternalPath the fileExternalPath to set
	 */
	public void setFileExternalPath(String fileExternalPath) {
		this.fileExternalPath = fileExternalPath;
	}

	/**
	 * @return the fileExternalAdditionalInfo
	 */
	public String getFileExternalAdditionalInfo() {
		return fileExternalAdditionalInfo;
	}

	/**
	 * @param fileExternalAdditionalInfo the fileExternalAdditionalInfo to set
	 */
	public void setFileExternalAdditionalInfo(String fileExternalAdditionalInfo) {
		this.fileExternalAdditionalInfo = fileExternalAdditionalInfo;
	}

	/**
	 * @return the addedUser
	 */
	public Integer getAddedUser() {
		return addedUser;
	}

	/**
	 * @param addedUser the addedUser to set
	 */
	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
	}


	public Integer getInsightId() {
		return insightId;
	}

	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
	}

	public String getSizeInKB() {
		return CommonUtils.round(fileSize/(double) (1024),2)+" KB";
	}

	public void setSizeInKB(String sizeInKB) {
		this.sizeInKB = sizeInKB;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	/**
	 * @return the fileNameWihPath
	 */
	public String getFileNameWihPath() {
		return fileNameWihPath;
	}

	/**
	 * @param fileNameWihPath the fileNameWihPath to set
	 */
	public void setFileNameWihPath(String fileNameWihPath) {
		this.fileNameWihPath = fileNameWihPath;
	}


	
	

}
