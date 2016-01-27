package org.bmj.userinsights.insight.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.FoundViaDto;
import org.bmj.userinsights.dto.GeographiesDto;
import org.bmj.userinsights.dto.InsightAttachmentDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.MainUserTypeDto;

public class InsightDto implements Serializable{

	private static final long serialVersionUID = 6820198101454453382L;
	
	InsightDetailsDto insightDetailsDto=new InsightDetailsDto();
	AttachmentDto attachmentDTO=new AttachmentDto();//Class to show UI layer data for attachment details. 
	WeblinkDto weblinkDTO=new WeblinkDto();//Class to show UI layer data for web links details. 
	
	//Properties specific to Found Via for business and UI operation.(Start)
	List<FoundViaDto> lstFoundViaDto=new ArrayList<FoundViaDto>();// Master list of Found Via.
	private String strFoundVia; // This is bind with multi-select component for Found Via value.(Which is being edited by user)
	private String strOldFoundVia; // This is old value (last saved database state) of Found Via value.
	ArrayList<String> newFoundVia=new ArrayList<String>();// List of Id of newly added Found Via from UI.
	ArrayList<String> delFoundVia=new ArrayList<String>();// List of Id of deleted Found Via from UI.
	private String idOtherFoundVia=""; // Id value from database for 'Other' option of table "FoundVia".
	private String lblFoundVia; // List of found via values need to be shown up side of found via dropdown.;
	//Properties specific to Found Via for business and UI operation.(End)
	
	//Properties specific to Main User Type for business and UI operation.(Start)
	List<MainUserTypeDto> lstMainUserTypeDto=new ArrayList<MainUserTypeDto>();// Master list of Main User Type.
	private String strMainUserType; // This is bind with multi-select component for Main User Type value.(Which is being edited by user)
	private String strOldMainUserType; // This is old value (last saved database state) of Main User Type value.
	ArrayList<String> newMainUserType=new ArrayList<String>();// List of Id of newly added Main User Type from UI.
	ArrayList<String> delMainUserType=new ArrayList<String>();// List of Id of deleted Main User Type from UI.
	private String idOtherMainUserType=""; // Id value from database for 'Other' option of table "MainUserType".
	private String lblMainUserType; // List of MainUserType values need to be shown up side of MainUserType dropdown.;
	//Properties specific to Main User Type for business and UI operation.(End)
	
	//Properties specific to Geographies for business and UI operation.(Start)
	List<GeographiesDto> lstGeographiesDto=new ArrayList<GeographiesDto>();// Master list of Main User Type.
	private String strGeographies; // This is bind with multi-select component for Geographies value.(Which is being edited by user)
	private String strOldGeographies; // This is old value (last saved database state) of Geographies value.
	ArrayList<String> newGeographies=new ArrayList<String>();// List of Id of newly added Geographies from UI.
	ArrayList<String> delGeographies=new ArrayList<String>();// List of Id of deleted Geographies from UI.
	private String idOtherGeographies=""; // Id value from database for 'Other' option of table "Geographies".
	private String lblGeographies; // List of Geographies values need to be shown up side of Geographies dropdown.;
	//Properties specific to Geographies for business and UI operation.(End)
	
	// Properties specific to UI Start
	
	//Properties specific to Product for business and UI operation.(Start)
	private String strProducts; // This is bind with tag editor component for product value.(Which is being edited by user)
	private String strOldProducts; // This is old value (last saved database state) of product value.
	ArrayList<String> newProdList=new ArrayList<String>();// List of name of newly added products.
	ArrayList<String> delProdList=new ArrayList<String>();// List of name of deleted products.
	//Properties specific to Product for business and UI operation.(End)
	
	
	//Properties specific to Project for business and UI operation.(Start)
	private String strProjects; // This is bind with tag editor component for project value.(Which is being edited by user)
	private String strOldProjects;// This is old value (last saved database state) of project value.
	ArrayList<String> newProjList=new ArrayList<String>();// List of name of newly added projects.
	ArrayList<String> delProjList=new ArrayList<String>();// List of name of deleted projects.
	//Properties specific to Project for business and UI operation.(End)
	
