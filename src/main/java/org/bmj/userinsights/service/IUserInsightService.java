package org.bmj.userinsights.service;

import org.bmj.userinsights.search.dto.SearchCriteria;

public interface IUserInsightService {
	public String getPerson(String id) throws Exception;
	
	public SearchCriteria getSearchCriteriaDto() throws Exception;
}
