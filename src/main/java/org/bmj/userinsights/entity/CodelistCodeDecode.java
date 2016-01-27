package org.bmj.userinsights.entity;

import java.io.Serializable;
import java.util.Date;

public class CodelistCodeDecode implements Serializable{

	private static final long serialVersionUID = 531374466973275887L;
	
	private Integer codeDecodeId;
	private Integer codeDecodeCode;
	private String codeDecodeDecode;
	private Integer codelistId;
	private Integer displayOrder;
	private Integer addedUser;
	private Date addedDate;
	/**
	 * @return the codeDecodeId
	 */
	public Integer getCodeDecodeId() {
		return codeDecodeId;
	}
	/**
	 * @param codeDecodeId the codeDecodeId to set
	 */
	public void setCodeDecodeId(Integer codeDecodeId) {
		this.codeDecodeId = codeDecodeId;
	}
	/**
	 * @return the codeDecodeCode
	 */
	public Integer getCodeDecodeCode() {
		return codeDecodeCode;
	}
	/**
	 * @param codeDecodeCode the codeDecodeCode to set
	 */
	public void setCodeDecodeCode(Integer codeDecodeCode) {
		this.codeDecodeCode = codeDecodeCode;
	}
	/**
	 * @return the codeDecodeDecode
	 */
	public String getCodeDecodeDecode() {
		return codeDecodeDecode;
	}
	/**
	 * @param codeDecodeDecode the codeDecodeDecode to set
	 */
	public void setCodeDecodeDecode(String codeDecodeDecode) {
		this.codeDecodeDecode = codeDecodeDecode;
	}
	/**
	 * @return the codelistId
	 */
	public Integer getCodelistId() {
		return codelistId;
	}
	/**
	 * @param codelistId the codelistId to set
	 */
	public void setCodelistId(Integer codelistId) {
		this.codelistId = codelistId;
	}
	/**
	 * @return the displayOrder
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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
		
}
