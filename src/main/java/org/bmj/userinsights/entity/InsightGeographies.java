package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightGeographies {
	
	private int id;
	private int insightId;
	private int geographiesId;
	private String insightGeographicValue;
	private int addedUser;
	private Date addedDate;

	public InsightGeographies(){}

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

	public int getGeographiesId() {
		return geographiesId;
	}

	public void setGeographiesId(int geographiesId) {
		this.geographiesId = geographiesId;
	}

	public String getInsightGeographicValue() {
		return insightGeographicValue;
	}

	public void setInsightGeographicValue(String insightGeographicValue) {
		this.insightGeographicValue = insightGeographicValue;
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
