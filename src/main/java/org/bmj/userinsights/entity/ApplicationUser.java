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
	
	
	public Integer getAppuserId() {
		return appuserId;
	}
	public void setAppuserId(Integer appuserId) {
		this.appuserId = appuserId;
	}
	public String getAppuserName() {
		return appuserName;
	}
	public void setAppuserName(String appuserName) {
		this.appuserName = appuserName;
	}
	public String getAppuserDescription() {
		return appuserDescription;
	}
	public void setAppuserDescription(String appuserDescription) {
		this.appuserDescription = appuserDescription;
	}
	public String getAppuserIdentityCriteria() {
		return appuserIdentityCriteria;
	}
	public void setAppuserIdentityCriteria(String appuserIdentityCriteria) {
		this.appuserIdentityCriteria = appuserIdentityCriteria;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	

}