	//Properties specific to Tag for business and UI operation.(Start)
	private String strTags;// This is bind with tag editor component for tags value.(Which is being edited by user)
	private String strOldTags;// This is old value (last saved database state) of tag value.	
	ArrayList<String> newTagList=new ArrayList<String>();// List of name of newly added tags.
	ArrayList<String> delTagList=new ArrayList<String>();// List of name of deleted tags.
	//Properties specific to Tag for business and UI operation.(End)	
	
	// Master list of InsightType
	List<SelectValuesDto> lstInsightTypesDto =new ArrayList<SelectValuesDto>();	
	// Master list of severities
	List<SelectValuesDto> lstSeveritiesDto =new ArrayList<SelectValuesDto>();
	
	//start properties specific to business and UI(view) page
	private String strType;	//this is bind with user type
	private String strSeverity;	// to display severity value
	private String foundDate; // to display found date
	private String addedDate; // to display added date
	
	private String strAppliesto; // this is bind with applies to to display applies to values
	private String strGeographicalLoc; // this is bind with GeographicalLoc to display GeographicalLoc values
	private String strFoundVias; // this is bind with FoundVias to display FoundVias values
	private String emailAddress; // this is bind with emailAddress to display emailAddress values
	private String username;
	//end properties specific to business and UI(view) page
	
	List<InsightAttachmentDto> lstInsightAttachmentDTO=new ArrayList<InsightAttachmentDto>(); // this list conatins all the attachment details for this insight	
	private Integer userId;	
	private Integer oldFoundCount;// Hold value of "found count" in case of existing Insight.	
	private String msgSuccess="";

	/**
	 * @return the insightDetailsDto
	 */
	public InsightDetailsDto getInsightDetailsDto() {
		return insightDetailsDto;
	}

	/**
	 * @param insightDetailsDto the insightDetailsDto to set
	 */
	public void setInsightDetailsDto(InsightDetailsDto insightDetailsDto) {
		this.insightDetailsDto = insightDetailsDto;
	}

	/**
	 * @return the attachmentDTO
	 */
	public AttachmentDto getAttachmentDTO() {
		return attachmentDTO;
	}

	/**
	 * @param attachmentDTO the attachmentDTO to set
	 */
	public void setAttachmentDTO(AttachmentDto attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}

	/**
	 * @return the weblinkDTO
	 */
	public WeblinkDto getWeblinkDTO() {
		return weblinkDTO;
	}

	/**
	 * @param weblinkDTO the weblinkDTO to set
	 */
	public void setWeblinkDTO(WeblinkDto weblinkDTO) {
		this.weblinkDTO = weblinkDTO;
	}

	/**
	 * @return the lstFoundViaDto
	 */
	public List<FoundViaDto> getLstFoundViaDto() {
		return lstFoundViaDto;
	}

	/**
	 * @param lstFoundViaDto the lstFoundViaDto to set
	 */
	public void setLstFoundViaDto(List<FoundViaDto> lstFoundViaDto) {
		this.lstFoundViaDto = lstFoundViaDto;
	}

	/**
	 * @return the strFoundVia
	 */
	public String getStrFoundVia() {
		return strFoundVia;
	}

	/**
	 * @param strFoundVia the strFoundVia to set
	 */
	public void setStrFoundVia(String strFoundVia) {
		this.strFoundVia = strFoundVia;
	}

	/**
	 * @return the strOldFoundVia
	 */
	public String getStrOldFoundVia() {
		return strOldFoundVia;
	}

	/**
	 * @param strOldFoundVia the strOldFoundVia to set
	 */
	public void setStrOldFoundVia(String strOldFoundVia) {
		this.strOldFoundVia = strOldFoundVia;
	}

	/**
	 * @return the newFoundVia
	 */
	public ArrayList<String> getNewFoundVia() {
		return newFoundVia;
	}

	/**
	 * @param newFoundVia the newFoundVia to set
	 */
	public void setNewFoundVia(ArrayList<String> newFoundVia) {
		this.newFoundVia = newFoundVia;
	}

	/**
	 * @return the delFoundVia
	 */
	public ArrayList<String> getDelFoundVia() {
		return delFoundVia;
	}

