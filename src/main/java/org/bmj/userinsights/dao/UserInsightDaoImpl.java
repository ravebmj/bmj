package org.bmj.userinsights.dao;

import java.util.List;

import org.bmj.userinsights.entity.Person;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class UserInsightDaoImpl extends HibernateDaoSupport  implements IUserInsightDao{

	@Override
	public String getPerson(String id) throws Exception {
		Person person=null;
		List returnVal = this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("Person.getPerson",
						"id", new Integer(id));

		if (null != returnVal && returnVal.size() > 0) {
			person = (Person) returnVal.get(0);

			return person.getFname();
		}

		return null;
	}

}
