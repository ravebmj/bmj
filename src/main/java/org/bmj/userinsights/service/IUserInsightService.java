package org.bmj.userinsights.service;

import java.util.List;

import org.bmj.userinsights.common.dto.DecodedNamesDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.search.dto.SearchCriteria;

public interface IUserInsightService {
	public String getPerson(String id) throws Exception;
	public List<DecodedNamesDto> getCodeListDecodedNames(String codelistName,String applicationId) throws Exception;
	
	public SearchCriteria getSearchCriteriaDto(List<InsightTypesDto> lstInsightTypesDto) throws Exception;
}
