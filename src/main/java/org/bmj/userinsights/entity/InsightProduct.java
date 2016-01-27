package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class InsightProduct  implements Serializable{
	
	
	private static final long serialVersionUID = -5255081806844517547L;
	private Integer id;	
	private InsightDetail insightDetail;
	private Product product;
	private Integer addedUser;
	private Date addedDate;
	public InsightProduct(){}
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	

}
