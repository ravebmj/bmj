package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightGeographies  implements Serializable{
	
	private Integer id;
	private InsightDetail insightDetail;
	private Geographies geographies;
	private String insightGeographicOtherValue;
	private Integer addedUser;
	private Date addedDate;

	public InsightGeographies(){}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the insightDetail
	 */
	public InsightDetail getInsightDetail() {
		return insightDetail;
	}

	/**
	 * @param insightDetail the insightDetail to set
	 */
	public void setInsightDetail(InsightDetail insightDetail) {
		this.insightDetail = insightDetail;
	}

	/**
	 * @return the geographies
	 */
	public Geographies getGeographies() {
		return geographies;
	}

	/**
	 * @param geographies the geographies to set
	 */
	public void setGeographies(Geographies geographies) {
		this.geographies = geographies;
	}

	/**
	 * @return the insightGeographicOtherValue
	 */
	public String getInsightGeographicOtherValue() {
		return insightGeographicOtherValue;
	}

	/**
	 * @param insightGeographicOtherValue the insightGeographicOtherValue to set
	 */
	public void setInsightGeographicOtherValue(String insightGeographicOtherValue) {
		this.insightGeographicOtherValue = insightGeographicOtherValue;
	}

	/**
	 * @return the addedUser
	 */
	public Integer getAddedUser() {
		return addedUser;
	}

	/**
	 * @param addedUser the addedUser to set
	 */
	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
	}

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	
	
	
}
