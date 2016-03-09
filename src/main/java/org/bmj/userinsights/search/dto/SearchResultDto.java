package org.bmj.userinsights.search.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;

public class SearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 2938054522288743030L;
		
	List<InsightDetailsDto> searchResult = new ArrayList<>();// this list contains all the search results related to the searchcriteria
	
	// start - these properties are specific to reset quick search or advance search options
	private String sortFlag; // use this flag on jsp to differentiate view all
	private String keyword; //to hold quich search text box value
	private String insightType; // to hold insight type
	private String serverity; // to hold severity value
	private String dateRangeOpt; // to hold datecriteria value
	private String fromDate; // to hold from date
	private String toDate; // to hold to date
	private String serachType; // this property hold value and used for to differentiate which search button clicked.
	private String weightageSortFlag;
	// end - these properties are specific to reset quick search or advance search options
	/**
	 * @return the searchResult
	 */
	public List<InsightDetailsDto> getSearchResult() {
		return searchResult;
	}
	/**
	 * @param searchResult the searchResult to set
	 */
	public void setSearchResult(List<InsightDetailsDto> searchResult) {
		this.searchResult = searchResult;
	}
	
	/**
	 * @return the sortFlag
	 */
	public String getSortFlag() {
		return sortFlag;
	}
	/**
	 * @param sortFlag the sortFlag to set
	 */
	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}
	
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
	 * @return the dateRangeOpt
	 */
	public String getDateRangeOpt() {
		return dateRangeOpt;
	}
	/**
	 * @param dateRangeOpt the dateRangeOpt to set
	 */
	public void setDateRangeOpt(String dateRangeOpt) {
		this.dateRangeOpt = dateRangeOpt;
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
	 * @return the serachType
	 */
	public String getSerachType() {
		return serachType;
	}
	/**
	 * @param serachType the serachType to set
	 */
	public void setSerachType(String serachType) {
		this.serachType = serachType;
	}
	/**
	 * @return the weightageSortFlag
	 */
	public String getWeightageSortFlag() {
		return weightageSortFlag;
	}
	/**
	 * @param weightageSortFlag the weightageSortFlag to set
	 */
	public void setWeightageSortFlag(String weightageSortFlag) {
		this.weightageSortFlag = weightageSortFlag;
	}
	
	

	
}
