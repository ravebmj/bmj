package org.bmj.userinsights.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoard {
	
    @RequestMapping("/dashboard")  
    public ModelAndView showDashboard() {  
        return new ModelAndView("dashboard");  
    } 
}
