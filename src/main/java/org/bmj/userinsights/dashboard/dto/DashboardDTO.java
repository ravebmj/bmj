package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;
import java.util.List;

import org.bmj.userinsights.dto.InsightDetailsDto;

public class DashboardDto implements Serializable {


	private static final long serialVersionUID = -8014402374004189946L;
	
	private List<InsightDetailsDto> recentInsightsLst;	// list conatins recently added insights
	private List<InsightDetailsDto> strongestEvidenceInsightLst; // this list contains strongest evidence insights
	private String insightId;
	private String bannerText;
	
	
	
	
	

	
	/**
	 * @return the bannerText
	 */
	public String getBannerText() {
		return bannerText;
	}
	/**
	 * @param bannerText the bannerText to set
	 */
	public void setBannerText(String bannerText) {
		this.bannerText = bannerText;
	}
	/**
	 * @return the insightId
	 */
	public String getInsightId() {
		return insightId;
	}
	/**
	 * @param insightId the insightId to set
	 */
	public void setInsightId(String insightId) {
		this.insightId = insightId;
	}
	/**
	 * @return the recentInsightsLst
	 */
	public List<InsightDetailsDto> getRecentInsightsLst() {
		return recentInsightsLst;
	}
	/**
	 * @param recentInsightsLst the recentInsightsLst to set
	 */
	public void setRecentInsightsLst(List<InsightDetailsDto> recentInsightsLst) {
		this.recentInsightsLst = recentInsightsLst;
	}
	/**
	 * @return the strongestEvidenceInsightLst
	 */
	public List<InsightDetailsDto> getStrongestEvidenceInsightLst() {
		return strongestEvidenceInsightLst;
	}
	/**
	 * @param strongestEvidenceInsightLst the strongestEvidenceInsightLst to set
	 */
	public void setStrongestEvidenceInsightLst(
			List<InsightDetailsDto> strongestEvidenceInsightLst) {
		this.strongestEvidenceInsightLst = strongestEvidenceInsightLst;
	}
	

}

