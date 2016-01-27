package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InsightDetail  implements Serializable{

	
	private static final long serialVersionUID = -5119035759859846280L;
	private Integer id;
	private String title;
	private String description;
	private String plainDescription;
	private Integer type;
	private Date foundDate;
	private Integer foundCount;
	private Integer insightServerity;
	private Integer insightApplicationID;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
	private String companyName;
	private Set<InsightProduct> products = new HashSet<>();
	private Set<InsightProject> projects= new HashSet<>();
	private Set<InsightTag> tags= new HashSet<>();
	private Set<InsightFoundVia> foundVias= new HashSet<>();
	private Set<InsightMainUserType> mainUserTypes= new HashSet<>();
	private Set<InsightGeographies> geographies= new HashSet<>();
	private Set<InsightWeblink> weblinks= new HashSet<>();
	private Set<InsightAttachment> attachments= new HashSet<>();
	
	
	public InsightDetail(){}

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
	 * @return the foundDate
	 */
	public Date getFoundDate() {
		return foundDate;
	}

	/**
	 * @param foundDate the foundDate to set
	 */
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
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
	 * @return the insightServerity
	 */
	public Integer getInsightServerity() {
		return insightServerity;
	}

	/**
	 * @param insightServerity the insightServerity to set
	 */
	public void setInsightServerity(Integer insightServerity) {
		this.insightServerity = insightServerity;
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

	/**
	 * @return the products
	 */
	public Set<InsightProduct> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Set<InsightProduct> products) {
		this.products = products;
	}

	/**
	 * @return the projects
	 */
	public Set<InsightProject> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<InsightProject> projects) {
		this.projects = projects;
	}

	/**
	 * @return the tags
	 */
	public Set<InsightTag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<InsightTag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the foundVias
	 */
	public Set<InsightFoundVia> getFoundVias() {
		return foundVias;
	}

	/**
	 * @param foundVias the foundVias to set
	 */
	public void setFoundVias(Set<InsightFoundVia> foundVias) {
		this.foundVias = foundVias;
	}

	/**
	 * @return the mainUserTypes
	 */
	public Set<InsightMainUserType> getMainUserTypes() {
		return mainUserTypes;
	}

	/**
	 * @param mainUserTypes the mainUserTypes to set
	 */
	public void setMainUserTypes(Set<InsightMainUserType> mainUserTypes) {
		this.mainUserTypes = mainUserTypes;
	}

	/**
	 * @return the geographies
	 */
	public Set<InsightGeographies> getGeographies() {
		return geographies;
	}

	/**
	 * @param geographies the geographies to set
	 */
	public void setGeographies(Set<InsightGeographies> geographies) {
		this.geographies = geographies;
	}

	/**
	 * @return the weblinks
	 */
	public Set<InsightWeblink> getWeblinks() {
		return weblinks;
	}

	/**
	 * @param weblinks the weblinks to set
	 */
	public void setWeblinks(Set<InsightWeblink> weblinks) {
		this.weblinks = weblinks;
	}

	/**
	 * @return the attachments
	 */
	public Set<InsightAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Set<InsightAttachment> attachments) {
		this.attachments = attachments;
	}

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
	 * @return the plainDescription
	 */
	public String getPlainDescription() {
		return plainDescription;
	}

	/**
	 * @param plainDescription the plainDescription to set
	 */
	public void setPlainDescription(String plainDescription) {
		this.plainDescription = plainDescription;
	}

	
	
	
}
