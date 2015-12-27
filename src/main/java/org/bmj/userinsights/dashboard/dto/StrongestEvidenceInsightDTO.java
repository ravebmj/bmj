package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;
import java.util.List;

import org.bmj.userinsights.entity.InsightProject;

public class StrongestEvidenceInsightDTO implements Serializable{

	private static final long serialVersionUID = 4013886837885499326L;
	
	private Integer insightId;
	private String insightName;
	private String projectName;
	private Integer foundCount;
	
	
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
	public Integer getInsightId() {
		return insightId;
	}
	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
	}
	public List<InsightProject> getProjects() {
		return projects;
	}
	public void setProjects(List<InsightProject> projects) {
		this.projects = projects;
	}
	public Integer getFoundCount() {
		return foundCount;
	}
	public void setFoundCount(Integer foundCount) {
		this.foundCount = foundCount;
	}
	

}
