package org.bmj.userinsights.search.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bmj.userinsights.search.Dao.ISearchDao;
import org.bmj.userinsights.search.dto.SearchResultDetailDto;
import org.bmj.userinsights.search.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchService implements ISearchService {
	@Autowired
	private ISearchDao searchDao;
	@Override
	public SearchResultDto getAllInsight() {
		SearchResultDto dto = new SearchResultDto();
		
		for(int i=0;i<14;i++){
			SearchResultDetailDto detailDto = new SearchResultDetailDto();
			detailDto.setProjectName("project name"+i);
			//detailDto.setLastEditedDate(new Date());
			/*detailDto.setProduct(new ArrayList<String>() {{
			    add("prodct");
			}});*/
			/*detailDto.setTitle("title"+i);
			detailDto.setUser(10+i);
			detailDto.setTags(new ArrayList<String>() {{
			    add("tags");
			}});*/
			
			dto.getSearchResult().add(detailDto);
		}
		
		return dto;

	}
	
	public void searchProduct(){
		searchDao.searchProduct();
	}

	@Override
	public List<SearchResultDetailDto> getSearchResults(String keyword,
			String insightType, String serverity, String dateRangeOpt,
			String fromDate, String toDate) throws Exception {
		
		return searchDao.getSearchResults(keyword, insightType, serverity, dateRangeOpt, fromDate, toDate);
	}
}
