package org.bmj.userinsights.common.dto;

import java.io.Serializable;

public class SelectValuesDto implements Serializable{

	private static final long serialVersionUID = -3462157332392603651L;
	
	private Integer codeDecodedCode;
	private String codeDecodedName;
	
	
	

	public String getCodeDecodedName() {
		return codeDecodedName;
	}

	public void setCodeDecodedName(String codeDecodedName) {
		this.codeDecodedName = codeDecodedName;
	}

	public Integer getCodeDecodedCode() {
		return codeDecodedCode;
	}

	public void setCodeDecodedCode(Integer codeDecodedCode) {
		this.codeDecodedCode = codeDecodedCode;
	}

}
