package org.bmj.userinsights.dashboard.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dashboard.dto.DashboardDto;
import org.bmj.userinsights.dashboard.service.IDashboardService;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.search.dto.SearchCriteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
/** 
 *This class is used for dashboard related actions except the quick search and advance search
 */
@SessionAttributes({"searchCriteria","dashboardDto"})
@Controller
public class DashBoardController {	
	
	private static final  Logger log = Logger.getLogger(DashBoardController.class);
		
	@Autowired
	private IDashboardService dashboardService; // inject the dashboard service
	ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");// get the resource bundle object
	
	/**
	 * This method redirect the request from home to dashboard page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/home")  
    public String showHome() throws Exception {  
		log.info("entered in to showHome method in DashBoardController");
        return "redirect:dashboard.html";  
    }  
	
	/**
	 * This method will display all the data which are present on dashboard
	 * page.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/dashboard")
	public ModelAndView showDashboard(HttpServletRequest request) {
		
		String googleSession=request.getParameter("googleSession");// If value of this parameter is "true" then google signin is already exist. 
		if(googleSession==null || (googleSession!=null && !googleSession.equalsIgnoreCase("true"))){
			ModelAndView mav = null;
			request.setAttribute("pageType", "dashboard");// This variable decides final page for navigation after successful google signin.
			
			//Navigate to the intermediate page which checks google session. If google session not exist then navigate to BMJ google login. If exist then
			//navigate to page which is based on "pageType" variable.
			mav = new ModelAndView("sessionCheck");	
			return mav;
		}
		
		log.info("Entered in to showdashboard method in DashBoardController");
		List<SelectValuesDto> lstInsightTypesDto = null;
		List<SelectValuesDto> lstSeveritiesDto = null;		
		List<SelectValuesDto> lstDateCriteriaDto = null;
		List<InsightDetailsDto> lstRecentInsightsDto = null;
		List<InsightDetailsDto> lstSrongestEvidenceInsightDTO = null;
		List<ProductDto> lstProductDto = null;
		DashboardDto dashboardDto = new DashboardDto();
		ModelAndView mav = new ModelAndView("dashboard");
		SearchCriteriaDto searchCriteriaDto = new SearchCriteriaDto();
		try {
			
			String deleteInsightId = request.getParameter("deleteInsightId");
			if(deleteInsightId!=null){
				HttpSession session = request.getSession();	
				if(session != null && session.getAttribute("BMJSessionToken")!=null){
					dashboardDto.setInsightId(deleteInsightId);
					dashboardDto.setBannerText(messagesProperties.getString("dashboard_banner_message"));
					log.info("Saving delete date to null for insight id = "+deleteInsightId);
					dashboardService.saveInsightForDeleteDate(dashboardDto);
				}else{
					log.debug("in the dashboard page to delete insight for insightId "+deleteInsightId);
					request.setAttribute("insightId",deleteInsightId);
					mav = new ModelAndView("redirectEdit");	
					return mav;
				}
			}
			
			// populate the possible values for the insight types dropdown in
			// Advanced Search section			
			lstInsightTypesDto = CommonUtils.getSelectValuesDtoLst(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,
					InsightsConstants.APPLICATION_ID);
			// populate the possible values for the severity dropdown in
			// Advanced Search section
			lstSeveritiesDto = CommonUtils.getSelectValuesDtoLst(InsightsConstants.SEVERITY_CODE_LIST_NAME,
					InsightsConstants.APPLICATION_ID);
			// populate the possible values for the Date Range options dropdown
			// in Advanced Search section
			lstDateCriteriaDto = InsightsConstants.getDateCriteriaLst();
			//This map is using from CommonUtils to get the insight type name by passing insight type id
			CommonUtils.setCodeListInsightMap(CommonUtils.getCodeListMap(
					InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,
					InsightsConstants.APPLICATION_ID));
			// get all recently added insights based on Added date
			lstRecentInsightsDto = dashboardService.getRecentlyAddedInsights();
			// get all strongest evidence insight based on users count
			lstSrongestEvidenceInsightDTO = dashboardService
					.getStrongestEvidenceInsights();
			// get all product
			lstProductDto = dashboardService.getActiveProducts();
			if(request.getParameter("banner")!=null){
				log.info("Setting banner success message");
				dashboardDto.setBannerText(messagesProperties.getString("dashboard_banner_success_message"));
			}
			

		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,
					messagesProperties.getString("dashboard_error"));

		}

		searchCriteriaDto.setLstInsightTypesDto(lstInsightTypesDto);
		searchCriteriaDto.setLstSeveritiesDto(lstSeveritiesDto);
		searchCriteriaDto.setLstDateCriteriaDto(lstDateCriteriaDto);
		dashboardDto.setRecentInsightsLst(lstRecentInsightsDto);
		dashboardDto
				.setStrongestEvidenceInsightLst(lstSrongestEvidenceInsightDTO);
		dashboardDto.setProductDtoLst(lstProductDto);
		mav.addObject("dashboardDto", dashboardDto);
		mav.addObject("searchCriteria", searchCriteriaDto);
		log.info("End of the showdashboard method in DashBoardController");

		return mav;
	}
    
	@ModelAttribute("searchCriteria")
	   public SearchCriteriaDto populateSearchCriteriaDto() throws Exception {
		log.info("start populateSearchCriteriaDto method in DashBoardController");
		List<SelectValuesDto> lstInsightTypesDto = null;
		List<SelectValuesDto> lstSeveritiesDto = null;		
		List<SelectValuesDto> lstDateCriteriaDto = null;
		SearchCriteriaDto searchCriteriaDto = new SearchCriteriaDto();
		// populate the possible values for the insight types dropdown in
					// Advanced Search section			
					lstInsightTypesDto = CommonUtils.getSelectValuesDtoLst(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,
							InsightsConstants.APPLICATION_ID);
					// populate the possible values for the severity dropdown in
					// Advanced Search section
					lstSeveritiesDto = CommonUtils.getSelectValuesDtoLst(InsightsConstants.SEVERITY_CODE_LIST_NAME,
							InsightsConstants.APPLICATION_ID);
					// populate the possible values for the Date Range options dropdown
					// in Advanced Search section
					lstDateCriteriaDto = InsightsConstants.getDateCriteriaLst();
					searchCriteriaDto.setLstInsightTypesDto(lstInsightTypesDto);
					searchCriteriaDto.setLstSeveritiesDto(lstSeveritiesDto);
					searchCriteriaDto.setLstDateCriteriaDto(lstDateCriteriaDto);
					log.info("end populateSearchCriteriaDto method  in DashBoardController");
	       return new SearchCriteriaDto(); // populates form for the first time if its null
	   }
	
	@ModelAttribute("dashboardDto")
	   public DashboardDto populateDashboardDto() {
	       return new DashboardDto(); // populates form for the first time if its null
	   }
}
