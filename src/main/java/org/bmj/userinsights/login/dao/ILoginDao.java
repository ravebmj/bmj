package org.bmj.userinsights.login.dao;

import org.bmj.userinsights.entity.AdminUser;

public interface ILoginDao {

	public AdminUser gettingAdminUserDetail(String username) throws Exception;
	public String gettingPasswordPassPhraseValue(String key) throws Exception;

}
