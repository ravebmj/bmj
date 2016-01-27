package org.bmj.userinsights.insight.dto;

import java.io.Serializable;

public class EditInsightDto implements Serializable{


	private static final long serialVersionUID = -2561220382618917887L;
	
	private Integer id;
	private String title;
	private String description;
	private Integer foundCount;
	private Integer oldFoundCount;// Hold value of "found count" in case of existing Insight.
	//Properties specific to Product for business and UI operation.
	private String strProducts; // This is bind with tag editor component for product value.(Which is being edited by user)
	private String strOldProducts; // This is old value (last saved database state) of product value.
	//Properties specific to Product for business and UI operation.(End)
	
	
	//Properties specific to Project for business and UI operation.(Start)
	private String strProjects; // This is bind with tag editor component for project value.(Which is being edited by user)
	private String strOldProjects;// This is old value (last saved database state) of project value.
	private String companyName; //Name of Company
	private Integer type; // Type of insight
	
	
	
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * @return the foundCount
	 */
	public Integer getFoundCount() {
		return foundCount;
	}
	/**
	 * @param foundCount the foundCount to set
	 */
	public void setFoundCount(Integer foundCount) {
		this.foundCount = foundCount;
	}
	/**
	 * @return the oldFoundCount
	 */
	public Integer getOldFoundCount() {
		return oldFoundCount;
	}
	/**
	 * @param oldFoundCount the oldFoundCount to set
	 */
	public void setOldFoundCount(Integer oldFoundCount) {
		this.oldFoundCount = oldFoundCount;
	}
	/**
	 * @return the strProducts
	 */
	public String getStrProducts() {
		return strProducts;
	}
	/**
	 * @param strProducts the strProducts to set
	 */
	public void setStrProducts(String strProducts) {
		this.strProducts = strProducts;
	}
	/**
	 * @return the strOldProducts
	 */
	public String getStrOldProducts() {
		return strOldProducts;
	}
	/**
	 * @param strOldProducts the strOldProducts to set
	 */
	public void setStrOldProducts(String strOldProducts) {
		this.strOldProducts = strOldProducts;
	}
	/**
	 * @return the strProjects
	 */
	public String getStrProjects() {
		return strProjects;
	}
	/**
	 * @param strProjects the strProjects to set
	 */
	public void setStrProjects(String strProjects) {
		this.strProjects = strProjects;
	}
	/**
	 * @return the strOldProjects
	 */
	public String getStrOldProjects() {
		return strOldProjects;
	}
	/**
	 * @param strOldProjects the strOldProjects to set
	 */
	public void setStrOldProjects(String strOldProjects) {
		this.strOldProjects = strOldProjects;
	}
	

}
