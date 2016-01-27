package org.bmj.userinsights.dashboard.service;

import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;
/**
 * This interface having the abstract method which service class can give the implementation
 */
public interface IDashboardService {
	/**
	 * Get list of recently added insights by added date
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getRecentlyAddedInsights() throws Exception;
	/**
	 * Get list of strongest evidence insights by users count
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getStrongestEvidenceInsights() throws Exception;
}
