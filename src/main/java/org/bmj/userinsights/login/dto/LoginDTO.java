package org.bmj.userinsights.login.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
	
	private static final long serialVersionUID = -1786871314851826595L;
	private String name;
	private String password;
	private String pageRequested;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the pageRequested
	 */
	public String getPageRequested() {
		return pageRequested;
	}
	/**
	 * @param pageRequested the pageRequested to set
	 */
	public void setPageRequested(String pageRequested) {
		this.pageRequested = pageRequested;
	}
	

}
