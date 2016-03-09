package org.bmj.userinsights.dashboard.service;

import java.util.List;

import org.bmj.userinsights.dashboard.Dao.IDashboardDao;
import org.bmj.userinsights.dashboard.dto.DashboardDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;
/**
 * This service class having dao reference injected by spring container
 * throught that reference call the dao methods and get the details
 */
public class DashboardService implements IDashboardService{

	
	/**
	 * Get list of recently added insights by added date
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<InsightDetailsDto> getRecentlyAddedInsights() throws Exception {		
		return getDaoRef().getRecentlyAddedInsights();
	}

	/**
	 * Get list of strongest evidence insights by users count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<InsightDetailsDto> getStrongestEvidenceInsights()
			throws Exception {		
		return getDaoRef().getStrongestEvidenceInsights();
	}
	
	/**
	 * get reference to DashboardDao implementation.
	 * @return
	 * @throws Exception
	 */
	private IDashboardDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IDashboardDao)ctx.getBean("refDashboardDaoImpl");
	}

	/**
	 * Set delete date for insight
	 * @return
	 * @throws Exception
	 */
	@Override
	public void saveInsightForDeleteDate(DashboardDto dashboardDto ) throws Exception {
		 getDaoRef().saveInsightForDeleteDate(dashboardDto );
		
	}

	

	

}
