package org.bmj.userinsights.search.dto;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.common.dto.SelectValuesDto;

public class SearchCriteriaDto {
	
	private String keyword;
	private String insightType;
	private String serverity;
	private String createdDate;
	private String fromDate;
	private String toDate;
	
	private String strSearch;
	
	
	List<SelectValuesDto> lstInsightTypesDto = new ArrayList<SelectValuesDto>();
	List<SelectValuesDto> lstSeveritiesDto = new ArrayList<SelectValuesDto>();
	List<SelectValuesDto> lstDateCriteriaDto = new ArrayList<SelectValuesDto>();
	
	private String type;
	public SearchCriteriaDto(){}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the insightType
	 */
	public String getInsightType() {
		return insightType;
	}
	/**
	 * @param insightType the insightType to set
	 */
	public void setInsightType(String insightType) {
		this.insightType = insightType;
	}
	/**
	 * @return the serverity
	 */
	public String getServerity() {
		return serverity;
	}
	/**
	 * @param serverity the serverity to set
	 */
	public void setServerity(String serverity) {
		this.serverity = serverity;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the strSearch
	 */
	public String getStrSearch() {
		return strSearch;
	}
	/**
	 * @param strSearch the strSearch to set
	 */
	public void setStrSearch(String strSearch) {
		this.strSearch = strSearch;
	}
	
	/**
	 * @return the lstDateCriteriaDto
	 */
	public List<SelectValuesDto> getLstDateCriteriaDto() {
		return lstDateCriteriaDto;
	}
	/**
	 * @param lstDateCriteriaDto the lstDateCriteriaDto to set
	 */
	public void setLstDateCriteriaDto(List<SelectValuesDto> lstDateCriteriaDto) {
		this.lstDateCriteriaDto = lstDateCriteriaDto;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the lstInsightTypesDto
	 */
	public List<SelectValuesDto> getLstInsightTypesDto() {
		return lstInsightTypesDto;
	}
	/**
	 * @param lstInsightTypesDto the lstInsightTypesDto to set
	 */
	public void setLstInsightTypesDto(List<SelectValuesDto> lstInsightTypesDto) {
		this.lstInsightTypesDto = lstInsightTypesDto;
	}
	/**
	 * @return the lstSeveritiesDto
	 */
	public List<SelectValuesDto> getLstSeveritiesDto() {
		return lstSeveritiesDto;
	}
	/**
	 * @param lstSeveritiesDto the lstSeveritiesDto to set
	 */
	public void setLstSeveritiesDto(List<SelectValuesDto> lstSeveritiesDto) {
		this.lstSeveritiesDto = lstSeveritiesDto;
	}
	
	
	
}
