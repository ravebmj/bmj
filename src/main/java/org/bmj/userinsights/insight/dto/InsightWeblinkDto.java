package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.Date;

public class InsightWeblinkDto  implements Serializable{
	
	
	private static final long serialVersionUID = 3107119055246544294L;
	private Integer id;
	private String weblinkValue;
	private Integer addedUser;
	private Date addedDate;
	
	
	private Integer insightId;
	
	private boolean flgDelete;// To check if URL is delete by user.
	private String webLinkUIId;//This timestamp base id which can use as primary key for UI level operation  
	
	public InsightWeblinkDto(){}

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

	public Integer getInsightId() {
		return insightId;
	}

	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
	}



	public String getWebLinkUIId() {
		return webLinkUIId;
	}

	public void setWebLinkUIId(String webLinkUIId) {
		this.webLinkUIId = webLinkUIId;
	}

	public boolean isFlgDelete() {
		return flgDelete;
	}

	public void setFlgDelete(boolean flgDelete) {
		this.flgDelete = flgDelete;
	}

	

}
