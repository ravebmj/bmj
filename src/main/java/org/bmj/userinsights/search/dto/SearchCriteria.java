package org.bmj.userinsights.search.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	
	private String Keyword;
	private List<String> severity = new ArrayList<>();
	private List<String> insightType = new ArrayList<>();
	private String type;
	
	public SearchCriteria(){}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}

	public List<String> getSeverity() {
		return severity;
	}

	public void setSeverity(List<String> severity) {
		this.severity = severity;
	}

	public List<String> getInsightType() {
		return insightType;
	}

	public void setInsightType(List<String> insightType) {
		this.insightType = insightType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
