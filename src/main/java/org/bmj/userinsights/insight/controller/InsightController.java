package org.bmj.userinsights.insight.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.AdminUserDto;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.FoundViaDto;
import org.bmj.userinsights.dto.GeographiesDto;
import org.bmj.userinsights.dto.InsightAttachmentDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.MainUserTypeDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.dto.ProjectDto;
import org.bmj.userinsights.dto.TagDto;
import org.bmj.userinsights.dto.TagEditorJson;
import org.bmj.userinsights.entity.InsightFoundVia;
import org.bmj.userinsights.entity.InsightGeographies;
import org.bmj.userinsights.entity.InsightMainUserType;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.InsightWeblink;
import org.bmj.userinsights.insight.dto.EditInsightDto;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.dto.InsightWeblinkDto;
import org.bmj.userinsights.insight.dto.WeblinkDto;
import org.bmj.userinsights.insight.service.IInsightService;
import org.bmj.userinsights.search.dto.SearchCriteriaDto;
import org.bmj.userinsights.server.BMJSessionToken;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
 * This class is handling all operation
 * related to insights creation/edit.
 */
@SessionAttributes( value = {"mInsightDTO","searchCriteria"})
@Controller
public class InsightController {
	private static final  Logger log = Logger.getLogger(InsightController.class);

	@Autowired
	private IInsightService insightService; 

	 ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");

	 /**
		 * Display create insight page
		 * @param request
		 * @return
		 */
	@RequestMapping(value="/createinsight",method=RequestMethod.GET)  
	public ModelAndView createInsight(HttpServletRequest request) throws Exception {
		log.info("in the createInsight insight page");
		String googleSession=request.getParameter("googleSession");
		if(googleSession==null || (googleSession!=null && !googleSession.equalsIgnoreCase("true"))){// If value of this parameter is "true" then google signin is already exist.
			ModelAndView mav = null;
			request.setAttribute("pageType", "createnew");// This variable decides final page for navigation after successful google signin.
			
			//Navigate to the intermediate page which checks google session. If google session not exist then navigate to BMJ google login. If exist then
			//navigate to page which is based on "pageType" variable.
			mav = new ModelAndView("sessionCheck");	
			return mav;
		}
		
		
		HttpSession session = request.getSession();		
		if(session != null && session.getAttribute("BMJSessionToken")!=null){
			resetSessionData(request);
			String fromSave=request.getParameter("fromSave");
			InsightDto insightDTO=new InsightDto();
			insightDTO.getInsightDetailsDto().setId(0);// New Insight
			try{
				if(fromSave!=null && fromSave.equalsIgnoreCase("true")){
					insightDTO.setMsgSuccess("Insight has been saved successfully.");
				}
				insightDTO.setLstFoundViaDto(insightService.getFoundViaDetails()); // Populate master list of "Found Via"
				Collections.sort(insightDTO.getLstFoundViaDto());
				insightDTO.setLstMainUserTypeDto(insightService.getMainUserTypeDetails()); // Populate master list of "Main User Type"
				Collections.sort(insightDTO.getLstMainUserTypeDto());
				insightDTO.setLstGeographiesDto(insightService.getGeographiesDetails()); // Populate master list of "Geographies"
				Collections.sort(insightDTO.getLstGeographiesDto());
				setOtherId(insightDTO);
				setMasterData(insightDTO);
				setWebLinkInSession(request, insightDTO);
			}catch(Exception e){
				CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_create"));
			}
			return new ModelAndView("createInsight","mInsightDTO",insightDTO);
		}else{
			log.info("in the createInsight insight page if the BMJSessionToken is null");				
			ModelAndView mav = new ModelAndView("redirectcreate");				
			return mav;
		}
	}
	
	 /**
	 * Display data of
	 * particular insight in 
	 * view mode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewinsight")  
	public ModelAndView viewInsight(HttpServletRequest request, 
			@RequestParam("insightId") String insightId) throws Exception {
		
		//In case of book mark page (view insight), we need to track page type and insight id after successful google login.
		 // so setting below pageType,insightId.
		request.setAttribute("pageType", "viewinsight");
		request.setAttribute("insightId", insightId);
		String googleSession=request.getParameter("googleSession");
		if(googleSession==null || (googleSession!=null && !googleSession.equalsIgnoreCase("true"))){// If value of this parameter is "true" then google signin is already exist.
			ModelAndView mav = null;
			request.setAttribute("pageType", "viewinsight");// This variable decides final page for navigation after successful google signin.
			request.setAttribute("insightId", insightId);
			
			//Navigate to the intermediate page which checks google session. If google session not exist then navigate to BMJ google login. If exist then
			//navigate to page which is based on "pageType" variable.
			mav = new ModelAndView("sessionCheck");	
			return mav;
		}
		
		log.info("starting of the viewInsight method in InsightController");
		List<InsightAttachmentDto> uploadedFiles = new ArrayList<InsightAttachmentDto>();
		String fromSave=request.getParameter("fromSave");
		ModelAndView mav = new ModelAndView("viewInsight");	
		try{
			List<InsightDto> insightDtoList = insightService.getInsightDetails(insightId);//to get insight details
			InsightDto insightDTO = insightDtoList.get(0);			
			List<AdminUserDto> adminUserDTOList = insightService.getadminUserDetails(insightDTO.getInsightDetailsDto().getAddedUser()); //to get email address of insight added user
			log.debug("adminUserDTOList size"+adminUserDTOList.size());
			AdminUserDto adminUserDTO = adminUserDTOList.get(0);
			uploadedFiles = insightDTO.getAttachmentDTO().getLstInsightAttachmentDTO();
			log.debug("uploadedFiles size"+uploadedFiles.size());
			insightDTO.setLstInsightAttachmentDTO(uploadedFiles);			
			setFormattedWeblinks(insightDTO);
			insightDTO.setStrGeographicalLoc(getGeographiesListNames(insightDTO));
			insightDTO.setStrAppliesto(getMainUserTypeListNames(insightDTO));
			insightDTO.setStrFoundVias(getFoundViasListNames(insightDTO));
			insightDTO.setEmailAddress(adminUserDTO.getAdminuserEmailAddress());
			insightDTO.setUsername(adminUserDTO.getAdminuserFirstName()+" "+adminUserDTO.getAdminuserLastName());
			insightDTO.getInsightDetailsDto().setDescription(StringEscapeUtils.unescapeHtml(insightDTO.getInsightDetailsDto().getDescription()));
			
			if(fromSave!=null && fromSave.equalsIgnoreCase("true")){
				insightDTO.setMsgSuccess("Insight has been saved successfully.");
			}else if(fromSave!=null && fromSave.equalsIgnoreCase("false")){
				insightDTO.setMsgSuccess("Session got expired,insight details was not saved.");
			}
			mav.addObject("mInsightDTO", insightDTO);

		}catch(Exception e){
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_view"));
		}
		log.info("ending of the viewInsight method in InsightController");
		return mav;
	}  
	
	/**
	 * Set formatted weblinks
	 * @param insightDTO
	 */
	private void setFormattedWeblinks(InsightDto insightDTO){
		if(insightDTO!=null && insightDTO.getInsightDetailsDto()!=null 
				&& insightDTO.getInsightDetailsDto().getWeblinks()!=null 
				&& insightDTO.getInsightDetailsDto().getWeblinks().size()>0){
			Set<InsightWeblink> weblinksSet = insightDTO.getInsightDetailsDto().getWeblinks();
			for(InsightWeblink insightWeblink : weblinksSet){
				 if(insightWeblink.getWeblinkValue()!=null && (!insightWeblink.getWeblinkValue().toLowerCase().startsWith("http://") && !insightWeblink.getWeblinkValue().toLowerCase().startsWith("https://"))){
					 insightWeblink.setWeblinkValue("http://"+insightWeblink.getWeblinkValue().trim());
				 }
			}
			
		}
	}

