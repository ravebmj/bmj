package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminUser implements Serializable{

	private static final long serialVersionUID = 8925164355263181577L;
	
	private Integer adminuserId;
	private String adminuserName;
	private String adminuserPassword;
	private String adminuserFirstName;
	private String adminuserMiddleName;
	private String adminuserLastName;
	private String adminuserEmailAddress;
	private Integer adminuserApplicationId;
	private Date addedDate;
	
	
	
	
	public Integer getAdminuserId() {
		return adminuserId;
	}
	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}
	public String getAdminuserName() {
		return adminuserName;
	}
	public void setAdminuserName(String adminuserName) {
		this.adminuserName = adminuserName;
	}
	public String getAdminuserPassword() {
		return adminuserPassword;
	}
	public void setAdminuserPassword(String adminuserPassword) {
		this.adminuserPassword = adminuserPassword;
	}
	public String getAdminuserFirstName() {
		return adminuserFirstName;
	}
	public void setAdminuserFirstName(String adminuserFirstName) {
		this.adminuserFirstName = adminuserFirstName;
	}
	public String getAdminuserMiddleName() {
		return adminuserMiddleName;
	}
	public void setAdminuserMiddleName(String adminuserMiddleName) {
		this.adminuserMiddleName = adminuserMiddleName;
	}
	public String getAdminuserLastName() {
		return adminuserLastName;
	}
	public void setAdminuserLastName(String adminuserLastName) {
		this.adminuserLastName = adminuserLastName;
	}
	public String getAdminuserEmailAddress() {
		return adminuserEmailAddress;
	}
	public void setAdminuserEmailAddress(String adminuserEmailAddress) {
		this.adminuserEmailAddress = adminuserEmailAddress;
	}
	public Integer getAdminuserApplicationId() {
		return adminuserApplicationId;
	}
	public void setAdminuserApplicationId(Integer adminuserApplicationId) {
		this.adminuserApplicationId = adminuserApplicationId;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

}
