package org.bmj.userinsights.insight.service;

import java.util.List;

import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.insight.Dao.IInsightDao;
import org.bmj.userinsights.insight.dto.InsightDTO;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

public class InsightService implements IInsightService{
	
	
	/**
	 * get reference to InsightService implementation.
	 * @return
	 * @throws Exception
	 */
	private IInsightDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IInsightDao)ctx.getBean("refInsightDaoImpl");
	}

	/**
	 * Get insight details and related objects and populate in to view insight page
	 */
	@Override
	public List<InsightDTO> getInsightDetails(String insightId)
			throws Exception {
		
		return getDaoRef().getInsightDetails(insightId);
	}

	/**
	 * get the user insight types
	 */
	@Override
	public List<InsightTypesDto> getInsightTypes(String insightId)
			throws Exception {
		
		return getDaoRef().getInsightTypes(insightId);
	}
	
	

}
