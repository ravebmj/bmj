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
	private Integer superadminRole;
	
	
	
	
	
	/**
	 * @return the superadminRole
	 */
	public Integer getSuperadminRole() {
		return superadminRole;
	}
	/**
	 * @param superadminRole the superadminRole to set
	 */
	public void setSuperadminRole(Integer superadminRole) {
		this.superadminRole = superadminRole;
	}
	/**
	 * @return the adminuserId
	 */
	public Integer getAdminuserId() {
		return adminuserId;
	}
	/**
	 * @param adminuserId the adminuserId to set
	 */
	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}
	/**
	 * @return the adminuserName
	 */
	public String getAdminuserName() {
		return adminuserName;
	}
	/**
	 * @param adminuserName the adminuserName to set
	 */
	public void setAdminuserName(String adminuserName) {
		this.adminuserName = adminuserName;
	}
	/**
	 * @return the adminuserPassword
	 */
	public String getAdminuserPassword() {
		return adminuserPassword;
	}
	/**
	 * @param adminuserPassword the adminuserPassword to set
	 */
	public void setAdminuserPassword(String adminuserPassword) {
		this.adminuserPassword = adminuserPassword;
	}
	/**
	 * @return the adminuserFirstName
	 */
	public String getAdminuserFirstName() {
		return adminuserFirstName;
	}
	/**
	 * @param adminuserFirstName the adminuserFirstName to set
	 */
	public void setAdminuserFirstName(String adminuserFirstName) {
		this.adminuserFirstName = adminuserFirstName;
	}
	/**
	 * @return the adminuserMiddleName
	 */
	public String getAdminuserMiddleName() {
		return adminuserMiddleName;
	}
	/**
	 * @param adminuserMiddleName the adminuserMiddleName to set
	 */
	public void setAdminuserMiddleName(String adminuserMiddleName) {
		this.adminuserMiddleName = adminuserMiddleName;
	}
	/**
	 * @return the adminuserLastName
	 */
	public String getAdminuserLastName() {
		return adminuserLastName;
	}
	/**
	 * @param adminuserLastName the adminuserLastName to set
	 */
	public void setAdminuserLastName(String adminuserLastName) {
		this.adminuserLastName = adminuserLastName;
	}
	/**
	 * @return the adminuserEmailAddress
	 */
	public String getAdminuserEmailAddress() {
		return adminuserEmailAddress;
	}
	/**
	 * @param adminuserEmailAddress the adminuserEmailAddress to set
	 */
	public void setAdminuserEmailAddress(String adminuserEmailAddress) {
		this.adminuserEmailAddress = adminuserEmailAddress;
	}
	/**
	 * @return the adminuserApplicationId
	 */
	public Integer getAdminuserApplicationId() {
		return adminuserApplicationId;
	}
	/**
	 * @param adminuserApplicationId the adminuserApplicationId to set
	 */
	public void setAdminuserApplicationId(Integer adminuserApplicationId) {
		this.adminuserApplicationId = adminuserApplicationId;
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
