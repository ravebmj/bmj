package org.bmj.userinsights.google.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Class use for BMJ google login and logout for BMJ and google application.
 * @author nilesh.kambli
 *
 */
@Controller
public class GoogleLogInController {
	
	private static final  Logger log = Logger.getLogger(GoogleLogInController.class);
	static ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	@Autowired
	private ILoginService loginService;
	
	/**
	 * This gets call when user does not have google login session in browser.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/googleLogin", method = RequestMethod.POST)
	public ModelAndView  googleLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageType=request.getParameter("pageType");
		ModelAndView mav = new ModelAndView("googleLogin");	
		request.setAttribute("pageType",pageType);
		request.setAttribute("insightId",request.getParameter("insightId"));
		
		return mav;
	}	
	
	/**
	 * This method gets call when user clicks on "Sign Out" link.
	 * @param request
	 * @param response
	 */
	@RequestMapping("/logout")  
	public String dologinOut(HttpServletRequest request) throws Exception {
		try{
		HttpSession session=request.getSession(true);
		session.setAttribute("BMJSessionToken", null);
		session.invalidate();
		}catch(Exception e)
		{
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_session"));
		}
		log.debug("Setting session value to null and rediredting to dashboard");	
		//return "redirect:dashboard.html";
		//System.out.println(" logout logout ");
		String appBaseURL=CommonUtils.getBaseUrl(request.getRequestURL().toString(),request.getRequestURI());
		log.debug(" .... appBaseURL ..... "+appBaseURL);
		//https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://linuxtestserver.acs.org:8080/bmj
		return "redirect:https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue="+appBaseURL;
	}
	/**
	 * This gets call when other google application does sign out.Here BMJ application invalidates BMJ session.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/invalidateSession")
	public @ResponseBody String invalidateSession(HttpServletRequest request) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("BMJSessionToken", null);
			session.invalidate();
			return "true";
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,
					messagesProperties.getString("error_session"));
			return "false";
		}
	}
	
}
