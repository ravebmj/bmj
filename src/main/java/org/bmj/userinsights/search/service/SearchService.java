package org.bmj.userinsights.search.service;

import java.util.ArrayList;
import java.util.Date;

import org.bmj.userinsights.search.dto.SearchResultDetailDto;
import org.bmj.userinsights.search.dto.SearchResultDto;

public class SearchService implements ISearchService {

	@Override
	public SearchResultDto getAllInsight() {
		SearchResultDto dto = new SearchResultDto();
		
		for(int i=0;i<14;i++){
			SearchResultDetailDto detailDto = new SearchResultDetailDto();
			detailDto.setProjectName("project name"+i);
			detailDto.setLastEditedDate(new Date());
			detailDto.setProduct(new ArrayList<String>() {{
			    add("prodct");
			}});
			detailDto.setTitle("title"+i);
			detailDto.setUser(10+i);
			detailDto.setTags(new ArrayList<String>() {{
			    add("tags");
			}});
			
			dto.getSearchResult().add(detailDto);
		}
		
		return dto;

	}
}
