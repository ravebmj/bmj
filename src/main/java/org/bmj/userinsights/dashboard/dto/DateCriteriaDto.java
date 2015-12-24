package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;

public class DateCriteriaDto implements Serializable{

	private static final long serialVersionUID = -3397124262824386225L;
	
	private Integer criteriaId;
	private String criteriaName;
	
	
	public Integer getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(Integer criteriaId) {
		this.criteriaId = criteriaId;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

}
