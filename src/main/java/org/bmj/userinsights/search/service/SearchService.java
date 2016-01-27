package org.bmj.userinsights.search.service;

import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.search.Dao.ISearchDao;
import org.bmj.userinsights.search.dto.SearchResultDto;
import org.bmj.userinsights.server.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * The Dao is inject in this class by spring container 
 * and call the Dao method through that reference
 */
public class SearchService implements ISearchService {
	@Autowired
	private ISearchDao searchDao; // spring injection for SearchDao class
	
	/**
	 * Get All Insight Detail
	 * @see org.bmj.userinsights.search.service.ISearchService#getAllInsight()
	 */
	@Override
	public SearchResultDto getAllInsight() throws Exception {
		SearchResultDto dto = new SearchResultDto();		
		List<InsightDetailsDto> insightList = getDaoRef().getAllInsight();
		dto.setSearchResult(insightList);			
	return dto;

	}
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
	@Override
	public List<InsightDetailsDto> getSearchResults(String keyword,
			String insightType, String serverity, String dateRangeOpt,
			String fromDate, String toDate) throws Exception {		
		return searchDao.getSearchResults(keyword, insightType, serverity, dateRangeOpt, fromDate, toDate);
	}
	
	/**
	 * get reference to SearchDao implementation.
	 * @return
	 * @throws Exception
	 */
	private ISearchDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (ISearchDao)ctx.getBean("refSearchDaoImpl");
	}

	/**
	 * Call Dao method to get List of Insight
	 * for that Product id.
	 * @see org.bmj.userinsights.search.service.ISearchService#getInsightForProducts(java.lang.Integer)
	 */
	@Override
	public List<InsightDetailsDto> getInsightForProducts(Integer productId)
			throws Exception {		
		return getDaoRef().getInsightForProduct(productId);
	}

	/**
	 * Call Dao method to get List of Insight
	 * for that Project id.
	 * @see org.bmj.userinsights.search.service.ISearchService#getInsightForProducts(java.lang.Integer)
	 */
	@Override
	public List<InsightDetailsDto> getInsightsForProject(Integer projectId)
			throws Exception {		
		return getDaoRef().getInsightForProject(projectId);
	}

	/**
	 * Call Dao method to get List of Insight
	 * for that Tag id.
	 * @see org.bmj.userinsights.search.service.ISearchService#getInsightForProducts(java.lang.Integer)
	 */
	@Override
	public List<InsightDetailsDto> getInsighstForTag(Integer tagId)
			throws Exception {		
		return getDaoRef().getInsightForTag(tagId);
	}
	/**
	 * Get the list of InsightDetailsDto by passing insight id
	 * this list contains all the required information to generate PDF report.
	 */
	public List<InsightDetailsDto> getInsightsForDownloadReport(String insightIds) throws Exception{
		return getDaoRef().getInsightsForDownloadReport(insightIds);
	}
	/**
	 * To get Tag name by Key
	 */
	public String getInsightKeyWordForTag(Integer insightIds) throws Exception{
		return getDaoRef().getInsightKeyWordForTag(insightIds);
	}
	/**
	 * To get Product name by Key
	 */
	public String getInsightKeyWordForProduct(Integer insightIds) throws Exception{
		return getDaoRef().getInsightKeyWordForProduct(insightIds);
	}
	/**
	 * To get Project name by Key
	 */
	public String getInsightKeyWordForProject(Integer insightIds) throws Exception{
		return getDaoRef().getInsightKeyWordForProject(insightIds);
	}
}
