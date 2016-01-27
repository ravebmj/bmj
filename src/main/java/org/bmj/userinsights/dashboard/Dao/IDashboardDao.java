package org.bmj.userinsights.dashboard.Dao;

import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;
/**
 * This interface has abstract methods to get recently,strongest evidences insights
 * The implementation provided in DashboardDao class
 */
public interface IDashboardDao {
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
