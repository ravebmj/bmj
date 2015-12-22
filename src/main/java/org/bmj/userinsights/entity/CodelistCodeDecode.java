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
	private Integer codelistStatus;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
	
	
	
	public Integer getCodeDecodeId() {
		return codeDecodeId;
	}
	public void setCodeDecodeId(Integer codeDecodeId) {
		this.codeDecodeId = codeDecodeId;
	}
	public Integer getCodeDecodeCode() {
		return codeDecodeCode;
	}
	public void setCodeDecodeCode(Integer codeDecodeCode) {
		this.codeDecodeCode = codeDecodeCode;
	}
	public String getCodeDecodeDecode() {
		return codeDecodeDecode;
	}
	public void setCodeDecodeDecode(String codeDecodeDecode) {
		this.codeDecodeDecode = codeDecodeDecode;
	}
	public Integer getCodelistId() {
		return codelistId;
	}
	public void setCodelistId(Integer codelistId) {
		this.codelistId = codelistId;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getCodelistStatus() {
		return codelistStatus;
	}
	public void setCodelistStatus(Integer codelistStatus) {
		this.codelistStatus = codelistStatus;
	}
	public Integer getAddedUser() {
		return addedUser;
	}
	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public Integer getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(Integer modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
