package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class CodelistName implements Serializable{

	
	private static final long serialVersionUID = -2417410023450197692L;

	private Integer codelistId;
	private String codelistName;
	private String codelistDescription;
	private Integer applicationId;
	private Integer codelistStatus;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
	
	
	
	public Integer getCodelistId() {
		return codelistId;
	}
	public void setCodelistId(Integer codelistId) {
		this.codelistId = codelistId;
	}
	public String getCodelistName() {
		return codelistName;
	}
	public void setCodelistName(String codelistName) {
		this.codelistName = codelistName;
	}
	public String getCodelistDescription() {
		return codelistDescription;
	}
	public void setCodelistDescription(String codelistDescription) {
		this.codelistDescription = codelistDescription;
	}
	public Integer getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public Integer getCodelistStatus() {
		return codelistStatus;
	}
	public void setCodelistStatus(Integer codelistStatus) {
		this.codelistStatus = codelistStatus;
	}
	public Integer getAddedUser() {
		return addedUser;
	}
	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public Integer getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(Integer modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
