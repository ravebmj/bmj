package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightAttachment  implements Serializable{

	
	private static final long serialVersionUID = -1941237565537892336L;
	private Integer id;
	private InsightDetail insightDetail;
	private String fileName;
	private String fileId;
	private Double fileSize;
	private String filePath;
	private String fileExternalPath;
	private String fileExternalAdditionalInfo;
	private Integer addedUser;
	private Date addedDate;
	
	public InsightAttachment(){}

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
	 * @return the insightDetail
	 */
	public InsightDetail getInsightDetail() {
		return insightDetail;
	}

	/**
	 * @param insightDetail the insightDetail to set
	 */
	public void setInsightDetail(InsightDetail insightDetail) {
		this.insightDetail = insightDetail;
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

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	
	

}
