package org.bmj.userinsights.dashboard.service;

import java.util.List;

import org.bmj.userinsights.dao.IUserInsightDao;
import org.bmj.userinsights.dashboard.Dao.IDashboardDao;
import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.SearchAllInsightsDto;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

public class DashboardService implements IDashboardService{

	@Override
	public List<SearchAllInsightsDto> getSearchAllInsightsDtoLst()
			throws Exception {
		
		return getDaoRef().getSearchAllInsightsDtoLst();
	}
	
	@Override
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception {
		
		return getDaoRef().getRecentlyAddedInsights();
	}
	
	
	/**
	 * get reference to GCSDao implementation.
	 * @return
	 * @throws Exception
	 */
	private IDashboardDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IDashboardDao)ctx.getBean("refDashboardDaoImpl");
	}

	

}
