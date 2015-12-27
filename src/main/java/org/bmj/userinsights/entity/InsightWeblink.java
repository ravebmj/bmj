package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightWeblink  implements Serializable{
	
	private Integer id;
	private Integer insightId;
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
	 * @return the insightId
	 */
	public Integer getInsightId() {
		return insightId;
	}

	/**
	 * @param insightId the insightId to set
	 */
	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
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