	/**
	 * @param delFoundVia the delFoundVia to set
	 */
	public void setDelFoundVia(ArrayList<String> delFoundVia) {
		this.delFoundVia = delFoundVia;
	}

	/**
	 * @return the idOtherFoundVia
	 */
	public String getIdOtherFoundVia() {
		return idOtherFoundVia;
	}

	/**
	 * @param idOtherFoundVia the idOtherFoundVia to set
	 */
	public void setIdOtherFoundVia(String idOtherFoundVia) {
		this.idOtherFoundVia = idOtherFoundVia;
	}

	/**
	 * @return the lblFoundVia
	 */
	public String getLblFoundVia() {
		return lblFoundVia;
	}

	/**
	 * @param lblFoundVia the lblFoundVia to set
	 */
	public void setLblFoundVia(String lblFoundVia) {
		this.lblFoundVia = lblFoundVia;
	}

	/**
	 * @return the lstMainUserTypeDto
	 */
	public List<MainUserTypeDto> getLstMainUserTypeDto() {
		return lstMainUserTypeDto;
	}

	/**
	 * @param lstMainUserTypeDto the lstMainUserTypeDto to set
	 */
	public void setLstMainUserTypeDto(List<MainUserTypeDto> lstMainUserTypeDto) {
		this.lstMainUserTypeDto = lstMainUserTypeDto;
	}

	/**
	 * @return the strMainUserType
	 */
	public String getStrMainUserType() {
		return strMainUserType;
	}

	/**
	 * @param strMainUserType the strMainUserType to set
	 */
	public void setStrMainUserType(String strMainUserType) {
		this.strMainUserType = strMainUserType;
	}

	/**
	 * @return the strOldMainUserType
	 */
	public String getStrOldMainUserType() {
		return strOldMainUserType;
	}

	/**
	 * @param strOldMainUserType the strOldMainUserType to set
	 */
	public void setStrOldMainUserType(String strOldMainUserType) {
		this.strOldMainUserType = strOldMainUserType;
	}

	/**
	 * @return the newMainUserType
	 */
	public ArrayList<String> getNewMainUserType() {
		return newMainUserType;
	}

	/**
	 * @param newMainUserType the newMainUserType to set
	 */
	public void setNewMainUserType(ArrayList<String> newMainUserType) {
		this.newMainUserType = newMainUserType;
	}

	/**
	 * @return the delMainUserType
	 */
	public ArrayList<String> getDelMainUserType() {
		return delMainUserType;
	}

	/**
	 * @param delMainUserType the delMainUserType to set
	 */
	public void setDelMainUserType(ArrayList<String> delMainUserType) {
		this.delMainUserType = delMainUserType;
	}

	/**
	 * @return the idOtherMainUserType
	 */
	public String getIdOtherMainUserType() {
		return idOtherMainUserType;
	}

	/**
	 * @param idOtherMainUserType the idOtherMainUserType to set
	 */
	public void setIdOtherMainUserType(String idOtherMainUserType) {
		this.idOtherMainUserType = idOtherMainUserType;
	}

	/**
	 * @return the lblMainUserType
	 */
	public String getLblMainUserType() {
		return lblMainUserType;
	}

	/**
	 * @param lblMainUserType the lblMainUserType to set
	 */
	public void setLblMainUserType(String lblMainUserType) {
		this.lblMainUserType = lblMainUserType;
	}

	/**
	 * @return the lstGeographiesDto
	 */
	public List<GeographiesDto> getLstGeographiesDto() {
		return lstGeographiesDto;
	}

	/**
	 * @param lstGeographiesDto the lstGeographiesDto to set
	 */
	public void setLstGeographiesDto(List<GeographiesDto> lstGeographiesDto) {
		this.lstGeographiesDto = lstGeographiesDto;
	}

	/**
	 * @return the strGeographies
	 */
	public String getStrGeographies() {
		return strGeographies;
	}

	/**
	 * @param strGeographies the strGeographies to set
	 */
	public void setStrGeographies(String strGeographies) {
		this.strGeographies = strGeographies;
	}

