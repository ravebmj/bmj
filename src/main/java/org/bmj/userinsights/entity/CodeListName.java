package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class CodeListName implements Serializable{


	private static final long serialVersionUID = -2417410023450197692L;

	private Integer codelistId;
	private String codelistName;
	private String codelistDescription;
	private Integer applicationId;
	private Integer addedUser;
	private Date addedDate;
	/**
	 * @return the codelistId
	 */
	public Integer getCodelistId() {
		return codelistId;
	}
	/**
	 * @param codelistId the codelistId to set
	 */
	public void setCodelistId(Integer codelistId) {
		this.codelistId = codelistId;
	}
	/**
	 * @return the codelistName
	 */
	public String getCodelistName() {
		return codelistName;
	}
	/**
	 * @param codelistName the codelistName to set
	 */
	public void setCodelistName(String codelistName) {
		this.codelistName = codelistName;
	}
	/**
	 * @return the codelistDescription
	 */
	public String getCodelistDescription() {
		return codelistDescription;
	}
	/**
	 * @param codelistDescription the codelistDescription to set
	 */
	public void setCodelistDescription(String codelistDescription) {
		this.codelistDescription = codelistDescription;
	}
	/**
	 * @return the applicationId
	 */
	public Integer getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
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
