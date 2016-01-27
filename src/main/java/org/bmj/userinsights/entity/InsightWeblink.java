package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightWeblink  implements Serializable{
	
	
	private static final long serialVersionUID = 3107119055246544294L;
	private Integer id;
	private InsightDetail insightDetail;
	private String weblinkValue;
	private Integer addedUser;
	private Date addedDate;
	
	public InsightWeblink(){}

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
	 * @return the weblinkValue
	 */
	public String getWeblinkValue() {
		return weblinkValue;
	}

	/**
	 * @param weblinkValue the weblinkValue to set
	 */
	public void setWeblinkValue(String weblinkValue) {
		this.weblinkValue = weblinkValue;
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
