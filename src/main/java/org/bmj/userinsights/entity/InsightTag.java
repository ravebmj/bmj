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

	/**
	 * @return the addedUser
	 */
	public int getAddedUser() {
		return addedUser;
	}

	/**
	 * @param addedUser the addedUser to set
	 */
	public void setAddedUser(int addedUser) {
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
