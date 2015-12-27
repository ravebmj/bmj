package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightMainUserType  implements Serializable{

	private Integer id;
	private InsightDetail insightDetail;
	private MainUserType mainUserType;
	private String mainUserTypeOtherValue;
	private Integer addedUser;
	private Date addedDate;
	
	public InsightMainUserType(){}

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
	 * @return the mainUserType
	 */
	public MainUserType getMainUserType() {
		return mainUserType;
	}

	/**
	 * @param mainUserType the mainUserType to set
	 */
	public void setMainUserType(MainUserType mainUserType) {
		this.mainUserType = mainUserType;
	}

	/**
	 * @return the mainUserTypeOtherValue
	 */
	public String getMainUserTypeOtherValue() {
		return mainUserTypeOtherValue;
	}

	/**
	 * @param mainUserTypeOtherValue the mainUserTypeOtherValue to set
	 */
	public void setMainUserTypeOtherValue(String mainUserTypeOtherValue) {
		this.mainUserTypeOtherValue = mainUserTypeOtherValue;
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
