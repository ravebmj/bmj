package org.bmj.userinsights.service;

import java.util.List;

import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dao.IUserInsightDao;
import org.bmj.userinsights.dashboard.dto.DateCriteriaDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.SeveritiesDto;
import org.bmj.userinsights.search.dto.SearchCriteria;
import org.bmj.userinsights.server.AppContext;
import org.bmj.userinsights.service.IUserInsightService;
import org.springframework.context.ApplicationContext;

public class UserInsightService implements IUserInsightService {

	@Override
	public String getPerson(String id) throws Exception {
		// TODO Auto-generated method stub
		return getDaoRef().getPerson(id);
	}
	/**
	 * get reference to GCSDao implementation.
	 * @return
	 * @throws Exception
	 */
	private IUserInsightDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IUserInsightDao)ctx.getBean("refUserInsightDaoImpl");
	}
	
	
	public List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception{
		return (List<SelectValuesDto>)getDaoRef().getSelectValuesDtoLst(codelistName, applicationId);
		
	}
	
	/*
	 * This method will search field
	 * @see org.bmj.userinsights.service.IUserInsightService#getSearchCriteriaDto()
	 */
	@Override
	public SearchCriteria getSearchCriteriaDto(List<InsightTypesDto> lstInsightTypesDto,List<SeveritiesDto> lstSeveritiesDto,List<DateCriteriaDto> lstDateCriteriaDto) throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setLstInsightTypesDto(lstInsightTypesDto);
		searchCriteria.setLstSeveritiesDto(lstSeveritiesDto);
		searchCriteria.setLstDateCriteriaDto(lstDateCriteriaDto);
		return searchCriteria;
	}
}
