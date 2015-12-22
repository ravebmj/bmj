package org.bmj.userinsights.dashboard.Dao;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.SearchAllInsightsDto;

public interface IDashboardDao {
	public List<SearchAllInsightsDto> getSearchAllInsightsDtoLst() throws Exception;
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception;
}
