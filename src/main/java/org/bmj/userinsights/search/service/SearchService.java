package org.bmj.userinsights.search.service;

import org.bmj.userinsights.search.Dao.ISearchDao;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

public class SearchService implements ISearchService{
	
	
	/**
	 * get reference to SearchService implementation.
	 * @return
	 * @throws Exception
	 */
	private ISearchDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (ISearchDao)ctx.getBean("refSearchDaoImpl");
	}

}
