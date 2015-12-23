package org.bmj.userinsights.service;

import java.util.List;

import org.bmj.userinsights.common.dto.DecodedNamesDto;

public interface IUserInsightService {
	public String getPerson(String id) throws Exception;
	public List<DecodedNamesDto> getCodeListDecodedNames(String codelistName,String applicationId) throws Exception;
}
