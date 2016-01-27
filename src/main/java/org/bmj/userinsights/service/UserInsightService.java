package org.bmj.userinsights.service;

import org.bmj.userinsights.dao.IUserInsightDao;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

public class UserInsightService implements IUserInsightService {

	/**
	 * get reference to User Insight Dao implementation.
	 * @return
	 * @throws Exception
	 */
	private IUserInsightDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (IUserInsightDao)ctx.getBean("refUserInsightDaoImpl");
	}
	
}
