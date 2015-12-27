package org.bmj.userinsights.dashboard.service;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.StrongestEvidenceInsightDTO;

public interface IDashboardService {
	public List<InsightTypesDto> getSearchAllInsightsDtoLst() throws Exception;

	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception;
	
	public List<StrongestEvidenceInsightDTO> getStrongestEvidenceInsights() throws Exception;
}
