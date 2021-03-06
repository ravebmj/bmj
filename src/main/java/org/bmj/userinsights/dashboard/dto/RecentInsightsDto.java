package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.bmj.userinsights.entity.InsightProject;


public class RecentInsightsDto implements Serializable{

	
	private static final long serialVersionUID = -3887757291106636530L;
	
	private Integer insightId;
	private String insightName;
	private String projectName;
	private String type;
	private String lastEdited;
	private List<InsightProject> projects;
	
	
	
	
	public String getInsightName() {
		return insightName;
	}
	public void setInsightName(String insightName) {
		this.insightName = insightName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getLastEdited() {
		return lastEdited;
	}
	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}
	public Integer getInsightId() {
		return insightId;
	}
	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<InsightProject> getProjects() {
		return projects;
	}
	public void setProjects(List<InsightProject> projects) {
		this.projects = projects;
	}
	
}
