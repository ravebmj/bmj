package org.bmj.userinsights.insight.controller;

import org.apache.log4j.Logger;
import org.bmj.userinsights.controller.HomePage;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.insight.service.IInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class InsightController {
	private static final  Logger log = Logger.getLogger(InsightController.class);
	
	@Autowired
	private IInsightService insightService; 
	
	
	@RequestMapping("/createinsight")  
    public ModelAndView showCreateInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto) throws Exception {
		
		System.out.println("in the showCreateInsight");
		
       return new ModelAndView("createInsight","dashboardDto",dashboardDto);
    } 
	
	/*
	 * Display data of
	 * particular insight in 
	 * view mode
	 */
	@RequestMapping(value="/viewinsight",method=RequestMethod.POST)  
    public ModelAndView showViewInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto, @RequestParam("insightId") String insightId) throws Exception {
		
		System.out.println("in the showViewInsight"+insightId);
		
       return new ModelAndView("viewInsight","dashboardDto",dashboardDto);
    }  
	
	
}
