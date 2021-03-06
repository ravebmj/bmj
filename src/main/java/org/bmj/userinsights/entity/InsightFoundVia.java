package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightFoundVia  implements Serializable{
	
	
	private static final long serialVersionUID = 6542883351175376287L;
	private Integer id;
	private InsightDetail insightDetail;
	private FoundVia foundVia;;
	private String foundViaOtherValue;
	private Integer addedUser;
	private Date addedDate;
	
	public InsightFoundVia(){}

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
	 * @return the foundVia
	 */
	public FoundVia getFoundVia() {
		return foundVia;
	}

	/**
	 * @param foundVia the foundVia to set
	 */
	public void setFoundVia(FoundVia foundVia) {
		this.foundVia = foundVia;
	}

	/**
	 * @return the foundViaOtherValue
	 */
	public String getFoundViaOtherValue() {
		return foundViaOtherValue;
	}

	/**
	 * @param foundViaOtherValue the foundViaOtherValue to set
	 */
	public void setFoundViaOtherValue(String foundViaOtherValue) {
		this.foundViaOtherValue = foundViaOtherValue;
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