	/**
	 * Display data of
	 * particular insight in 
	 * edit mode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editinsight",method=RequestMethod.GET)  
	public ModelAndView editInsight(HttpServletRequest request) throws Exception {
		String insightId=request.getParameter("insightId");
		String googleSession=request.getParameter("googleSession");
		if(googleSession==null || (googleSession!=null && !googleSession.equalsIgnoreCase("true"))){// If value of this parameter is "true" then google signin is already exist.
			ModelAndView mav = null;
			request.setAttribute("pageType", "editinsight");// This variable decides final page for navigation after successful google signin.
			request.setAttribute("insightId", insightId);
			
			//Navigate to the intermediate page which checks google session. If google session not exist then navigate to BMJ google login. If exist then
			//navigate to page which is based on "pageType" variable.
			mav = new ModelAndView("sessionCheck");	
			return mav;
		}
		
		log.info("in the edit insight method");
		HttpSession session = request.getSession();		
		if(session != null && session.getAttribute("BMJSessionToken")!=null){
			resetSessionData(request);
	
			
			InsightDto insightDTO = null ;
			try{
			List<InsightDto> insightDtoList = insightService.getInsightDetails(insightId);//to get insight details
		    insightDTO = insightDtoList.get(0);
		    insightDTO.setRoleId(getRoleId(request));
			setMasterData(insightDTO);
			setDBDataForFields(insightDTO);
			setOtherId(insightDTO);
			setWebLinkInSession(request, insightDTO);
			Collections.sort(insightDTO.getLstFoundViaDto());
			Collections.sort(insightDTO.getLstMainUserTypeDto());
			Collections.sort(insightDTO.getLstGeographiesDto());
			insightDTO.getInsightDetailsDto().setDescription(StringEscapeUtils.unescapeHtml(insightDTO.getInsightDetailsDto().getDescription()));
			}catch(Exception e){
				CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_edit"));
			}
			return new ModelAndView("editInsight","mInsightDTO",insightDTO);
		}else{
			log.debug("in the edit insight page insightId "+insightId);
			request.setAttribute("insightId",insightId);
			ModelAndView mav = new ModelAndView("redirectEdit");	
			return mav;
		}
	}

	/**
	 * Display insight data in light box after autocomplete click of insight title.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getInsightData", method = RequestMethod.GET)
	public @ResponseBody EditInsightDto getInsightData(HttpServletRequest request, HttpServletResponse response) {
		String insightId = request.getParameter("insightId");
		InsightDto  insightDTO =null;
		EditInsightDto editInsightDTO =new EditInsightDto();
		try {
			List<InsightDto> insightDtoList = insightService.getInsightDetails(insightId);//to get insight details
			insightDTO = insightDtoList.get(0);
			setDBDataForFields(insightDTO);
			editInsightDTO.setId(insightDTO.getInsightDetailsDto().getId());
			editInsightDTO.setDescription(StringEscapeUtils.unescapeHtml(insightDTO.getInsightDetailsDto().getDescription()));
			editInsightDTO.setFoundCount(insightDTO.getInsightDetailsDto().getFoundCount());
			editInsightDTO.setOldFoundCount(insightDTO.getInsightDetailsDto().getFoundCount());
			editInsightDTO.setTitle(insightDTO.getInsightDetailsDto().getTitle());
			editInsightDTO.setStrOldProducts(insightDTO.getStrOldProducts());
			editInsightDTO.setStrOldProjects(insightDTO.getStrOldProjects());
			editInsightDTO.setStrProducts(insightDTO.getStrProducts());
			editInsightDTO.setStrProjects(insightDTO.getStrProjects());
			editInsightDTO.setCompanyName(insightDTO.getInsightDetailsDto().getCompanyName());
			editInsightDTO.setType(insightDTO.getInsightDetailsDto().getType());
	
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_data"));
		}
		return editInsightDTO;
	}
	/**
	 * Set ID value for "Other" option tye.
	 * Components are Found Via , main User Type and Geographies.
	 * @param insightDTO
	 */
	private void setOtherId(InsightDto insightDTO)throws Exception {
		setOtherIdForFoundVia(insightDTO);
		setOtherIdForMainUserType(insightDTO);
		setOtherIdForGeographies(insightDTO);
	}
	/**
	 * It populates data for fields from database for existing Insight.
	 * Field include product , project, Tags , found via, mainusertype,geographies.
	 * @param insightDTO
	 */

	private void setDBDataForFields(InsightDto insightDTO) throws Exception{
		insightDTO.setStrProducts(getProductsList(insightDTO));
		insightDTO.setStrOldProducts(insightDTO.getStrProducts());

		insightDTO.setStrProjects(getProjectsList(insightDTO));
		insightDTO.setStrOldProjects(insightDTO.getStrProjects());

		insightDTO.setStrTags(getTagsList(insightDTO));
		insightDTO.setStrOldTags(insightDTO.getStrTags());


		insightDTO.setStrFoundVia(getFoundViaList(insightDTO));
		insightDTO.setStrOldFoundVia(insightDTO.getStrFoundVia());

		insightDTO.setStrMainUserType(getMainUserTypeList(insightDTO));
		insightDTO.setStrOldMainUserType(insightDTO.getStrMainUserType());

		insightDTO.setStrGeographies(getGeographiesList(insightDTO));
		insightDTO.setStrOldGeographies(insightDTO.getStrGeographies());
	}

