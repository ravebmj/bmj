package org.bmj.userinsights.search.controller;

import javax.servlet.http.HttpServletRequest;


import org.bmj.userinsights.dashboard.dto.DashboardDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {
	/*
	 * Return Search Result Page
	 */
    @RequestMapping("/searchresults")  
    public ModelAndView showDashboard(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto,HttpServletRequest req) { 
    	
    	
    	System.out.println("in the showSearchResults "+dashboardDto.getSearchTxt());
    	
    	System.out.println("in the showSearchResults "+dashboardDto.getSearchAllInsight());
    	//String[] values = req.getParameterValues("insightId");
    	System.out.println("insight id: "+req.getParameter("insightId"));
    	
        return new ModelAndView("searchresults","dashboardDto",dashboardDto);  

    } 
    
    /*
     * Advance Search
     * with Parameter
     */
    @RequestMapping(value= "/advanceSearch", method=RequestMethod.POST)  
    public ModelAndView advanceSearch(HttpServletRequest request) {
    	
    	ModelAndView model = new ModelAndView("searchresults");  
        return model; 
    }    
    
     
    
}