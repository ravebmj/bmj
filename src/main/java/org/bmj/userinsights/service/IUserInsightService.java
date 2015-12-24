package org.bmj.userinsights.service;

import java.util.List;

import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dashboard.dto.DateCriteriaDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.SeveritiesDto;
import org.bmj.userinsights.search.dto.SearchCriteria;

public interface IUserInsightService {
	public String getPerson(String id) throws Exception;
	public List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception;
	
	public SearchCriteria getSearchCriteriaDto(List<InsightTypesDto> lstInsightTypesDto,List<SeveritiesDto> lstSeveritiesDto,List<DateCriteriaDto> lstDateCriteriaDto) throws Exception;
}
