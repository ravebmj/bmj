package org.bmj.userinsights.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.dashboard.dto.SearchAllInsightsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoard {
	
    @RequestMapping("/dashboard")  
    public ModelAndView showDashboard() { 
    	DashboardDTO dashboardDto = new DashboardDTO();
    	System.out.println("in the showDashboard");
    	/*List<SearchAllInsightsDto> lstSearchAllInsightsDto = getSearchAllInsightsDtoLst();
    	dashboardDto.setSearchAllInsightsDtoLst(lstSearchAllInsightsDto);*/
        return new ModelAndView("dashboard","dashboardDto",dashboardDto);  
    } 
    
    
    public List<SearchAllInsightsDto> getSearchAllInsightsDtoLst(){
    	List<SearchAllInsightsDto> lstSearchAllInsightsDto = new ArrayList<SearchAllInsightsDto>();
    	
    	SearchAllInsightsDto obj1 = new SearchAllInsightsDto();
    	obj1.setSearchInsightId(1);
    	obj1.setSearchInsightName("Classic mail");
    	
    	SearchAllInsightsDto obj2 = new SearchAllInsightsDto();
    	obj1.setSearchInsightId(2);
    	obj1.setSearchInsightName("UPS Delivery");
    	
    	SearchAllInsightsDto obj3 = new SearchAllInsightsDto();
    	obj1.setSearchInsightId(3);
    	obj1.setSearchInsightName("Private Jet");
    	
    	return lstSearchAllInsightsDto;
    }
    
  
}
