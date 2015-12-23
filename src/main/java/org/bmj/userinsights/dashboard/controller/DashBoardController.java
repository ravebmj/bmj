package org.bmj.userinsights.dashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.bmj.userinsights.controller.HomePage;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.SearchAllInsightsDto;
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
    	
    	List<SearchAllInsightsDto> lstSearchAllInsightsDto = null;
    	List<RecentInsightsDto> lstRecentInsightsDto = null;
    	System.out.println("in the showDashboard");    	
    	DashboardDTO dashboardDto = new DashboardDTO();
    	ModelAndView mav = new ModelAndView("dashboard");    	
    	SearchCriteria searchCriteria=null;
		try {
			lstSearchAllInsightsDto = dashboardService.getSearchAllInsightsDtoLst();// populate the possible values for the SearchAllInsights dropdown in Advanced Search section
			lstRecentInsightsDto = dashboardService.getRecentlyAddedInsights();
			searchCriteria = userInsightService.getSearchCriteriaDto();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	dashboardDto.setSearchAllInsightsDtoLst(lstSearchAllInsightsDto);
    	dashboardDto.setRecentInsightsDtoLst(lstRecentInsightsDto);
    	
        mav.addObject("dashboardDto", dashboardDto);  
        mav.addObject("searchCriteria", searchCriteria);
    	
    	
        return mav;  
    } 
   
}
