package org.bmj.userinsights.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchResults {
	
    @RequestMapping("/searchresults")  
    public ModelAndView showDashboard(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto,HttpServletRequest req) { 
    	
    	
    	System.out.println("in the showSearchResults "+dashboardDto.getSearchTxt());
    	
    	
    	
        return new ModelAndView("searchresults","dashboardDto",dashboardDto);  
    } 
}