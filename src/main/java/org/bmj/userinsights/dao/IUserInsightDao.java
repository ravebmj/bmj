package org.bmj.userinsights.dao;

import java.util.List;

import org.bmj.userinsights.common.dto.SelectValuesDto;

public interface IUserInsightDao {
	public String getPerson(String id) throws Exception;
	public List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception;
}
