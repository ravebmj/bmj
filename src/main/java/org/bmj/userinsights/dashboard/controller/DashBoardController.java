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
import org.bmj.userinsights.service.IUserInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoardController {
	
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private IUserInsightService userInsightService;
	
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
    	
    	System.out.println("in the showDashboard");
    	
    	DashboardDTO dashboardDto = new DashboardDTO();
    	ModelAndView mav = new ModelAndView("dashboard");
    	
    	
    	List<SearchAllInsightsDto> lstSearchAllInsightsDto = getSearchAllInsightsDtoLst();
    	List<RecentInsightsDto> lstRecentInsightsDto = getRecentlyAddedInsights();
    	dashboardDto.setSearchAllInsightsDtoLst(lstSearchAllInsightsDto);
    	dashboardDto.setRecentInsightsDtoLst(lstRecentInsightsDto);
    	
        mav.addObject("dashboardDto", dashboardDto);  
    	
    	
        return mav;  
    } 
    
    
    public List<SearchAllInsightsDto> getSearchAllInsightsDtoLst(){
    	List<SearchAllInsightsDto> lstSearchAllInsightsDto = new ArrayList<SearchAllInsightsDto>();
    	
    	SearchAllInsightsDto obj1 = new SearchAllInsightsDto();
    	obj1.setSearchInsightId(1);
    	obj1.setSearchInsightName("Classic mail");
    	
    	SearchAllInsightsDto obj2 = new SearchAllInsightsDto();
    	obj2.setSearchInsightId(2);
    	obj2.setSearchInsightName("UPS Delivery");
    	
    	SearchAllInsightsDto obj3 = new SearchAllInsightsDto();
    	obj3.setSearchInsightId(3);
    	obj3.setSearchInsightName("Private Jet");
    	
    	lstSearchAllInsightsDto.add(obj1);
    	lstSearchAllInsightsDto.add(obj2);
    	lstSearchAllInsightsDto.add(obj3);
    	
    	
    	return lstSearchAllInsightsDto;
    }
    
    
    
    public List<RecentInsightsDto> getRecentlyAddedInsights(){
    	List<RecentInsightsDto> lstRecentInsightsDto = new ArrayList<RecentInsightsDto>();
    	
    	RecentInsightsDto recent1 = new RecentInsightsDto();    	
    	recent1.setInsightId(1);
    	recent1.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent1.setProjectName("BMJ print design");
    	recent1.setType("User Insight");
    	recent1.setLastEdited("05-Jan-2015");
    	
    	RecentInsightsDto recent2 = new RecentInsightsDto(); 
    	recent2.setInsightId(2);
    	recent2.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent2.setProjectName("BMJ print design");
    	recent2.setType("User Insight");
    	recent2.setLastEdited("05-Jan-2015");
    	
    	RecentInsightsDto recent3 = new RecentInsightsDto(); 
    	recent3.setInsightId(3);
    	recent3.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent3.setProjectName("BMJ print design");
    	recent3.setType("User Insight");
    	recent3.setLastEdited("05-Jan-2015");
    	
    	lstRecentInsightsDto.add(recent1);
    	lstRecentInsightsDto.add(recent2);
    	lstRecentInsightsDto.add(recent3);
    	
    	return lstRecentInsightsDto;
    	
    }
  
}
