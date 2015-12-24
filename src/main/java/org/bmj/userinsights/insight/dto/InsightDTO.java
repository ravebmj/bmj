package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class InsightDTO implements Serializable{

	private static final long serialVersionUID = 6820198101454453382L;
	private Integer id;
	private String title;
	private String description;
	
	private ArrayList product;
	private String products;

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


}
