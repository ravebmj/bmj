package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;

public class InsightTypesDto implements Serializable{

	private static final long serialVersionUID = -2044322730825914074L;

	private Integer insightTypeId;
	private String insightTypeName;
	
	
	public Integer getInsightTypeId() {
		return insightTypeId;
	}
	public void setInsightTypeId(Integer insightTypeId) {
		this.insightTypeId = insightTypeId;
	}
	public String getInsightTypeName() {
		return insightTypeName;
	}
	public void setInsightTypeName(String insightTypeName) {
		this.insightTypeName = insightTypeName;
	}
	
	
	
	
	
}
