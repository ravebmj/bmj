package org.bmj.userinsights.entity;

import java.util.Date;

public class CodeListName {

	private Integer id;
	private String name;
	private String description; 
	private Integer applicationId;
	private Integer codeListStatus;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
	
	public CodeListName(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCodeListStatus() {
		return codeListStatus;
	}

	public void setCodeListStatus(Integer codeListStatus) {
		this.codeListStatus = codeListStatus;
	}

	public Integer getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
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

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	
	
	
}
