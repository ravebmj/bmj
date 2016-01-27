package org.bmj.userinsights.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.Tag;
/**
 * DTO for Tag entiry
 * @author nilesh.kambli
 *
 */
public class InsightTagDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private InsightDetail insightDetail;
	private Tag tag;
	private Integer addedUser;
	private Date addedDate;
	
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
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
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
