package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;
import java.util.List;

public class DashboardDTO implements Serializable{


	private static final long serialVersionUID = -8014402374004189946L;
	
	private String searchTxt;	
	private String searchAllInsight;
	private List<InsightTypesDto> insightTypesDtoLst;
	private List<RecentInsightsDto> recentInsightsDtoLst;
	private List<StrongestEvidenceInsightDTO> strongestEvidenceInsightDtoLst;

	public String getSearchAllInsight() {
		return searchAllInsight;
	}

	public void setSearchAllInsight(String searchAllInsight) {
		this.searchAllInsight = searchAllInsight;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	

	public List<RecentInsightsDto> getRecentInsightsDtoLst() {
		return recentInsightsDtoLst;
	}

	public void setRecentInsightsDtoLst(List<RecentInsightsDto> recentInsightsDtoLst) {
		this.recentInsightsDtoLst = recentInsightsDtoLst;
	}

	public List<InsightTypesDto> getInsightTypesDtoLst() {
		return insightTypesDtoLst;
	}

	public void setInsightTypesDtoLst(List<InsightTypesDto> insightTypesDtoLst) {
		this.insightTypesDtoLst = insightTypesDtoLst;
	}

	public List<StrongestEvidenceInsightDTO> getStrongestEvidenceInsightDtoLst() {
		return strongestEvidenceInsightDtoLst;
	}

	public void setStrongestEvidenceInsightDtoLst(
			List<StrongestEvidenceInsightDTO> strongestEvidenceInsightDtoLst) {
		this.strongestEvidenceInsightDtoLst = strongestEvidenceInsightDtoLst;
	}

}
