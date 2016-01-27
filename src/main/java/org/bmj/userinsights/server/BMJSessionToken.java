package org.bmj.userinsights.server;

import java.io.Serializable;
import java.util.Date;

public class BMJSessionToken implements Serializable{


private static final long serialVersionUID = 7150896430029541046L;
private Integer userId;
private String userName;
private String userFirstName;
private String userMiddleName;
private String userLastName;
private String userFullName;
private String userEmailAddress;
private Integer applicationId;
private Date addedDate;


/**
 * @return the userId
 */
public Integer getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(Integer userId) {
	this.userId = userId;
}
/**
 * @return the userName
 */
public String getUserName() {
	return userName;
}
/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}
/**
 * @return the userFirstName
 */
public String getUserFirstName() {
	return userFirstName;
}
/**
 * @param userFirstName the userFirstName to set
 */
public void setUserFirstName(String userFirstName) {
	this.userFirstName = userFirstName;
}
/**
 * @return the userMiddleName
 */
public String getUserMiddleName() {
	return userMiddleName;
}
/**
 * @param userMiddleName the userMiddleName to set
 */
public void setUserMiddleName(String userMiddleName) {
	this.userMiddleName = userMiddleName;
}
/**
 * @return the userLastName
 */
public String getUserLastName() {
	return userLastName;
}
/**
 * @param userLastName the userLastName to set
 */
public void setUserLastName(String userLastName) {
	this.userLastName = userLastName;
}
/**
 * @return the userFullName
 */
public String getUserFullName() {
	return userFullName;
}
/**
 * @param userFullName the userFullName to set
 */
public void setUserFullName(String userFullName) {
	this.userFullName = userFullName;
}
/**
 * @return the userEmailAddress
 */
public String getUserEmailAddress() {
	return userEmailAddress;
}
/**
 * @param userEmailAddress the userEmailAddress to set
 */
public void setUserEmailAddress(String userEmailAddress) {
	this.userEmailAddress = userEmailAddress;
}
/**
 * @return the applicationId
 */
public Integer getApplicationId() {
	return applicationId;
}
/**
 * @param applicationId the applicationId to set
 */
public void setApplicationId(Integer applicationId) {
	this.applicationId = applicationId;
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





}
