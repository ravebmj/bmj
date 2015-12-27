package org.bmj.userinsights.dashboard.Dao;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.StrongestEvidenceInsightDTO;

public interface IDashboardDao {
	public List<InsightTypesDto> getSearchAllInsightsDtoLst() throws Exception;
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception;
	public List<StrongestEvidenceInsightDTO> getStrongestEvidenceInsights() throws Exception;
}
