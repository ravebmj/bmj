package org.bmj.userinsights.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.login.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginPage {
	private static final  Logger log = Logger.getLogger(LoginPage.class);
	
	@RequestMapping("/login")  
    public ModelAndView showLoginPage(HttpServletRequest request) throws Exception {  
		ModelAndView mv = new ModelAndView("login");
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setPageRequested((String)request.getAttribute("pageRequested"));
		System.out.println(" Inside showLoginPage "+loginDTO.getPageRequested());
		mv.addObject("loginDTO", loginDTO);
		return mv;     
	}  
	@RequestMapping("/loginSubmit")  
    public ModelAndView dologinSubmit(@ModelAttribute("loginDTO") LoginDTO loginData, HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		String userName=loginData.getName();
		String password=loginData.getPassword();
		String pageRequested=loginData.getPageRequested();
		pageRequested=pageRequested.substring(pageRequested.lastIndexOf("/")+1, pageRequested.length()-5);
		System.out.println(loginData.getPageRequested()+" -"+userName+" - "+password+"-"+pageRequested);
		if(userName.equals("nilesh") && password.equals("password")){
			ModelAndView mv = new ModelAndView(pageRequested);
			return mv; 
		}else{
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("loginDTO", loginData);
			return mv; 
		}
		    
	}  	
	
	@RequestMapping("/login1")  
    public ModelAndView showLoginPage1() throws Exception {  
		return new ModelAndView("login1");   
    } 
}
