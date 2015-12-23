package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightMainUserType {

	private int id;
	private int insightId;
	private int mainUserTypeId;
	private String mainUserTypeOtherValue;
	private int addedUser;
	private Date addedDate;
	
	public InsightMainUserType(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInsightId() {
		return insightId;
	}

	public void setInsightId(int insightId) {
		this.insightId = insightId;
	}

	public int getMainUserTypeId() {
		return mainUserTypeId;
	}

	public void setMainUserTypeId(int mainUserTypeId) {
		this.mainUserTypeId = mainUserTypeId;
	}

	public String getMainUserTypeOtherValue() {
		return mainUserTypeOtherValue;
	}

	public void setMainUserTypeOtherValue(String mainUserTypeOtherValue) {
		this.mainUserTypeOtherValue = mainUserTypeOtherValue;
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
	
	
}
