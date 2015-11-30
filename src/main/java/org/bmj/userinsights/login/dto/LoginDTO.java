package org.bmj.userinsights.login.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
	
	private static final long serialVersionUID = -1786871314851826595L;
	private String name;
	private String password;
	private String pageRequested;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPageRequested() {
		return pageRequested;
	}
	public void setPageRequested(String pageRequested) {
		this.pageRequested = pageRequested;
	}

}
