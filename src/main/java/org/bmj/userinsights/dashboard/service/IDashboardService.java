package org.bmj.userinsights.dashboard.service;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;

public interface IDashboardService {
	public List<InsightTypesDto> getSearchAllInsightsDtoLst() throws Exception;

	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception;
}