	/**
	 * @return the strOldGeographies
	 */
	public String getStrOldGeographies() {
		return strOldGeographies;
	}

	/**
	 * @param strOldGeographies the strOldGeographies to set
	 */
	public void setStrOldGeographies(String strOldGeographies) {
		this.strOldGeographies = strOldGeographies;
	}

	/**
	 * @return the newGeographies
	 */
	public ArrayList<String> getNewGeographies() {
		return newGeographies;
	}

	/**
	 * @param newGeographies the newGeographies to set
	 */
	public void setNewGeographies(ArrayList<String> newGeographies) {
		this.newGeographies = newGeographies;
	}

	/**
	 * @return the delGeographies
	 */
	public ArrayList<String> getDelGeographies() {
		return delGeographies;
	}

	/**
	 * @param delGeographies the delGeographies to set
	 */
	public void setDelGeographies(ArrayList<String> delGeographies) {
		this.delGeographies = delGeographies;
	}

	/**
	 * @return the idOtherGeographies
	 */
	public String getIdOtherGeographies() {
		return idOtherGeographies;
	}

	/**
	 * @param idOtherGeographies the idOtherGeographies to set
	 */
	public void setIdOtherGeographies(String idOtherGeographies) {
		this.idOtherGeographies = idOtherGeographies;
	}

	/**
	 * @return the lblGeographies
	 */
	public String getLblGeographies() {
		return lblGeographies;
	}

	/**
	 * @param lblGeographies the lblGeographies to set
	 */
	public void setLblGeographies(String lblGeographies) {
		this.lblGeographies = lblGeographies;
	}

	/**
	 * @return the strProducts
	 */
	public String getStrProducts() {
		return strProducts;
	}

	/**
	 * @param strProducts the strProducts to set
	 */
	public void setStrProducts(String strProducts) {
		this.strProducts = strProducts;
	}

	/**
	 * @return the strOldProducts
	 */
	public String getStrOldProducts() {
		return strOldProducts;
	}

	/**
	 * @param strOldProducts the strOldProducts to set
	 */
	public void setStrOldProducts(String strOldProducts) {
		this.strOldProducts = strOldProducts;
	}

	/**
	 * @return the newProdList
	 */
	public ArrayList<String> getNewProdList() {
		return newProdList;
	}

	/**
	 * @param newProdList the newProdList to set
	 */
	public void setNewProdList(ArrayList<String> newProdList) {
		this.newProdList = newProdList;
	}

	/**
	 * @return the delProdList
	 */
	public ArrayList<String> getDelProdList() {
		return delProdList;
	}

	/**
	 * @param delProdList the delProdList to set
	 */
	public void setDelProdList(ArrayList<String> delProdList) {
		this.delProdList = delProdList;
	}

	/**
	 * @return the strProjects
	 */
	public String getStrProjects() {
		return strProjects;
	}

	/**
	 * @param strProjects the strProjects to set
	 */
	public void setStrProjects(String strProjects) {
		this.strProjects = strProjects;
	}

	/**
	 * @return the strOldProjects
	 */
	public String getStrOldProjects() {
		return strOldProjects;
	}

	/**
	 * @param strOldProjects the strOldProjects to set
	 */
	public void setStrOldProjects(String strOldProjects) {
		this.strOldProjects = strOldProjects;
	}

	/**
	 * @return the newProjList
	 */
	public ArrayList<String> getNewProjList() {
		return newProjList;
	}

	/**
	 * @param newProjList the newProjList to set
	 */
	public void setNewProjList(ArrayList<String> newProjList) {
		this.newProjList = newProjList;
	}

	/**
	 * @return the delProjList
	 */
	public ArrayList<String> getDelProjList() {
		return delProjList;
	}

	/**
	 * @param delProjList the delProjList to set
	 */
	public void setDelProjList(ArrayList<String> delProjList) {
		this.delProjList = delProjList;
	}

	/**
	 * @return the strTags
	 */
	public String getStrTags() {
		return strTags;
	}

	/**
	 * @param strTags the strTags to set
	 */
	public void setStrTags(String strTags) {
		this.strTags = strTags;
	}

