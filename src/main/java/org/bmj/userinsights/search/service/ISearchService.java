package org.bmj.userinsights.search.service;


import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.search.dto.SearchResultDto;
/**
 * This interface has abstract methods for search criteria
 */
public interface ISearchService {
	/**
	 * Get all insights when click on view all
	 * @return
	 * @throws Exception
	 */
	SearchResultDto getAllInsight() throws Exception;
	/**
	 * Get the search results based on search criteria
	 * @param keyword
	 * @param insightType
	 * @param serverity
	 * @param dateRangeOpt
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getSearchResults(String keyword,String insightType,String serverity,String dateRangeOpt,String fromDate,String toDate) throws Exception;
	/**
	 * Get the product related insights
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightForProducts(Integer productId) throws Exception;
	/**
	 * Get the project related insights
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightsForProject(Integer projectId) throws Exception;
	/**
	 * Get the Tag related insights
	 * @param tagId
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsighstForTag(Integer tagId) throws Exception;
	/**
	 * Get the insights details to generate PDF report
	 * @param insightIds
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightsForDownloadReport(String insightIds) throws Exception;
	/**
	 * Get the Tag name for tag id
	 * @param insightIds
	 * @return
	 * @throws Exception
	 */
	public String getInsightKeyWordForTag(Integer insightIds) throws Exception;
	/**
	 * Get the product name for product id
	 * @param insightIds
	 * @return
	 * @throws Exception
	 */
	public String getInsightKeyWordForProduct(Integer insightIds) throws Exception;
	/**
	 * Get the project name for project id
	 * @param insightIds
	 * @return
	 * @throws Exception
	 */
	public String getInsightKeyWordForProject(Integer insightIds) throws Exception;

}
