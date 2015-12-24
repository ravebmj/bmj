package org.bmj.userinsights.search.dto;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dashboard.dto.DateCriteriaDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.SeveritiesDto;

public class SearchCriteria {
	
	private String keyword;
	private String insightType;
	private String serverity;
	private String createdDate;
	private String fromDate;
	private String toDate;
	//private List<String> severity = new ArrayList<>();
	//private List<String> insightType = new ArrayList<>();
	List<InsightTypesDto> lstInsightTypesDto = new ArrayList<InsightTypesDto>();
	List<SeveritiesDto> lstSeveritiesDto = new ArrayList<SeveritiesDto>();
	List<DateCriteriaDto> lstDateCriteriaDto = new ArrayList<DateCriteriaDto>();
	private String type;
	
	public SearchCriteria(){}

	

	

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

	public String getInsightType() {
		return insightType;
	}

	public void setInsightType(String insightType) {
		this.insightType = insightType;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public List<SeveritiesDto> getLstSeveritiesDto() {
		return lstSeveritiesDto;
	}



	public void setLstSeveritiesDto(List<SeveritiesDto> lstSeveritiesDto) {
		this.lstSeveritiesDto = lstSeveritiesDto;
	}



	public String getServerity() {
		return serverity;
	}



	public void setServerity(String serverity) {
		this.serverity = serverity;
	}





	public List<DateCriteriaDto> getLstDateCriteriaDto() {
		return lstDateCriteriaDto;
	}





	public void setLstDateCriteriaDto(List<DateCriteriaDto> lstDateCriteriaDto) {
		this.lstDateCriteriaDto = lstDateCriteriaDto;
	}





	public String getCreatedDate() {
		return createdDate;
	}





	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}





	public String getFromDate() {
		return fromDate;
	}





	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}





	public String getToDate() {
		return toDate;
	}





	public void setToDate(String toDate) {
		this.toDate = toDate;
	}



	
	
}
