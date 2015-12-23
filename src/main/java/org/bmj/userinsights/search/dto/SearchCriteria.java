package org.bmj.userinsights.search.dto;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dashboard.dto.InsightTypesDto;

public class SearchCriteria {
	
	private String Keyword;
	private List<String> severity = new ArrayList<>();
	//private List<String> insightType = new ArrayList<>();
	List<InsightTypesDto> lstInsightTypesDto = new ArrayList<InsightTypesDto>();
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

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<InsightTypesDto> getLstInsightTypesDto() {
		return lstInsightTypesDto;
	}

	public void setLstInsightTypesDto(List<InsightTypesDto> lstInsightTypesDto) {
		this.lstInsightTypesDto = lstInsightTypesDto;
	}
	
	
	
}
