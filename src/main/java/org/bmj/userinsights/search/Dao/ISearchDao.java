package org.bmj.userinsights.search.Dao;

import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.search.dto.SearchResultDto;
/**
 * This interface has abstract methods for search criteria
 */
public interface ISearchDao {
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
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightForProduct(Integer id) throws Exception;
	/**
	 * Get the project related insights
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightForProject(Integer id) throws Exception;
	/**
	 * Get the Tag related insights
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getInsightForTag(Integer id) throws Exception;
	/**
	 * Get all insights when click on view all
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getAllInsight() throws Exception;
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
	/**
	 * Get all product when click on view all
	 * @return
	 * @throws Exception
	 */
	List<ProductDto>  getAllActiveProducts() throws Exception;
}