	/**
	 * Set master data for insight type and severity.
	 * @param insightDTO
	 * @throws Exception
	 */
	private void setMasterData(InsightDto insightDTO) throws Exception {
		// Set Insight Type data.
		List<SelectValuesDto> lstInsightTypesDto = insightService.getSelectValuesDtoLst(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);// populate the possible values for the insight types dropdown
		insightDTO.setLstInsightTypesDto(lstInsightTypesDto);

		// Set Severity.
		List<SelectValuesDto> lstSeveritiesDto =  insightService.getSelectValuesDtoLst(InsightsConstants.SEVERITY_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);// populate the possible values for the insight types dropdown
		insightDTO.setLstSeveritiesDto(lstSeveritiesDto);
	}
	/**
	 * Reset session data (web links and attachments) of insight object.
	 * @param request
	 */
	private void resetSessionData(HttpServletRequest request) throws Exception{
		resetNewAttachments(request);
		resetWebLinks(request);
	}
	/**
	 * Reset list of new uploaded files to null before coming to New/Edit Insight scenario.
	 * @param request
	 */
	private void resetNewAttachments(HttpServletRequest request)throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("newUploadList", null);
	} 

	/**
	 * Reset list of web links before coming to New/Edit Insight scenario.
	 * @param request
	 */
	private void resetWebLinks(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("webLinks", null);
	} 	


	/**
	 * It gets call when user does save operation on main Insight page.
	 * @param insightDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveinsight",method=RequestMethod.POST)  
	public ModelAndView saveInsight(@ModelAttribute("mInsightDTO") InsightDto insightDTO,HttpServletRequest request) throws Exception {
		log.info("in the saveInsight insight page ");				
		HttpSession session = request.getSession();
		if(session != null  && session.getAttribute("BMJSessionToken")!=null ){		
			try{
				String saveType=request.getParameter("saveType");
				insightDTO.setUserId(getUserId(request));// TBD in dynamic way.
				
				insightDTO.getInsightDetailsDto().setDescription(StringEscapeUtils.escapeHtml(insightDTO.getInsightDetailsDto().getDescription()));
				insightDTO.getInsightDetailsDto().setTitle(CommonUtils.replaceNewLineCrraigeReturnTabSpaces(insightDTO.getInsightDetailsDto().getTitle()));
				
				if((insightDTO.getInsightDetailsDto().getType()==3 || insightDTO.getInsightDetailsDto().getType()==4) && null != insightDTO.getInsightDetailsDto().getCompanyName() && !"".equals(insightDTO.getInsightDetailsDto().getCompanyName().trim()) ){
					insightDTO.getInsightDetailsDto().setCompanyName(insightDTO.getInsightDetailsDto().getCompanyName());
				}else{
					insightDTO.getInsightDetailsDto().setCompanyName("");
				}
	
				setModifiedProducts(insightDTO);		
				setModifiedProjects(insightDTO);
				setModifiedTags(insightDTO);
				setModifiedFoundVia(insightDTO);
				setModifiedMainUserType(insightDTO);
				setModifiedGeographies(insightDTO);
	
				setAttachmentForNewInsight(insightDTO, request);
				setWebLinks(insightDTO, request);
	
				Integer idInsight=insightService.saveInsightDetails(insightDTO);
				request.setAttribute("insightId",idInsight.intValue()+"");
				return new ModelAndView("redirectSave","saveType",saveType);
	
			}catch(Exception e){
				CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_save"));
	
			}
			return new ModelAndView("createInsight","mInsightDTO",insightDTO);
		}else{
			if(insightDTO!=null && insightDTO.getInsightDetailsDto()!=null && insightDTO.getInsightDetailsDto().getId()!=null && insightDTO.getInsightDetailsDto().getId()!=0){
				Integer insightId=insightDTO.getInsightDetailsDto().getId();			
				log.debug("in the saveInsight insight page insightId session expiry "+insightId+"");
				request.setAttribute("insightId",insightId+"");
				ModelAndView mav = new ModelAndView("redirectEdit");	
				return mav;
			}else{
				ModelAndView mav = new ModelAndView("redirectcreate");				
				return mav;
			}
		}
	}



	/**
	 * It gets call when user does  save on lightbox Insight data. 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveEditInsightData", method = RequestMethod.POST)
	public @ResponseBody String saveEditInsightData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("in the saveEditInsightData insight page ");				
		HttpSession session = request.getSession();
		if(session != null  && session.getAttribute("BMJSessionToken")!=null ){		
		String success="true";
			try{
			String title= request.getParameter("idInsightEditTitle");
			String desc= request.getParameter("idInsightEditDesc");
			String project= request.getParameter("idInsigtEditProject");
			String oldProject= request.getParameter("idInsigtEditOldProject");
			String product= request.getParameter("idInsigtEditProduct");
			String oldProduct= request.getParameter("idInsigtEditOldProduct");
			String foundCount= request.getParameter("idInsightEditfoundCnt");
			String insightId= request.getParameter("idInsight");
			String hidoldFoundCount= request.getParameter("oldFoundCount");
			String companyName = request.getParameter("idInsigtEditCompany");
			int inOldFoundCount=0;
			
			if(hidoldFoundCount!=null && !hidoldFoundCount.equals("")){
				inOldFoundCount=new Integer(hidoldFoundCount).intValue();
			}
	
			InsightDto insightDTO=new InsightDto();
			insightDTO.setUserId(getUserId(request));
			insightDTO.getInsightDetailsDto().setId(new Integer(insightId));
			insightDTO.getInsightDetailsDto().setTitle(CommonUtils.replaceNewLineCrraigeReturnTabSpaces(title));
			insightDTO.getInsightDetailsDto().setDescription(StringEscapeUtils.escapeHtml(desc));
			insightDTO.getInsightDetailsDto().setFoundCount(new Integer(foundCount));// Update new found count.
			if(inOldFoundCount>0 && insightDTO.getInsightDetailsDto().getFoundCount()!=null){// Add Old found count to newly added found count.
				insightDTO.getInsightDetailsDto().setFoundCount(insightDTO.getInsightDetailsDto().getFoundCount().intValue()+inOldFoundCount);
			}
	
			insightDTO.setStrOldProducts(oldProduct);
			insightDTO.setStrOldProjects(oldProject);
			insightDTO.setStrProducts(product);
			insightDTO.setStrProjects(project);
			insightDTO.getInsightDetailsDto().setCompanyName(companyName);
			setModifiedProducts(insightDTO);		
			setModifiedProjects(insightDTO);
	
			insightService.saveInsightDetails(insightDTO);
			}catch(Exception e)
			{
				CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_insight_saveandaddanother"));
			}
			return success;
		}else{
			String success="false";				
			log.debug("in the saveEditInsightData session expiry ");				
			return success;
		}

	}

	/**
	 * Set weblinks data in session.
	 * @param request
	 * @param insightDTO
	 */
	private void setWebLinkInSession(HttpServletRequest request,
			InsightDto insightDTO)throws Exception {
		HttpSession session = request.getSession();
		if(session !=null){
			session.setAttribute("webLinks",insightDTO.getWeblinkDTO().getLstInsightWeblinkDTO());
		}
	}

	/**
	 * Generate comma separated list of main user type.
	 * @param insightDTO
	 * @return
	 */
	private String getMainUserTypeList(InsightDto insightDTO) throws Exception{
	
		String mainUserType="";
		String lblMainUserType="";
		boolean flgOther=false; // Indicates 'Other' Option
		for (InsightMainUserType insightMainUserType : insightDTO.getInsightDetailsDto().getMainUserTypes()) {  //insightDTO.getProductsSet()

			if(insightMainUserType.getMainUserType().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				flgOther=true;
			}
			
				if(flgOther){// If "other' option then store as "id:OtherValue"
					mainUserType=mainUserType+","+insightMainUserType.getMainUserType().getId()+":"+insightMainUserType.getMainUserTypeOtherValue();
					lblMainUserType=lblMainUserType+", Other";
				}else{//else store only "Id"
					mainUserType=mainUserType+","+insightMainUserType.getMainUserType().getId()+"";
					lblMainUserType=lblMainUserType+", "+insightMainUserType.getMainUserType().getName()+"";
				
			
			}
		
			flgOther=false;
		}
		
		if(null != mainUserType && mainUserType.trim().length() > 1){
			mainUserType  = mainUserType .substring(1);
			}
			if(null != lblMainUserType&& lblMainUserType.trim().length() > 1){
			lblMainUserType= lblMainUserType.substring(1);
			}

		insightDTO.setLblMainUserType(lblMainUserType.trim());
		return mainUserType;
	}	

	/**
	 * Generate comma separated list of found via.
	 * @param insightDTO
	 * @return
	 */
	private String getFoundViaList(InsightDto insightDTO) throws Exception{
		
		String foundvia="";
		String lblFoundVia="";
		boolean flgOther=false; // Indicates 'Other' Option
		for (InsightFoundVia insightFoundVia : insightDTO.getInsightDetailsDto().getFoundVias()) {  

			if(insightFoundVia.getFoundVia().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				flgOther=true;
			}
			
			if(flgOther){// If "other' option then store as "id:OtherValue"
				foundvia=foundvia+","+insightFoundVia.getFoundVia().getId()+":"+insightFoundVia.getFoundViaOtherValue();
				lblFoundVia=lblFoundVia+", Other";
			}else{//else store only "Id"
				foundvia=foundvia+","+insightFoundVia.getFoundVia().getId()+"";
				lblFoundVia=lblFoundVia+", "+insightFoundVia.getFoundVia().getName()+"";
			}
				
			
			flgOther=false;
		}
		
		if(null != foundvia && foundvia.trim().length() > 1){
			foundvia  = foundvia .substring(1);
			}
			if(null != lblFoundVia&& lblFoundVia.trim().length() > 1){
				lblFoundVia= lblFoundVia.substring(1);
			}
		insightDTO.setLblFoundVia(lblFoundVia.trim());

		return foundvia;
	}

	/**
	 * Generate comma separated list of geographies.
	 * @param insightDTO
	 * @return
	 */
	private String getGeographiesList(InsightDto insightDTO)throws Exception {
		
		String geographies="";
		String lblGeographies="";
		boolean flgOther=false; // Indicates 'Other' Option
		for (InsightGeographies insightGeographies : insightDTO.getInsightDetailsDto().getGeographies()) {  //insightDTO.getProductsSet()

			if(insightGeographies.getGeographies().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				flgOther=true;
			}
					if(flgOther){// If "other' option then store as "id:OtherValue"
						geographies=geographies+","+insightGeographies.getGeographies().getId()+":"+insightGeographies.getInsightGeographicOtherValue();
						lblGeographies=lblGeographies+", Other";
					}else{//else store only "Id"
						geographies=geographies+","+insightGeographies.getGeographies().getId()+"";
						lblGeographies=lblGeographies+", "+insightGeographies.getGeographies().getName()+"";
					}	
			
			flgOther=false;
		}
		
		if(null != geographies && geographies.trim().length() > 1){
			geographies  = geographies .substring(1);
			}
			if(null != lblGeographies&& lblGeographies.trim().length() > 1){
				lblGeographies= lblGeographies.substring(1);
			}
		insightDTO.setLblGeographies(lblGeographies.trim());

		return geographies;
	}

	/**
	 * Set id value from database for 'Other' option of table "FoundVia".Which will use at UI layer.
	 * @param insightDTO
	 */
	private void setOtherIdForFoundVia(InsightDto insightDTO)throws Exception {
		for (FoundViaDto foundViaDTO : insightDTO.getLstFoundViaDto()) {
			if(foundViaDTO.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				insightDTO.setIdOtherFoundVia(foundViaDTO.getId()+"");
			}

		}
	}

	/**
	 * Set id value from database for 'Other' option of table "MainUserType".Which will use at UI layer.
	 * @param insightDTO
	 */
	private void setOtherIdForMainUserType(InsightDto insightDTO)throws Exception {
		for (MainUserTypeDto mainUserTypeDto : insightDTO.getLstMainUserTypeDto()) {
			if(mainUserTypeDto.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				insightDTO.setIdOtherMainUserType(mainUserTypeDto.getId()+"");
			}

		}
	}

	/**
	 * Set id value from database for 'Other' option of table "Geographies".Which will use at UI layer.
	 * @param insightDTO
	 */
	private void setOtherIdForGeographies(InsightDto insightDTO)throws Exception {
		for (GeographiesDto geographiesDto : insightDTO.getLstGeographiesDto()) {
			if(geographiesDto.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				insightDTO.setIdOtherGeographies(geographiesDto.getId()+"");
			}

		}
	}

	/**
	 * Generate comma seperated list of product names.
	 * @param insightDTO
	 * @return
	 */
	/**
	 * Generate comma seperated list of product names.
	 * @param insightDTO
	 * @return
	 */
	private String getProductsList(InsightDto insightDTO) throws Exception{
		String products="";
		for (InsightProduct insightProduct : insightDTO.getInsightDetailsDto().getProducts()) {  //insightDTO.getProductsSet()
			
				products=products+","+insightProduct.getProduct().getName();
			
		}
		if (!products.equals("") && products.substring(0, 1).equalsIgnoreCase(",")) {
			products = products.substring(1, products.length());
		}
		return products;
	}

	/**
	 * Generate comma seperated list of project names.
	 * @param insightDTO
	 * @return
	 */
	private String getProjectsList(InsightDto insightDTO) throws Exception{
		
		String projects="";
		for (InsightProject insightProject : insightDTO.getInsightDetailsDto().getProjects()) {	
				projects=projects+","+insightProject.getProject().getName();
		}
		if (!projects.equals("") && projects.substring(0, 1).equalsIgnoreCase(",")) {
			projects = projects.substring(1, projects.length());
		}
		return projects;
	}	

	/**
	 * Generate comma seperated list of tags names.
	 * @param insightDTO
	 * @return
	 */
	private String getTagsList(InsightDto insightDTO)throws Exception {
		String tags="";
		for (InsightTag insightTag : insightDTO.getInsightDetailsDto().getTags()) {	
				tags=tags+","+insightTag.getTag().getName();
		}
		if (!tags.equals("") && tags.substring(0, 1).equalsIgnoreCase(",")) {
			tags = tags.substring(1, tags.length());
		}
		return tags;
	}	
	
	/**
	 * Generate comma separated list of geographies.
	 * @param insightDTO
	 * @return
	 */
	private String getGeographiesListNames(InsightDto insightDTO)throws Exception {
		StringBuilder geographies = new StringBuilder();
		String geographiesStr = "";
		if(insightDTO.getInsightDetailsDto().getGeographies()!=null && insightDTO.getInsightDetailsDto().getGeographies().size()>0){
			for (InsightGeographies insightGeographies : insightDTO.getInsightDetailsDto().getGeographies()) {  //insightDTO.getProductsSet()				
					if(insightGeographies.getGeographies().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE))
					{
						geographies.append(insightGeographies.getGeographies().getName()+" : "+CommonUtils.getReplaceAll(insightGeographies.getInsightGeographicOtherValue())+", ");
					}else{
						geographies.append(insightGeographies.getGeographies().getName()+", ");
					}
				}
			if(!geographies.equals("")){
				geographiesStr = geographies.substring(0, geographies.length()-2);
			}
			
		}
		return geographiesStr;
	}

	/**
	 * Generate comma separated list of main user type.
	 * @param insightDTO
	 * @return
	 */
	private String getMainUserTypeListNames(InsightDto insightDTO)throws Exception {
		StringBuilder mainUserType = new StringBuilder();
		String mainUserTypeStr = "";
		if(insightDTO.getInsightDetailsDto().getMainUserTypes()!=null && insightDTO.getInsightDetailsDto().getMainUserTypes().size()>0){
		for (InsightMainUserType insightMainUserType : insightDTO.getInsightDetailsDto().getMainUserTypes()) {  //insightDTO.getProductsSet()			
				if(insightMainUserType.getMainUserType().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE))
				{
					mainUserType.append(insightMainUserType.getMainUserType().getName()+" : "+CommonUtils.getReplaceAll(insightMainUserType.getMainUserTypeOtherValue())+", ");
				}else{
					mainUserType.append(insightMainUserType.getMainUserType().getName()+", ");
				}
			}
		if(!mainUserType.equals("")){
			mainUserTypeStr = mainUserType.substring(0, mainUserType.length()-2);
		}
	  }
		return mainUserTypeStr;

	}	
	/**
	 * Generate comma separated list of FoundVia.
	 * @param insightDTO
	 * @return
	 */
	private String getFoundViasListNames(InsightDto insightDTO)throws Exception {
		StringBuilder foundVia = new StringBuilder();
		String foundViaStr = "";
		if(insightDTO.getInsightDetailsDto().getFoundVias()!=null && insightDTO.getInsightDetailsDto().getFoundVias().size()>0){
			for (InsightFoundVia insightFoundVia : insightDTO.getInsightDetailsDto().getFoundVias()) {  //insightDTO.getProductsSet()				
					if(insightFoundVia.getFoundVia().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE))
					{
						foundVia.append(insightFoundVia.getFoundVia().getName()+" : "+CommonUtils.getReplaceAll(insightFoundVia.getFoundViaOtherValue())+", ");
					}else{
						foundVia.append(insightFoundVia.getFoundVia().getName()+", ");
					}
				
				}
			if(!foundVia.equals("")){
				foundViaStr = foundVia.substring(0, foundVia.length()-2);	
			}
		}
		return foundViaStr;
	}
	/**
	 * Set weblinks data into DTO from session object for insight.
	 * @param insightDTO
	 * @param request
	 */
	private void setWebLinks(InsightDto insightDTO, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(false);
		List<InsightWeblinkDto> lstSessionWeblinkDTO = new ArrayList<InsightWeblinkDto>();
		if (session!=null && session.getAttribute("webLinks") != null) {
			lstSessionWeblinkDTO = (List<InsightWeblinkDto>) session.getAttribute("webLinks");
			insightDTO.getWeblinkDTO().setLstInsightWeblinkDTO(lstSessionWeblinkDTO);
		}
	}
	/**
	 * Set attachments for new insight.
	 * @param insightDTO
	 * @param request
	 */
	private void setAttachmentForNewInsight(InsightDto insightDTO,
			HttpServletRequest request)throws Exception {
		// If Save happens in case of Create New Insight
		if(insightDTO.getInsightDetailsDto().getId()==0){
			//Set attachment list in case of new insight from session.
			HttpSession session = request.getSession(false);
			if (session!=null && session.getAttribute("newUploadList") != null) {// User is uploading more than one time before click on save.
				List<InsightAttachmentDto> lstSessAttchNewInsight = (List<InsightAttachmentDto>) session.getAttribute("newUploadList");
				insightDTO.getAttachmentDTO().setLstInsightAttachmentDTO(lstSessAttchNewInsight);
			}
		}
	}
	/**
	 * This method sets two list of "Found Via" by user.
	 * 1) newly added "Found Via" 2) deleted list of "Found Via".
	 * @param insightDTO
	 */	
	private void setModifiedFoundVia(InsightDto insightDTO) throws Exception {
		ArrayList<String> listNewElements=getStringToArray(insightDTO.getStrFoundVia());
		ArrayList<String> listOldElements=getStringToArray(insightDTO.getStrOldFoundVia());
		ArrayList<String> newFoundViaList=new ArrayList<String>();
		ArrayList<String> delFoundViaList=new ArrayList<String>();
		setNewElements(listNewElements,listOldElements,newFoundViaList);
		setDeleteElements(listNewElements,listOldElements,delFoundViaList);
		insightDTO.setNewFoundVia(newFoundViaList);
		insightDTO.setDelFoundVia(delFoundViaList);
	}

	/**
	 * This method sets two list of "Main User Type" by user.
	 * 1) newly added "Main User Type" 2) deleted list of "Main User Type".
	 * @param insightDTO
	 */	
	private void setModifiedMainUserType(InsightDto insightDTO)throws Exception {
		ArrayList<String> listNewElements=getStringToArray(insightDTO.getStrMainUserType());
		ArrayList<String> listOldElements=getStringToArray(insightDTO.getStrOldMainUserType());
		ArrayList<String> newMainUserTypeList=new ArrayList<String>();
		ArrayList<String> delMainUserTypeList=new ArrayList<String>();
		setNewElements(listNewElements,listOldElements,newMainUserTypeList);
		setDeleteElements(listNewElements,listOldElements,delMainUserTypeList);
		insightDTO.setNewMainUserType(newMainUserTypeList);
		insightDTO.setDelMainUserType(delMainUserTypeList);
	}

	/**
	 * This method sets two list of "Geographies" by user.
	 * 1) newly added "Geographies" 2) deleted list of "Geographies".
	 * @param insightDTO
	 */	
	private void setModifiedGeographies(InsightDto insightDTO)throws Exception {
		ArrayList<String> listNewElements=getStringToArray(insightDTO.getStrGeographies());
		ArrayList<String> listOldElements=getStringToArray(insightDTO.getStrOldGeographies());
		ArrayList<String> newGeographiesList=new ArrayList<String>();
		ArrayList<String> delGeographiesList=new ArrayList<String>();
		setNewElements(listNewElements,listOldElements,newGeographiesList);
		setDeleteElements(listNewElements,listOldElements,delGeographiesList);
		insightDTO.setNewGeographies(newGeographiesList);
		insightDTO.setDelGeographies(delGeographiesList);
	}

	/**
	 * This method sets two list of products by user.
	 * 1) newly added products 2) deleted list of products.
	 * @param insightDTO
	 */
	private void setModifiedProducts(InsightDto insightDTO)throws Exception {
		ArrayList<String> listOfNewElements=getStringToArray(insightDTO.getStrProducts());
		ArrayList<String> listOfOldElements=getStringToArray(insightDTO.getStrOldProducts());
		ArrayList<String> newProdList=new ArrayList<String>();
		ArrayList<String> delProdList=new ArrayList<String>();
		setNewElements(listOfNewElements,listOfOldElements,newProdList);
		setDeleteElements(listOfNewElements,listOfOldElements,delProdList);
		insightDTO.setNewProdList(newProdList);
		insightDTO.setDelProdList(delProdList);
	}

	/**
	 * This method sets two list of projects by user.
	 * 1) newly added products 2) deleted list of products.
	 * @param insightDTO
	 */
	private void setModifiedProjects(InsightDto insightDTO)throws Exception {
		ArrayList<String> listOfNewElements=getStringToArray(insightDTO.getStrProjects());
		ArrayList<String> listOfOldElements=getStringToArray(insightDTO.getStrOldProjects());
		ArrayList<String> newProjList=new ArrayList<String>();
		ArrayList<String> delProjList=new ArrayList<String>();
		setNewElements(listOfNewElements,listOfOldElements,newProjList);
		setDeleteElements(listOfNewElements,listOfOldElements,delProjList);
		insightDTO.setNewProjList(newProjList);
		insightDTO.setDelProjList(delProjList);
	}

	/**
	 * This method sets two list of tags by user.
	 * 1) newly added tags 2) deleted list of tags.
	 * @param insightDTO
	 */
	private void setModifiedTags(InsightDto insightDTO) throws Exception{
		ArrayList<String> listOfNewElements=getStringToArray(insightDTO.getStrTags());
		ArrayList<String> listOfOldElements=getStringToArray(insightDTO.getStrOldTags());
		ArrayList<String> newTagList=new ArrayList<String>();
		ArrayList<String> delTagList=new ArrayList<String>();
		setNewElements(listOfNewElements,listOfOldElements,newTagList);
		setDeleteElements(listOfNewElements,listOfOldElements,delTagList);
		insightDTO.setNewTagList(newTagList);
		insightDTO.setDelTagList(delTagList);
	}


	/**
	 * Populate array of new elements added by user in UI.
	 * @param listOfNewElements
	 * @param listOfOldElements
	 * @param newlyAddedElementsList
	 */
	private void setNewElements(ArrayList<String> listOfNewElements,ArrayList<String> listOfOldElements,ArrayList<String> newlyAddedElementsList)throws Exception{
		for (String newElement : listOfNewElements) {
			boolean flagFound=false;
			for (String oldElement : listOfOldElements) {
				if(newElement.equalsIgnoreCase(oldElement)){
					flagFound=true;
				}
			}

			if(!flagFound){
				if(!newElement.trim().equals("")){
					newlyAddedElementsList.add(newElement);
				}
			}
		}

	}

	/**
	 * Populate array of deleted elements by user .
	 * @param listOfNewElements
	 * @param listOfOldElements
	 * @param deletedElementsList
	 */
	private void setDeleteElements(ArrayList<String> listOfNewElements,ArrayList<String> listOfOldElements,ArrayList<String> deletedElementsList)throws Exception{
		for (String oldElement : listOfOldElements) {
			boolean flagFound=false;
			for (String newElement : listOfNewElements) {
				if(oldElement.equalsIgnoreCase(newElement)){
					flagFound=true;
				}
			}

			if(!flagFound){
				if(!oldElement.trim().equals("")){
					deletedElementsList.add(oldElement);
				}
			}
		}

	}	
	/**
	 * To convert comma separated string to array.
	 * @param entityName
	 * @return
	 */
	private ArrayList<String> getStringToArray(String entityName)throws Exception {
		String entityArray[] = null;
		ArrayList<String> listEntity = new ArrayList<String>();
		if(null != entityName && !"".equals(entityName.trim())){
			entityArray = entityName.split(",");
			for (int l = 0; l < entityArray.length; l++) {
				listEntity.add(entityArray[l]);
			}
		}

		return listEntity;
	}  	

	/**
	 * Display auto complete list of product when user type product name.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public @ResponseBody
	String listProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String products = null;
		try {
			String prodName = request.getParameter("prodName");
			List<ProductDto> lstProductDTO = insightService.getAutoCompleteProductList(prodName);

			List<TagEditorJson> lstProduct = new ArrayList<TagEditorJson>();

			for (ProductDto productDTO : lstProductDTO) {
				TagEditorJson tagEditorJson = new TagEditorJson();
				tagEditorJson.setId(productDTO.getName());
				tagEditorJson.setText(productDTO.getName());
				lstProduct.add(tagEditorJson);
			}
			JSONArray jsonA = new JSONArray();
			jsonA.put(lstProduct);

			String strJson = jsonA.toString();
			products = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_product"));
		}
		return products;
	}
	/**
	 * Display auto complete list of projects when user type project name.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listProjects", method = RequestMethod.GET)
	public @ResponseBody
	String listProjects(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String projects = null;
		try {
			String projName = request.getParameter("projName");
			List<ProjectDto> lstProjectDTO = insightService.getAutoCompleteProjectList(projName);

			List<TagEditorJson> lstProject = new ArrayList<TagEditorJson>();

			for (ProjectDto projecttDTO : lstProjectDTO) {
				TagEditorJson tagEditorJson = new TagEditorJson();
				tagEditorJson.setId(projecttDTO.getName());
				tagEditorJson.setText(projecttDTO.getName());
				lstProject.add(tagEditorJson);
			}
			JSONArray jsonA = new JSONArray();
			jsonA.put(lstProject);

			String strJson = jsonA.toString();
			projects = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_project"));
		}
		return projects;
	}
	/**
	 * Display auto complete list of tags when user type tag name.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listTags", method = RequestMethod.GET)
	public @ResponseBody
	String listTags(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tags = null;
		try {
			String tagName = request.getParameter("tagName");
			List<TagDto> lstTagDTO = insightService.getAutoCompleteTagList(tagName);

			List<TagEditorJson> lstTag = new ArrayList<TagEditorJson>();

			for (TagDto tagDTO : lstTagDTO) {
				TagEditorJson tagEditorJson = new TagEditorJson();
				tagEditorJson.setId(tagDTO.getName());
				tagEditorJson.setText(tagDTO.getName());
				lstTag.add(tagEditorJson);
			}
			JSONArray jsonA = new JSONArray();
			jsonA.put(lstTag);

			String strJson = jsonA.toString();
			tags = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_tags"));
		}
		return tags;
	}


	/**
	 * Display auto complete list of Insight titles when user type insight title.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listInsight", method = RequestMethod.GET)
	public @ResponseBody
	String listInsight(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String insights = null;
		try {
			String insightName = request.getParameter("insightName");
			List<InsightDto> lstInsightDTO = insightService.getAutoCompleteInsightsList(insightName);

			List<TagEditorJson> lstInsight = new ArrayList<TagEditorJson>();

			for (InsightDto insightDTO : lstInsightDTO) {
				TagEditorJson tagEditorJson = new TagEditorJson();
				tagEditorJson.setId(insightDTO.getInsightDetailsDto().getTitle());
				tagEditorJson.setText(insightDTO.getInsightDetailsDto().getTitle());
				lstInsight.add(tagEditorJson);
			}
			JSONArray jsonA = new JSONArray();
			jsonA.put(lstInsight);

			String strJson = jsonA.toString();
			insights = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_listinsight"));
		}
		return insights;
	}

	/**
	 * It gets call when user clicks on add button of URL.    
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addLink", method = RequestMethod.GET)
	public @ResponseBody WeblinkDto addLink(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WeblinkDto weblinkDTO=new WeblinkDto();
		try{
		String value = request.getParameter("value");
		String insightId = request.getParameter("insightId");
		HttpSession session = request.getSession();
		List<InsightWeblinkDto> lstSessionWeblinkDTO = new ArrayList<InsightWeblinkDto>();
		if (session!=null && session.getAttribute("webLinks") != null) {// User is uploading more than one time before click on save.
			lstSessionWeblinkDTO = (List<InsightWeblinkDto>) session.getAttribute("webLinks");
			InsightWeblinkDto insightWeblinkDTO=new InsightWeblinkDto();
			insightWeblinkDTO.setWeblinkValue(value);
			insightWeblinkDTO.setWebLinkUIId(CommonUtils.getBMJCurrentTimeStampId());
			lstSessionWeblinkDTO.add(insightWeblinkDTO);
		}
		weblinkDTO.setLstInsightWeblinkDTO(lstSessionWeblinkDTO);
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_addlink"));
		}
		return weblinkDTO;
	}


	/**
	 * It gets call when user clicks on add button of URL.    
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteLink", method = RequestMethod.GET)
	public @ResponseBody WeblinkDto deleteLink(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WeblinkDto weblinkDTO=new WeblinkDto();
		try{
		String weblinkId = request.getParameter("weblinkId");
		String insightId = request.getParameter("insightId");
		String weblinkUIId = request.getParameter("weblinkUIId");
		HttpSession session = request.getSession();
		List<InsightWeblinkDto> lstSessionWeblinkDTO = new ArrayList<InsightWeblinkDto>();
		if (session!=null && session.getAttribute("webLinks") != null) {// .
			lstSessionWeblinkDTO = (List<InsightWeblinkDto>) session.getAttribute("webLinks");
			for (InsightWeblinkDto insightWeblinkDTO : lstSessionWeblinkDTO) {
				if(insightWeblinkDTO.getWebLinkUIId()!=null && weblinkUIId!=null && insightWeblinkDTO.getWebLinkUIId().equals(weblinkUIId)){
					insightWeblinkDTO.setFlgDelete(true);//Mark weblink as delete.
					break;
				}else if(insightWeblinkDTO.getId()!=null && weblinkId!=null && !weblinkId.equalsIgnoreCase("null") && insightWeblinkDTO.getId().intValue()==new Integer(weblinkId).intValue() ){
					insightWeblinkDTO.setFlgDelete(true);//Mark weblink as delete.
					break;
				}
			}
			session.setAttribute("webLinks",lstSessionWeblinkDTO);
		}
		weblinkDTO.setLstInsightWeblinkDTO(lstSessionWeblinkDTO);
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_deletelink"));
		}
		return weblinkDTO;
	}	


	
	/**
	 * This is method is used to read the severity images dynamically from getRealPath/resources/images/
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/displaySeverityImage", method = RequestMethod.GET)
	public ModelAndView displaySeverityImage(HttpServletRequest request,HttpServletResponse response) {
		log.info("starting of the displaySeverityImage method in InsightController");
		ModelAndView mav = new ModelAndView("viewInsight");	
		List<SelectValuesDto> lstSeveritiesDto = null;
		try{
			
			lstSeveritiesDto = CommonUtils.getSelectValuesDtoLst(InsightsConstants.SEVERITY_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);
			log.debug("severities list size "+lstSeveritiesDto.size());
			Map<Integer,String> severityMap =CommonUtils.getSeverityCodeListMap(lstSeveritiesDto);
			String idSeverity=request.getParameter("id");
			String fileName = severityMap.get(Integer.valueOf(idSeverity));
			log.debug("fileName "+fileName);
			String targetPath=(new StringBuffer(request.getSession().getServletContext().getRealPath("/"))
			.append(File.separator)
			.append(InsightsConstants.SEVERITY_IMAGE_PATH)
			.append(fileName.toLowerCase(Locale.ENGLISH))
			.append(InsightsConstants.SEVERITY_IMAGES_FILE_EXT)
			.toString());
			log.debug("targetPath "+targetPath);
			Path path = Paths.get(targetPath);
			byte[] data = Files.readAllBytes(path);
			writeFiletoStream(data, fileName, response, InsightsConstants.SEVERITY_IMAGES_FILE_EXT);
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_servrity"));
		}
		log.info("ending of the displaySeverityImage method in InsightController");
		return mav;
	}


	/**
	 * Display auto complete list of product when user type product name.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/autoComInsightTitle", method = RequestMethod.GET)
	public @ResponseBody
	String autoCompleteInsightTitle(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String products = null;
		try {
			String title = request.getParameter("nametitle");

			List<InsightDto> lstInsightDTO=insightService.getAutoCompleteInsightsList(title);

			List<TagEditorJson> lstTitles = new ArrayList<TagEditorJson>();

			for (InsightDto insightDTO : lstInsightDTO) {
				TagEditorJson tagEditorJson = new TagEditorJson();
				tagEditorJson.setId(insightDTO.getInsightDetailsDto().getId()+"" );
				tagEditorJson.setText(insightDTO.getInsightDetailsDto().getTitle());
				lstTitles.add(tagEditorJson);
			}

			JSONArray jsonA = new JSONArray();
			jsonA.put(lstTitles);

			String strJson = jsonA.toString();
			products = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_title"));
		}
		return products;
	}

	/**
	 * Display auto complete list of Insight title or product or project or tag when user type text.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/autoComSearchText", method = RequestMethod.GET)
	public @ResponseBody
	String autoCompleteSearchText(HttpServletRequest request, HttpServletResponse response)throws Exception {
		log.info("starting of the autoCompleteSearchText method in InsightController");
		String products = null;
		try {
			String title = request.getParameter("nametitle");
			List<InsightDetailsDto> lstInsightDetailsDto=insightService.getAutoCompleteSearchTextList(title);

			ArrayList<TagEditorJson> lstTitles = new ArrayList<TagEditorJson>();
			ArrayList<TagEditorJson> lstTitles1 = new ArrayList<TagEditorJson>();
			log.debug("insight detailsDto list size "+lstInsightDetailsDto.size());
			for (InsightDetailsDto insightDetailsDto : lstInsightDetailsDto) {
				TagEditorJson tagEditorJson = new TagEditorJson();
					if(StringUtils.containsIgnoreCase(insightDetailsDto.getTitle(), title)){
						tagEditorJson.setText(insightDetailsDto.getTitle());
						lstTitles.add(tagEditorJson);
					}
					log.debug("insightProject list size "+insightDetailsDto.getProjects().size());
					for(InsightProject insightProject : insightDetailsDto.getProjects()){
						TagEditorJson projectTagEditorJson = new TagEditorJson();
						if(StringUtils.containsIgnoreCase(insightProject.getProject().getName(), title)){
							projectTagEditorJson.setText(insightProject.getProject().getName());
							lstTitles.add(projectTagEditorJson);
						}
					}
					log.debug("insightProduct list size "+insightDetailsDto.getProducts().size());
					for(InsightProduct insightProduct : insightDetailsDto.getProducts()){
						TagEditorJson productTagEditorJson = new TagEditorJson();						
						if(StringUtils.containsIgnoreCase(insightProduct.getProduct().getName(), title)){
							productTagEditorJson.setText(insightProduct.getProduct().getName());
							lstTitles.add(productTagEditorJson);
						}
					}
					log.debug("insightTag list size "+insightDetailsDto.getTags().size());
					for(InsightTag insightTag : insightDetailsDto.getTags()){
						TagEditorJson tagTagEditorJson = new TagEditorJson();						
						if(StringUtils.containsIgnoreCase(insightTag.getTag().getName(), title)){
							tagTagEditorJson.setText(insightTag.getTag().getName());
							lstTitles.add(tagTagEditorJson);
						}
					}
			}
			JSONArray jsonA = new JSONArray();
			Iterator<TagEditorJson> iterator = lstTitles.iterator();

	        while (iterator.hasNext())
	        {
	        	TagEditorJson o = (TagEditorJson) iterator.next();
	            if(!lstTitles1.contains(o)) lstTitles1.add(o);
	        }
			jsonA.put(lstTitles1);

			String strJson = jsonA.toString();
			products = strJson.substring(1, strJson.length() - 1);

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_autocomplete_list"));
		}
		log.info("ending of the autoCompleteSearchText method in InsightController");
		return products;
	}


	/**
	 * To add generated file
	 * @param contents
	 *  @param fileName
	 * @param response
	 * @param fileType
	 * @throws Exception
	 */
	public static void writeFiletoStream(byte[] contents, String fileName, HttpServletResponse response,String fileType) throws Exception{
		log.info("starting of the writeFiletoStream method in InsightController");
		log.debug("image fileType "+fileType);
		log.debug("image fileName "+fileName);
		response.setContentType("image/"+fileType);
		response.setContentLength(contents.length);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName+"\"");

		ServletOutputStream out = response.getOutputStream();
		out.write(contents);
		out.flush();
		out.close();  
		log.info("ending of the writeFiletoStream method in InsightController");
	}
	
	/**
	 * Get current user id from session token.
	 * @param request
	 * @throws Exception
	 */
	private Integer getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("BMJSessionToken") != null) {
			BMJSessionToken bmjSessionToken = (BMJSessionToken) session.getAttribute("BMJSessionToken");
			return bmjSessionToken.getUserId();
		}
		return null;
	}

	/**
	 * Get Role  id from session token.
	 * @param request
	 * @throws Exception
	 */
	private Integer getRoleId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("BMJSessionToken") != null) {
			BMJSessionToken bmjSessionToken = (BMJSessionToken) session.getAttribute("BMJSessionToken");
			log.info("Role id for logged in user = "+bmjSessionToken.getRoleId());
			return bmjSessionToken.getRoleId();
		}
		return null;
	}

	
	/**
	 * To round the given value
	 * upto given places 
	 * @param value
	 * @param places
	 * @throws Exception
	 */
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	
	@ModelAttribute("mInsightDTO")
	   public InsightDto populateInsightDto() {
	       return new InsightDto(); // populates form for the first time if its null
	   }
	
	
	
	   /**
		 * Undo operation for
		 * particular insight 
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/undoInsight",method=RequestMethod.GET)  
		public @ResponseBody String deleteInsight(HttpServletRequest request) throws Exception {
			String result = "pass";
			try{
				String insightId= request.getParameter("insightid");
				log.info("Performing undo operation for insight id = "+insightId);
				insightService.saveOrUndoOperation(insightId);
			}catch(Exception e){
				result = "fail";
				CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_deleting_insight"));
			}
			
			return result;
		}  
	
		@ModelAttribute("searchCriteria")
		 public SearchCriteriaDto populateSearchCriteriaDto() {
		       return new SearchCriteriaDto(); // populates form for the first time if its null
		   }
		
}
