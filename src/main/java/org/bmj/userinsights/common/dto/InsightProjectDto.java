package org.bmj.userinsights.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.Project;
/**
 * DTO for Project entity
 * @author nilesh.kambli
 *
 */
public class InsightProjectDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private InsightDetail insightDetail;
	private Project project;
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
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
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
