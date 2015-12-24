package org.bmj.userinsights.entity;

import java.util.Date;

public class Project {
	
	private int id;
	private String name;
	private String description;
	private int applicationId;
	private int addedUser;
	private Date addedDate;
	private int modifiedUser;
	private Date modifiedDate;
	
	public Project(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(int addedUser) {
		this.addedUser = addedUser;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public int getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(int modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	

}
