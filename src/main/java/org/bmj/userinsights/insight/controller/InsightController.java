package org.bmj.userinsights.insight.controller;

import org.apache.log4j.Logger;
import org.bmj.userinsights.controller.HomePage;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsightController {
	private static final  Logger log = Logger.getLogger(InsightController.class);
	
	
	
	@RequestMapping("/createinsight")  
    public ModelAndView showCreateInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto) throws Exception {
		
		System.out.println("in the showCreateInsight");
		
       return new ModelAndView("createInsight","dashboardDto",dashboardDto);
    } 
	
	
	@RequestMapping("/viewinsight")  
    public ModelAndView showViewInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto) throws Exception {
		
		System.out.println("in the showCreateInsight");
		
       return new ModelAndView("viewInsight","dashboardDto",dashboardDto);
    }  
}
