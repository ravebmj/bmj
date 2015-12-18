package org.bmj.userinsights.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchResults {
	/*
	 * Return Search Result Page
	 */
    @RequestMapping("/searchresults")  
    public ModelAndView showDashboard() {  
        ModelAndView model = new ModelAndView("searchresults");  
        return model;
    } 
    
    /*
     * Advance Search
     * with Parameter
     */
    @RequestMapping(value= "/advanceSearch", method=RequestMethod.POST)  
    public ModelAndView shows(HttpServletRequest request) {
    	
    	ModelAndView model = new ModelAndView("searchresults");  
        return model; 
    }    
    
    
}