package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeblinkDto implements Serializable{

	private static final long serialVersionUID = -7372760964696645016L;
	List<InsightWeblinkDto> lstInsightWeblinkDTO=new ArrayList<InsightWeblinkDto>();
	/**
	 * @return the lstInsightWeblinkDTO
	 */
	public List<InsightWeblinkDto> getLstInsightWeblinkDTO() {
		return lstInsightWeblinkDTO;
	}
	/**
	 * @param lstInsightWeblinkDTO the lstInsightWeblinkDTO to set
	 */
	public void setLstInsightWeblinkDTO(List<InsightWeblinkDto> lstInsightWeblinkDTO) {
		this.lstInsightWeblinkDTO = lstInsightWeblinkDTO;
	}
	
	
}
