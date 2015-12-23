package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightDetail {

	private int id;
	private String title;
	private String description;
	private int type;
	private Date foundDate;
	private int foundCount;
	private int insightServerity;
	private int insightApplicationID;
	private int addedUser;
	private Date addedDate;
	private int modifiedUser;
	private Date modifiedDate;
	
	public InsightDetail(){}

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

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public int getFoundCount() {
		return foundCount;
	}

	public void setFoundCount(int foundCount) {
		this.foundCount = foundCount;
	}

	public int getInsightServerity() {
		return insightServerity;
	}

	public void setInsightServerity(int insightServerity) {
		this.insightServerity = insightServerity;
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
	
	
	
}