	/**
	 * @return the strOldTags
	 */
	public String getStrOldTags() {
		return strOldTags;
	}

	/**
	 * @param strOldTags the strOldTags to set
	 */
	public void setStrOldTags(String strOldTags) {
		this.strOldTags = strOldTags;
	}

	/**
	 * @return the newTagList
	 */
	public ArrayList<String> getNewTagList() {
		return newTagList;
	}

	/**
	 * @param newTagList the newTagList to set
	 */
	public void setNewTagList(ArrayList<String> newTagList) {
		this.newTagList = newTagList;
	}

	/**
	 * @return the delTagList
	 */
	public ArrayList<String> getDelTagList() {
		return delTagList;
	}

	/**
	 * @param delTagList the delTagList to set
	 */
	public void setDelTagList(ArrayList<String> delTagList) {
		this.delTagList = delTagList;
	}

	/**
	 * @return the lstInsightTypesDto
	 */
	public List<SelectValuesDto> getLstInsightTypesDto() {
		return lstInsightTypesDto;
	}

	/**
	 * @param lstInsightTypesDto the lstInsightTypesDto to set
	 */
	public void setLstInsightTypesDto(List<SelectValuesDto> lstInsightTypesDto) {
		this.lstInsightTypesDto = lstInsightTypesDto;
	}

	/**
	 * @return the lstSeveritiesDto
	 */
	public List<SelectValuesDto> getLstSeveritiesDto() {
		return lstSeveritiesDto;
	}

	/**
	 * @param lstSeveritiesDto the lstSeveritiesDto to set
	 */
	public void setLstSeveritiesDto(List<SelectValuesDto> lstSeveritiesDto) {
		this.lstSeveritiesDto = lstSeveritiesDto;
	}

	/**
	 * @return the strType
	 */
	public String getStrType() {
		return strType;
	}

	/**
	 * @param strType the strType to set
	 */
	public void setStrType(String strType) {
		this.strType = strType;
	}

	/**
	 * @return the strSeverity
	 */
	public String getStrSeverity() {
		return strSeverity;
	}

	/**
	 * @param strSeverity the strSeverity to set
	 */
	public void setStrSeverity(String strSeverity) {
		this.strSeverity = strSeverity;
	}

	/**
	 * @return the foundDate
	 */
	public String getFoundDate() {
		return foundDate;
	}

	/**
	 * @param foundDate the foundDate to set
	 */
	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}

	/**
	 * @return the addedDate
	 */
	public String getAddedDate() throws Exception{
		this.setAddedDate(CommonUtils.getDDMMMYYYY(insightDetailsDto.getAddedDate()));
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the strAppliesto
	 */
	public String getStrAppliesto() {
		return strAppliesto;
	}

	/**
	 * @param strAppliesto the strAppliesto to set
	 */
	public void setStrAppliesto(String strAppliesto) {
		this.strAppliesto = strAppliesto;
	}

	/**
	 * @return the strGeographicalLoc
	 */
	public String getStrGeographicalLoc() {
		return strGeographicalLoc;
	}

	/**
	 * @param strGeographicalLoc the strGeographicalLoc to set
	 */
	public void setStrGeographicalLoc(String strGeographicalLoc) {
		this.strGeographicalLoc = strGeographicalLoc;
	}

	/**
	 * @return the strFoundVias
	 */
	public String getStrFoundVias() {
		return strFoundVias;
	}

	/**
	 * @param strFoundVias the strFoundVias to set
	 */
	public void setStrFoundVias(String strFoundVias) {
		this.strFoundVias = strFoundVias;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the oldFoundCount
	 */
	public Integer getOldFoundCount() {
		return oldFoundCount;
	}

	/**
	 * @param oldFoundCount the oldFoundCount to set
	 */
	public void setOldFoundCount(Integer oldFoundCount) {
		this.oldFoundCount = oldFoundCount;
	}

	/**
	 * @return the msgSuccess
	 */
	public String getMsgSuccess() {
		return msgSuccess;
	}

	/**
	 * @param msgSuccess the msgSuccess to set
	 */
	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}
	
	
	
}
