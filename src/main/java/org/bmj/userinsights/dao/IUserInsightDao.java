package org.bmj.userinsights.dao;

import java.util.List;

import org.bmj.userinsights.common.dto.DecodedNamesDto;

public interface IUserInsightDao {
	public String getPerson(String id) throws Exception;
	public List<DecodedNamesDto> getCodeListDecodedNames(String codelistName,String applicationId) throws Exception;
}
