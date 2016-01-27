package org.bmj.userinsights.login.dao;

import java.util.List;

import org.bmj.userinsights.entity.AdminUser;
import org.bmj.userinsights.entity.InsightConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class LoginDao extends HibernateDaoSupport implements ILoginDao{


	/**
	 * This method is returning
	 * password for given User
	 * @param username
	 * @return String
	 */
	@Override
	public AdminUser gettingAdminUserDetail(String username) throws Exception {
		AdminUser adminUser=null;
		List returnVal = this.getHibernateTemplate().findByNamedQueryAndNamedParam("AdminUser.getPasswordForUserName","username",username);
		if (null != returnVal && returnVal.size() > 0) {
			adminUser = (AdminUser) returnVal.get(0);
			return adminUser;
		}

		return null;
	}

	/**
	 * This method is retrieving
	 *  value from DataBase 
	 * @param String
	 * @return String
	 */
	@Override
	@Cacheable(value="loginCache",key="#key")
	public String gettingPasswordPassPhraseValue(String key) throws Exception {
		
		InsightConfig insightConfig=null;
		List returnVal = this.getHibernateTemplate().findByNamedQueryAndNamedParam("InsightConfig.getValueByName","configKey", key);

		if (null != returnVal && returnVal.size() > 0) {
			insightConfig = (InsightConfig) returnVal.get(0);
			return insightConfig.getValue();
		}

		return null;
	}
}
