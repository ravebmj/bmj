package org.bmj.userinsights.insight.Dao;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.insight.dto.InsightDTO;

public interface IInsightDao {
	public List<InsightDTO> getInsightDetails(String insightId) throws Exception;
	public List<InsightTypesDto> getInsightTypes(String insightId) throws Exception;
}
