package org.bmj.userinsights.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.dashboard.dto.DateCriteriaDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.SeveritiesDto;
import org.bmj.userinsights.dashboard.dto.StrongestEvidenceInsightDTO;
import org.bmj.userinsights.dashboard.service.IDashboardService;
import org.bmj.userinsights.search.dto.SearchCriteria;
import org.bmj.userinsights.service.IUserInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("searchCriteria")
@Controller
public class DashBoardController {
	
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private IUserInsightService userInsightService;
	
	@Autowired
	private IDashboardService dashboardService;
	
	private static final  Logger log = Logger.getLogger(DashBoardController.class);
	
	@RequestMapping("/home")  
    public String showHome() throws Exception {  
		
		System.out.println("++++4444+++++ "+messageSource.getMessage("welcomemessage", null, "Default",null));		
		//System.out.println(userInsightService.getPerson("1"));
		//System.out.println(" ****4444***** "+userInsightService.getPerson("2"));
        return "redirect:dashboard.html";  
    }  
	
	
    @RequestMapping("/dashboard")  
    public ModelAndView showDashboard() {
    	List<InsightTypesDto> lstInsightTypesDto = null;
    	List<SeveritiesDto> lstSeveritiesDto = null;
    	List<DateCriteriaDto> lstDateCriteriaDto = null;
    	List<RecentInsightsDto> lstRecentInsightsDto = null;
    	List<StrongestEvidenceInsightDTO> lstSrongestEvidenceInsightDTO = null;
    	System.out.println("in the showDashboard");    	
    	DashboardDTO dashboardDto = new DashboardDTO();
    	ModelAndView mav = new ModelAndView("dashboard");    	
    	SearchCriteria searchCriteria=null;
		try {
			lstInsightTypesDto = getInsightTypesCodeListDecodedNames(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);// populate the possible values for the insight types dropdown in Advanced Search section
			lstSeveritiesDto = getSeveritiesCodeListDecodedNames(InsightsConstants.SEVERITY_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);
			lstDateCriteriaDto = InsightsConstants.getDateCriteriaLst();			
			lstRecentInsightsDto = dashboardService.getRecentlyAddedInsights();
			lstSrongestEvidenceInsightDTO = dashboardService.getStrongestEvidenceInsights();
			
			searchCriteria = userInsightService.getSearchCriteriaDto(lstInsightTypesDto,lstSeveritiesDto,lstDateCriteriaDto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	dashboardDto.setInsightTypesDtoLst(lstInsightTypesDto);
    	dashboardDto.setRecentInsightsDtoLst(lstRecentInsightsDto);
    	dashboardDto.setStrongestEvidenceInsightDtoLst(lstSrongestEvidenceInsightDTO);
    	
        mav.addObject("dashboardDto", dashboardDto);  
        mav.addObject("searchCriteria", searchCriteria);
    	
    	
        return mav;  
    } 
    
    
    private List<InsightTypesDto> getInsightTypesCodeListDecodedNames(String codelistName,String applicationId) throws Exception{
    	List<SelectValuesDto> decodedNamesDtoLst = userInsightService.getSelectValuesDtoLst(codelistName,applicationId);
    	List<InsightTypesDto> lstInsighsTypes = new ArrayList<InsightTypesDto>();
    	if(decodedNamesDtoLst!=null && decodedNamesDtoLst.size()>0){
    		for(SelectValuesDto decodedObj:decodedNamesDtoLst){
    			InsightTypesDto insightTypesDto = new InsightTypesDto();    			
    			insightTypesDto.setInsightTypeId(decodedObj.getCodeDecodedCode());
    			insightTypesDto.setInsightTypeName(decodedObj.getCodeDecodedName());
    			lstInsighsTypes.add(insightTypesDto);
    		}
    	}
    	
    	
    	return lstInsighsTypes;
    	
    }
    
    private List<SeveritiesDto> getSeveritiesCodeListDecodedNames(String codelistName,String applicationId) throws Exception{
    	List<SelectValuesDto> decodedNamesDtoLst = userInsightService.getSelectValuesDtoLst(codelistName,applicationId);
    	List<SeveritiesDto> lstSeverities = new ArrayList<SeveritiesDto>();
    	if(decodedNamesDtoLst!=null && decodedNamesDtoLst.size()>0){
    		for(SelectValuesDto decodedObj:decodedNamesDtoLst){
    			SeveritiesDto severitiesDto = new SeveritiesDto();
    			severitiesDto.setServerityId(decodedObj.getCodeDecodedCode());
    			severitiesDto.setServerityName(decodedObj.getCodeDecodedName());
    			lstSeverities.add(severitiesDto);
    		}
    	}
    	
    	
    	return lstSeverities;
    	
    }
   
}
