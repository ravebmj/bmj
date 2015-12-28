package org.bmj.userinsights.search.dto;

import java.util.Date;
import java.util.List;

import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;

/*
 * Dto to display
 * search 
 */
public class SearchResultDetailDto {

	private Integer insightId;
	private String title;
	private String type;
	private List<String> product;
	private String projectName;
	private int user;
	//private List<String> tags;
	private String lastEditedDate;
	private Integer foundCount;
	
	private List<InsightProject> projects;
	private List<InsightProduct> products;
	private List<InsightTag> tags;
	
	/*private Integer insightId;
	private String insightName;
	private String projectName;
	private String type;
	private String lastEdited;
	private List<InsightProject> projects;*/
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getProduct() {
		return product;
	}
	public void setProduct(List<String> product) {
		this.product = product;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	public String getLastEditedDate() {
		return lastEditedDate;
	}
	public void setLastEditedDate(String lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}
	public List<InsightProject> getProjects() {
		return projects;
	}
	public void setProjects(List<InsightProject> projects) {
		this.projects = projects;
	}
	public Integer getInsightId() {
		return insightId;
	}
	public void setInsightId(Integer insightId) {
		this.insightId = insightId;
	}
	public Integer getFoundCount() {
		return foundCount;
	}
	public void setFoundCount(Integer foundCount) {
		this.foundCount = foundCount;
	}
	public List<InsightProduct> getProducts() {
		return products;
	}
	public void setProducts(List<InsightProduct> products) {
		this.products = products;
	}
	public List<InsightTag> getTags() {
		return tags;
	}
	public void setTags(List<InsightTag> tags) {
		this.tags = tags;
	}
		
	
}
