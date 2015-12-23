package org.bmj.userinsights.dashboard.Dao;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;

public interface IDashboardDao {
	public List<InsightTypesDto> getSearchAllInsightsDtoLst() throws Exception;
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception;
}
