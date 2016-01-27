package org.bmj.userinsights.login.service;

import org.bmj.userinsights.entity.AdminUser;



public interface ILoginService {
	public AdminUser gettingAdminUserDetail(String username) throws Exception;
	public String gettingPasswordPassPhraseValue(String name) throws Exception;
}
