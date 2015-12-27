package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class InsightDTO implements Serializable{

	private static final long serialVersionUID = 6820198101454453382L;
	private Integer id;
	private String title;
	private String description;
	private String insightType;
	
	private ArrayList product;
	private String products;
	
	private int addedUser;
	private Date addedDate;
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

	public ArrayList getProduct() {
		return product;
	}

	public void setProduct(ArrayList product) {
		this.product = product;
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


}
