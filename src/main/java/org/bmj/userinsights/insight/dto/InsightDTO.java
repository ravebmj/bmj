package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bmj.userinsights.entity.FoundVia;
import org.bmj.userinsights.entity.Geographies;
import org.bmj.userinsights.entity.InsightFoundVia;
import org.bmj.userinsights.entity.InsightGeographies;
import org.bmj.userinsights.entity.InsightMainUserType;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.MainUserType;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.entity.Project;
import org.bmj.userinsights.entity.Tag;

public class InsightDTO implements Serializable{

	private static final long serialVersionUID = 6820198101454453382L;
	private Integer id;
	private String title;
	private String description;
	private String insightType;
	private String foundDate;
	private Integer foundCount;
	private Integer insightServerity;
	private Integer insightApplicationID;
		
	private List<Product> productsList;
	private List<Project> projectsList;
	private List<Tag> tagList;
	private List<FoundVia> foundViaList;
	private List<MainUserType> mainUserTypeList;
	private List<Geographies> geographiesList;
	
	private Set<InsightProduct> productsSet;
	private Set<InsightProject> projects;
	private Set<InsightTag> tags;
	private Set<InsightFoundVia> foundVias;
	private Set<InsightMainUserType> mainUserTypes;
	private Set<InsightGeographies> geographies;
	private String products;
	
	private int addedUser;
	private String addedDate;
	private int modifiedUser;
	private Date modifiedDate;
	
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(int addedUser) {
		this.addedUser = addedUser;
	}

	
	public int getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(int modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getInsightType() {
		return insightType;
	}

	public void setInsightType(String insightType) {
		this.insightType = insightType;
	}

	public Set<InsightProduct> getProductsSet() {
		return productsSet;
	}

	public void setProductsSet(Set<InsightProduct> productsSet) {
		this.productsSet = productsSet;
	}

	
	public Integer getFoundCount() {
		return foundCount;
	}

	public void setFoundCount(Integer foundCount) {
		this.foundCount = foundCount;
	}

	public Integer getInsightServerity() {
		return insightServerity;
	}

	public void setInsightServerity(Integer insightServerity) {
		this.insightServerity = insightServerity;
	}

	
	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public Set<InsightProject> getProjects() {
		return projects;
	}

	public void setProjects(Set<InsightProject> projects) {
		this.projects = projects;
	}

	public Set<InsightTag> getTags() {
		return tags;
	}

	public void setTags(Set<InsightTag> tags) {
		this.tags = tags;
	}

	public Set<InsightFoundVia> getFoundVias() {
		return foundVias;
	}

	public void setFoundVias(Set<InsightFoundVia> foundVias) {
		this.foundVias = foundVias;
	}

	public Set<InsightMainUserType> getMainUserTypes() {
		return mainUserTypes;
	}

	public void setMainUserTypes(Set<InsightMainUserType> mainUserTypes) {
		this.mainUserTypes = mainUserTypes;
	}

	public Set<InsightGeographies> getGeographies() {
		return geographies;
	}

	public void setGeographies(Set<InsightGeographies> geographies) {
		this.geographies = geographies;
	}

	public Integer getInsightApplicationID() {
		return insightApplicationID;
	}

	public void setInsightApplicationID(Integer insightApplicationID) {
		this.insightApplicationID = insightApplicationID;
	}

	public List<Project> getProjectsList() {
		return projectsList;
	}

	public void setProjectsList(List<Project> projectsList) {
		this.projectsList = projectsList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<FoundVia> getFoundViaList() {
		return foundViaList;
	}

	public void setFoundViaList(List<FoundVia> foundViaList) {
		this.foundViaList = foundViaList;
	}

	public List<MainUserType> getMainUserTypeList() {
		return mainUserTypeList;
	}

	public void setMainUserTypeList(List<MainUserType> mainUserTypeList) {
		this.mainUserTypeList = mainUserTypeList;
	}

	public List<Geographies> getGeographiesList() {
		return geographiesList;
	}

	public void setGeographiesList(List<Geographies> geographiesList) {
		this.geographiesList = geographiesList;
	}

	public String getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}


}
