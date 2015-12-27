package org.bmj.userinsights.insight.service;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.insight.dto.InsightDTO;

public interface IInsightService {
	
	public List<InsightDTO> getInsightDetails( String insightId) throws Exception;
	
	public List<InsightTypesDto> getInsightTypes(String insightId) throws Exception;
}
