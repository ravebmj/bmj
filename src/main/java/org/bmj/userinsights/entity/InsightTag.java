package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightTag {
	
	private int id;
	private int insightId;
	private int tagId;
	private int addedUser;
	private Date addedDate;
	
	public InsightTag(){}

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

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAddUser() {
		return addedUser;
	}

	public void setAddUser(int addUser) {
		this.addedUser = addUser;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	

}
