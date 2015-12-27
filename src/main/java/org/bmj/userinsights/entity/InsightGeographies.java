package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightGeographies  implements Serializable{
	
	private Integer id;
	private InsightDetail insightDetail;
	private Geographies geographies;
	private String insightGeographicValue;
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
	 * @return the insightGeographicValue
	 */
	public String getInsightGeographicValue() {
		return insightGeographicValue;
	}

	/**
	 * @param insightGeographicValue the insightGeographicValue to set
	 */
	public void setInsightGeographicValue(String insightGeographicValue) {
		this.insightGeographicValue = insightGeographicValue;
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
