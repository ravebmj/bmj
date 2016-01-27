package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dto.InsightAttachmentDto;

public class AttachmentDto implements Serializable{
	private static final long serialVersionUID = 3179355302675422173L;
	List<String> lstErrMessages=new ArrayList<String>();
	private String errMessage="";
	private String fileAlreadyExist="";
	List<InsightAttachmentDto> lstInsightAttachmentDTO=new ArrayList<InsightAttachmentDto>();
	/**
	 * @return the lstErrMessages
	 */
	public List<String> getLstErrMessages() {
		return lstErrMessages;
	}
	/**
	 * @param lstErrMessages the lstErrMessages to set
	 */
	public void setLstErrMessages(List<String> lstErrMessages) {
		this.lstErrMessages = lstErrMessages;
	}
	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}
	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	/**
	 * @return the lstInsightAttachmentDTO
	 */
	public List<InsightAttachmentDto> getLstInsightAttachmentDTO() {
		return lstInsightAttachmentDTO;
	}
	/**
	 * @param lstInsightAttachmentDTO the lstInsightAttachmentDTO to set
	 */
	public void setLstInsightAttachmentDTO(
			List<InsightAttachmentDto> lstInsightAttachmentDTO) {
		this.lstInsightAttachmentDTO = lstInsightAttachmentDTO;
	}
	/**
	 * @return the fileAlreadyExist
	 */
	public String getFileAlreadyExist() {
		return fileAlreadyExist;
	}
	/**
	 * @param fileAlreadyExist the fileAlreadyExist to set
	 */
	public void setFileAlreadyExist(String fileAlreadyExist) {
		this.fileAlreadyExist = fileAlreadyExist;
	}

	
	
}
