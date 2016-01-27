package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class ApplicationUser implements Serializable{

	
	private static final long serialVersionUID = -1900720732758114148L;
	
	private Integer appuserId;
	private String appuserName;
	private String appuserDescription;
	private String appuserIdentityCriteria;
	private Date addedDate;
	/**
	 * @return the appuserId
	 */
	public Integer getAppuserId() {
		return appuserId;
	}
	/**
	 * @param appuserId the appuserId to set
	 */
	public void setAppuserId(Integer appuserId) {
		this.appuserId = appuserId;
	}
	/**
	 * @return the appuserName
	 */
	public String getAppuserName() {
		return appuserName;
	}
	/**
	 * @param appuserName the appuserName to set
	 */
	public void setAppuserName(String appuserName) {
		this.appuserName = appuserName;
	}
	/**
	 * @return the appuserDescription
	 */
	public String getAppuserDescription() {
		return appuserDescription;
	}
	/**
	 * @param appuserDescription the appuserDescription to set
	 */
	public void setAppuserDescription(String appuserDescription) {
		this.appuserDescription = appuserDescription;
	}
	/**
	 * @return the appuserIdentityCriteria
	 */
	public String getAppuserIdentityCriteria() {
		return appuserIdentityCriteria;
	}
	/**
	 * @param appuserIdentityCriteria the appuserIdentityCriteria to set
	 */
	public void setAppuserIdentityCriteria(String appuserIdentityCriteria) {
		this.appuserIdentityCriteria = appuserIdentityCriteria;
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
