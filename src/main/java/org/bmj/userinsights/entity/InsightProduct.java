package org.bmj.userinsights.entity;

import java.util.Date;

public class InsightProduct {
	
	private int id;
	private int insightId;
	private int productId;
	private int addedUser;
	private Date addedDate;
	
	public InsightProduct(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInsightId() {
		return insightId;
	}

	public void setInsightId(int insightId) {
		this.insightId = insightId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
	
	

}
