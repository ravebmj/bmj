package org.bmj.userinsights.service;

import java.util.List;

import org.bmj.userinsights.common.dto.DecodedNamesDto;
import org.bmj.userinsights.dao.IUserInsightDao;
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
	
	
	public List<DecodedNamesDto> getCodeListDecodedNames(String codelistName,String applicationId) throws Exception{
		return getDaoRef().getCodeListDecodedNames(codelistName, applicationId);
		
	}
}
