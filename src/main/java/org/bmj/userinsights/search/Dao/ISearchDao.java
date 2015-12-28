package org.bmj.userinsights.search.Dao;

import java.util.List;

import org.bmj.userinsights.search.dto.SearchResultDetailDto;
import org.bmj.userinsights.search.dto.SearchResultDto;

public interface ISearchDao {
	public void searchProduct();
	
	public List<SearchResultDetailDto> getSearchResults(String keyword,String insightType,String serverity,String dateRangeOpt,String fromDate,String toDate) throws Exception;
}
