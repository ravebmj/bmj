package org.bmj.userinsights.common.dto;

import java.io.Serializable;
/** 
 *This Dto class will hold the codelist id,name and display order for insight types,severities and datecriteria dropdowns
 */
public class SelectValuesDto implements Serializable{

	private static final long serialVersionUID = -3462157332392603651L;
	/**
	 * This property for to hold codelist id
	 */
	private Integer codeDecodedCode;
	/**
	 * to hold types,severities and datecriteria dropdowns values
	 */
	private String codeDecodedName;
	/**
	 * 
	 */
	private Integer displayOrder;
	
	
	
	
	
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
	 * @return the codeDecodedCode
	 */
	public Integer getCodeDecodedCode() {
		return codeDecodedCode;
	}
	/**
	 * @param codeDecodedCode the codeDecodedCode to set
	 */
	public void setCodeDecodedCode(Integer codeDecodedCode) {
		this.codeDecodedCode = codeDecodedCode;
	}
	/**
	 * @return the codeDecodedName
	 */
	public String getCodeDecodedName() {
		return codeDecodedName;
	}
	/**
	 * @param codeDecodedName the codeDecodedName to set
	 */
	public void setCodeDecodedName(String codeDecodedName) {
		this.codeDecodedName = codeDecodedName;
	}
	
	
}
