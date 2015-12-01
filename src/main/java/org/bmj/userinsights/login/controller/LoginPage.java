package org.bmj.userinsights.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.login.dto.LoginDTO;
import org.bmj.userinsights.server.BMJSessionToken;
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
		if(userName.equals("nilesh") && password.equals("password")){// If user is authenticated.Replace this with new logic.
			setBMJSessionToken(request);
			ModelAndView mv = new ModelAndView(pageRequested);
			return mv; 
		}else{
			ModelAndView mv = new ModelAndView("login");
			loginData.setName("");
			loginData.setPassword("");
			mv.addObject("loginDTO", loginData);
			return mv; 
		}
		    
	}
	/**
	 * Set user details in session
	 * @param request
	 */	
	private void setBMJSessionToken(HttpServletRequest request) {
		HttpSession session=request.getSession(true);// Create new session if not exist.Or use exist session.
		BMJSessionToken bmjSessionToken=new BMJSessionToken();
		bmjSessionToken.setUserName("nilesh");
		session.setAttribute("BMJSessionToken", bmjSessionToken);
	}  	
	
	@RequestMapping("/login1")  
    public ModelAndView showLoginPage1() throws Exception {  
		return new ModelAndView("login1");   
    } 
}
