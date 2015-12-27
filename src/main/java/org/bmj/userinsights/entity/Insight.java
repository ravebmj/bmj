package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class Insight  implements Serializable{
	
	private Integer id;
	private String title;
	private String description;
	private Integer type;
	private Date insightFoundDate;
	private Integer insightFoundCount;
	private Integer sevirity;
	private Integer insightApplicationID;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the insightFoundDate
	 */
	public Date getInsightFoundDate() {
		return insightFoundDate;
	}
	/**
	 * @param insightFoundDate the insightFoundDate to set
	 */
	public void setInsightFoundDate(Date insightFoundDate) {
		this.insightFoundDate = insightFoundDate;
	}
	/**
	 * @return the insightFoundCount
	 */
	public Integer getInsightFoundCount() {
		return insightFoundCount;
	}
	/**
	 * @param insightFoundCount the insightFoundCount to set
	 */
	public void setInsightFoundCount(Integer insightFoundCount) {
		this.insightFoundCount = insightFoundCount;
	}
	/**
	 * @return the sevirity
	 */
	public Integer getSevirity() {
		return sevirity;
	}
	/**
	 * @param sevirity the sevirity to set
	 */
	public void setSevirity(Integer sevirity) {
		this.sevirity = sevirity;
	}
	/**
	 * @return the insightApplicationID
	 */
	public Integer getInsightApplicationID() {
		return insightApplicationID;
	}
	/**
	 * @param insightApplicationID the insightApplicationID to set
	 */
	public void setInsightApplicationID(Integer insightApplicationID) {
		this.insightApplicationID = insightApplicationID;
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
	/**
	 * @return the modifiedUser
	 */
	public Integer getModifiedUser() {
		return modifiedUser;
	}
	/**
	 * @param modifiedUser the modifiedUser to set
	 */
	public void setModifiedUser(Integer modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

}
