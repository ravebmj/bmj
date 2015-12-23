package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightFoundVia {
	
	private int id;
	private int insightId;
	private int foundViaId;
	private String foundViaValue;
	private int addedUser;
	private Date addedDate;
	
	public InsightFoundVia(){}

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

	public int getFoundViaId() {
		return foundViaId;
	}

	public void setFoundViaId(int foundViaId) {
		this.foundViaId = foundViaId;
	}

	public String getFoundViaValue() {
		return foundViaValue;
	}

	public void setFoundViaValue(String foundViaValue) {
		this.foundViaValue = foundViaValue;
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
