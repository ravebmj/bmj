package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightWeblink {
	
	private int id;
	private int insightId;
	private String weblinkValue;
	private int addedUser;
	private Date addedDate;
	
	public InsightWeblink(){}

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

	public String getWeblinkValue() {
		return weblinkValue;
	}

	public void setWeblinkValue(String weblinkValue) {
		this.weblinkValue = weblinkValue;
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
