package org.bmj.userinsights.insight.controller;

import org.apache.log4j.Logger;
import org.bmj.userinsights.controller.HomePage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateInsight {
	private static final  Logger log = Logger.getLogger(HomePage.class);
	
	@RequestMapping("/createInsight")  
    public ModelAndView showCreateInsight() throws Exception {  
       return new ModelAndView("createInsight");
    }  
}
