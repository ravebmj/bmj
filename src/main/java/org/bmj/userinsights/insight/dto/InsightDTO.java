package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.Product;

public class InsightDTO implements Serializable{

	private static final long serialVersionUID = 6820198101454453382L;
	private Integer id;
	private String title;
	private String description;
	private String insightType;
	private Date foundDate;
	private Integer foundCount;
	private Integer insightServerity;
		
	private List<Product> productsList;
	private String products;
	
	private int addedUser;
	private Date addedDate;
	private int modifiedUser;
	private Date modifiedDate;
	
	private Set<InsightProduct> productsSet;

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

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
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

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
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


}
