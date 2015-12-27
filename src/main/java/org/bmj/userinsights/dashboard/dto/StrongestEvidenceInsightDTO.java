package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;

public class StrongestEvidenceInsightDTO implements Serializable{

	private static final long serialVersionUID = 4013886837885499326L;
	
	private Integer evidenceId;
	private String insightName;
	private String projectName;
	
	
	public Integer getEvidenceId() {
		return evidenceId;
	}
	public void setEvidenceId(Integer evidenceId) {
		this.evidenceId = evidenceId;
	}
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
	

}
