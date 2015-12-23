package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;
import java.util.List;

public class DashboardDTO implements Serializable{


	private static final long serialVersionUID = -8014402374004189946L;
	
	private String searchTxt;	
	private String searchAllInsight;
	private List<InsightTypesDto> searchAllInsightsDtoLst;
	private List<RecentInsightsDto> recentInsightsDtoLst;

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

	public List<InsightTypesDto> getSearchAllInsightsDtoLst() {
		return searchAllInsightsDtoLst;
	}

	public void setSearchAllInsightsDtoLst(
			List<InsightTypesDto> searchAllInsightsDtoLst) {
		this.searchAllInsightsDtoLst = searchAllInsightsDtoLst;
	}

	public List<RecentInsightsDto> getRecentInsightsDtoLst() {
		return recentInsightsDtoLst;
	}

	public void setRecentInsightsDtoLst(List<RecentInsightsDto> recentInsightsDtoLst) {
		this.recentInsightsDtoLst = recentInsightsDtoLst;
	}

}
