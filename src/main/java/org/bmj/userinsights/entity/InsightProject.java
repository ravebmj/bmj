package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightProject {
	
	private int id;
	private int insightId;
	private int projectId;
	private int addedUser;
	private Date addedDate;
	
	public InsightProject(){}

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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
