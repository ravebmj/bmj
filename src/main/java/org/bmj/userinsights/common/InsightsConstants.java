package org.bmj.userinsights.common;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dashboard.dto.DateCriteriaDto;

public class InsightsConstants {
	public static final String[] arrAuthenticationPages = {"createInsight"}; // List of pages for which authentication is required.
	public static final String SECRET_KEY = "secretKey";
	public static final String TOKEN_PREFIX = "BMJ";
	public static final String TXN_DTL_REF_PREFIX = "TXN_DTL";
	
	
	public static final String APPLICATION_ID = "1";
	public static final String INSIGHT_TYPE_CODE_LIST_NAME = "InsightType";
	public static final String SEVERITY_CODE_LIST_NAME = "Severity";
	
	
	public static List<DateCriteriaDto> getDateCriteriaLst(){
		
		List<DateCriteriaDto> dateCriteriaDtoLst = new ArrayList<DateCriteriaDto>();
		
		DateCriteriaDto createDateCriteriaDtoObj = new DateCriteriaDto();
		createDateCriteriaDtoObj.setCriteriaId(1);
		createDateCriteriaDtoObj.setCriteriaName("CreatedDate");
		
		DateCriteriaDto endDateCriteriaDtoObj = new DateCriteriaDto();
		endDateCriteriaDtoObj.setCriteriaId(1);
		endDateCriteriaDtoObj.setCriteriaName("EndDate");
		
		dateCriteriaDtoLst.add(createDateCriteriaDtoObj);
		dateCriteriaDtoLst.add(endDateCriteriaDtoObj);
		
		return dateCriteriaDtoLst;
		
	}
}
