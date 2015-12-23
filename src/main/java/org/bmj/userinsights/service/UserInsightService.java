package org.bmj.userinsights.service;

import org.bmj.userinsights.dao.IUserInsightDao;
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
	
	/*
	 * This method will search field
	 * @see org.bmj.userinsights.service.IUserInsightService#getSearchCriteriaDto()
	 */
	@Override
	public SearchCriteria getSearchCriteriaDto() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		
		return searchCriteria;
	}
}
