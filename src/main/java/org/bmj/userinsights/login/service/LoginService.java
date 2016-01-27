package org.bmj.userinsights.login.service;

import org.bmj.userinsights.entity.AdminUser;
import org.bmj.userinsights.login.dao.ILoginDao;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

public class LoginService implements ILoginService {

	
	/**
	 * get reference toLoginDao implementation.
	 * @return
	 * @throws Exception
	 */
	private ILoginDao getDaoRef() throws Exception{
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		return (ILoginDao)ctx.getBean("refLoginDaoImpl");
	}
	
	@Override
	public AdminUser gettingAdminUserDetail(String username) throws Exception {
		return getDaoRef().gettingAdminUserDetail(username);
	}

	@Override
	public String gettingPasswordPassPhraseValue(String key) throws Exception {
		return getDaoRef().gettingPasswordPassPhraseValue(key);
	}

}
