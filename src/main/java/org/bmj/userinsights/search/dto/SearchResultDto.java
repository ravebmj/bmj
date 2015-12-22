package org.bmj.userinsights.search.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDto {
	
	//List of search Detail
	List<SearchResultDetailDto> searchResult = new ArrayList<>();

	public List<SearchResultDetailDto> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SearchResultDetailDto> searchResult) {
		this.searchResult = searchResult;
	}
	
	
	
	
	

}
