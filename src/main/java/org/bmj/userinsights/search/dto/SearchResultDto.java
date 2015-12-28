package org.bmj.userinsights.search.dto;

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

public class SearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 2938054522288743030L;
	//List of search Detail
	List<SearchResultDetailDto> searchResult = new ArrayList<>();
	
	
	private String title;
	private String type;
	private List<String> product;
	private String projectName;
	private int user;
	private List<String> tags;
	private Date lastEditedDate;
	private String lastEdited;
	
	private List<SearchResultDetailDto> lstSearchResultDetailDto;
	
	
	
	/*private Integer insightId;
	private String insightName;
	private String projectName;
	private String type;
	private String lastEdited;
	private List<InsightProject> projects;*/
	

	public List<SearchResultDetailDto> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SearchResultDetailDto> searchResult) {
		this.searchResult = searchResult;
	}

	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getProduct() {
		return product;
	}

	public void setProduct(List<String> product) {
		this.product = product;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Date getLastEditedDate() {
		return lastEditedDate;
	}

	public void setLastEditedDate(Date lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}

	public String getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public List<SearchResultDetailDto> getLstSearchResultDetailDto() {
		return lstSearchResultDetailDto;
	}

	public void setLstSearchResultDetailDto(
			List<SearchResultDetailDto> lstSearchResultDetailDto) {
		this.lstSearchResultDetailDto = lstSearchResultDetailDto;
	}

	
	
	

}
