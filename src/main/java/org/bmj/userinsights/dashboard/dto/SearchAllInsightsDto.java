package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;

public class SearchAllInsightsDto implements Serializable{

	private static final long serialVersionUID = -2044322730825914074L;

	private Integer searchInsightId;
	private String searchInsightName;
	
	
	
	
	public Integer getSearchInsightId() {
		return searchInsightId;
	}
	public void setSearchInsightId(Integer searchInsightId) {
		this.searchInsightId = searchInsightId;
	}
	public String getSearchInsightName() {
		return searchInsightName;
	}
	public void setSearchInsightName(String searchInsightName) {
		this.searchInsightName = searchInsightName;
	}
}
