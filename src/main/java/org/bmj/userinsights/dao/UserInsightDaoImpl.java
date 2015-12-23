package org.bmj.userinsights.dao;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.common.dto.DecodedNamesDto;
import org.bmj.userinsights.entity.CodelistCodeDecode;
import org.bmj.userinsights.entity.CodelistName;
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

	@Override
	public List<DecodedNamesDto> getCodeListDecodedNames(String codelistName,
			String applicationId) throws Exception {
		List<DecodedNamesDto> DecodedNamesDtoLst = new ArrayList<DecodedNamesDto>();
		CodelistName codelistNameObj = null;
		
		List returnVal = this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("CodelistName.getCodelistName",
						new String[]{"codelistName","applicationId"},new Object[]{codelistName,Integer.valueOf(applicationId)});
		
		if(returnVal!=null && returnVal.size()>0){
			codelistNameObj = (CodelistName) returnVal.get(0);
		}
		
		if(codelistNameObj!=null){
			List<CodelistCodeDecode> lstCodelistCodeDecode = (List<CodelistCodeDecode>) this.getHibernateTemplate()
					.findByNamedQueryAndNamedParam("CodelistCodeDecode.getCodelistCodeDecode",
							new String[]{"codelistId"},new Object[]{codelistNameObj.getCodelistId()});	
			if(lstCodelistCodeDecode!=null && lstCodelistCodeDecode.size()>0){
				
				for(CodelistCodeDecode codeDecode : lstCodelistCodeDecode){
					DecodedNamesDto dtoObj = new DecodedNamesDto();
					dtoObj.setCodeDecodedCode(codeDecode.getCodeDecodeCode());
					dtoObj.setCodeDecodedName(codeDecode.getCodeDecodeDecode());
					DecodedNamesDtoLst.add(dtoObj);
				}
			}
		}
		
		
		return DecodedNamesDtoLst;
	}

}
