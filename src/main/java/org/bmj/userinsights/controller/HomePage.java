package org.bmj.userinsights.controller;

import org.apache.log4j.Logger;
import org.bmj.userinsights.service.IUserInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {
	
	/*@Autowired
	MessageSource messageSource;
	
	@Autowired
	private IUserInsightService userInsightService;
	
	private static final  Logger log = Logger.getLogger(HomePage.class);
	
	@RequestMapping("/home")  
    public String showHome() throws Exception {  
		
		System.out.println("++++4444+++++ "+messageSource.getMessage("welcomemessage", null, "Default",null));
		
		//System.out.println(userInsightService.getPerson("1"));
		//System.out.println(" ****4444***** "+userInsightService.getPerson("2"));
        return "redirect:dashboard.html";  
    }  */

}
