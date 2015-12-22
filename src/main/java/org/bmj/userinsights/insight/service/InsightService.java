package org.bmj.userinsights.insight.service;

import org.bmj.userinsights.insight.Dao.IInsightDao;
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

}
