package org.bmj.userinsights.dashboard.dto;

import java.io.Serializable;

public class SeveritiesDto implements Serializable{

	
	private static final long serialVersionUID = -1633753714359959585L;
	
	
	private Integer serverityId;
	private String serverityName;
	
	
	public Integer getServerityId() {
		return serverityId;
	}
	public void setServerityId(Integer serverityId) {
		this.serverityId = serverityId;
	}
	public String getServerityName() {
		return serverityName;
	}
	public void setServerityName(String serverityName) {
		this.serverityName = serverityName;
	}
	

}
