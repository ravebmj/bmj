package org.bmj.userinsights.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchResults {
	
    @RequestMapping("/searchresults")  
    public ModelAndView showDashboard() {  
        return new ModelAndView("searchresults");  
    } 
}