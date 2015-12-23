package org.bmj.userinsights.entity;

import java.util.Date;

public class Insight {
	
	int id;
	String title;
	String description;
	int type;
	Date insightFoundDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getInsightFoundDate() {
		return insightFoundDate;
	}
	public void setInsightFoundDate(Date insightFoundDate) {
		this.insightFoundDate = insightFoundDate;
	}
	public int getInsightFoundCount() {
		return insightFoundCount;
	}
	public void setInsightFoundCount(int insightFoundCount) {
		this.insightFoundCount = insightFoundCount;
	}
	public int getSevirity() {
		return sevirity;
	}
	public void setSevirity(int sevirity) {
		this.sevirity = sevirity;
	}
	public int getInsightApplicationID() {
		return insightApplicationID;
	}
	public void setInsightApplicationID(int insightApplicationID) {
		this.insightApplicationID = insightApplicationID;
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
	public int getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(int modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	int insightFoundCount;
	int sevirity;
	int insightApplicationID;
	int addedUser;
	Date addedDate;
	int modifiedUser;
	Date modifiedDate;
	

}
