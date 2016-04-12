package org.bmj.userinsights.login.controller;

import java.net.URLDecoder;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.BMJTokenUtility;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.entity.AdminUser;
import org.bmj.userinsights.login.dto.LoginDTO;
import org.bmj.userinsights.login.service.ILoginService;
import org.bmj.userinsights.server.BMJSessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private ILoginService loginService;

	private static final  Logger log = Logger.getLogger(LoginController.class);
	static ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	@RequestMapping(value = "/login", method = RequestMethod.GET)  
	public ModelAndView showLoginPage(HttpServletRequest request) throws Exception {  
		ModelAndView mv = new ModelAndView("login");
		try{
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setPageRequested((String)request.getAttribute("pageRequested"));
		mv.addObject("loginDTO", loginDTO);
		}catch(Exception e)
		{
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_login"));
		}
		return mv;     
	}  

	/**
	 * Set user details in session
	 * @param request
	 */	
	private void setBMJSessionToken(HttpServletRequest request,AdminUser adminUser) {
		HttpSession session=request.getSession(true);// Create new session if not exist.Or use exist session.
		BMJSessionToken bmjSessionToken=new BMJSessionToken();
		bmjSessionToken.setUserId(adminUser.getAdminuserId());
		bmjSessionToken.setUserName(adminUser.getAdminuserName());
		bmjSessionToken.setUserFirstName(adminUser.getAdminuserFirstName());
		bmjSessionToken.setUserMiddleName(adminUser.getAdminuserMiddleName());
		bmjSessionToken.setUserLastName(adminUser.getAdminuserLastName());
		bmjSessionToken.setUserFullName(adminUser.getAdminuserFirstName()+" "+adminUser.getAdminuserLastName());
		bmjSessionToken.setUserEmailAddress(adminUser.getAdminuserEmailAddress());
		bmjSessionToken.setApplicationId(adminUser.getAdminuserApplicationId());
		bmjSessionToken.setAddedDate(adminUser.getAddedDate());
		bmjSessionToken.setRoleId(adminUser.getSuperadminRole());
		log.debug("Setting value in session");
		session.setAttribute("BMJSessionToken", bmjSessionToken);
	}  	


	/**
	 * This method is stating whether 
	 * user id valid or not
	 * @param request
	 * @param response
	 * @return string
	 */
	@RequestMapping(value = "/validatingUser", method = RequestMethod.GET)
	public @ResponseBody String validatingUser(HttpServletRequest request, HttpServletResponse response) {

		AdminUser adminUser=null;
		String result= CommonUtils.INVALID_USER;
		log.debug("Validaling user  for login= "+request.getParameter("username"));
		try {
			adminUser = loginService.gettingAdminUserDetail(request.getParameter("username"));
			if(adminUser!= null &&  (encodingAndDecodingPassword(adminUser.getAdminuserPassword()).equals(URLDecoder.decode(request.getParameter("password"))) ))
			{
				log.debug(request.getParameter("username")+ " is valid user.");
				setBMJSessionToken(request,adminUser);
				result = CommonUtils.VALID_USER+ " "+adminUser.getAdminuserFirstName()+ " "+adminUser.getAdminuserMiddleName()+ " "+adminUser.getAdminuserLastName();
			}
			} 
		catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_login_validate"));
			result = "Error";
		}
		return result;

	}
	
	
	/**
	 * This method is encrypting
	 * and decrypting  the password
	 * @param request
	 * @param response
	 * @return string
	 */
	private String encodingAndDecodingPassword(String password) throws Exception {
		log.debug("Encoding and Decoding password obtained from Database  =  "+password);
		String passPhrase = loginService.gettingPasswordPassPhraseValue(CommonUtils.CONFIG_VALUE_PASSWORD_PASS_PHRASE);
		String encodedPassPhrase=BMJTokenUtility.getGeneratedEncodedToken(CommonUtils.ENCODING_KEY,passPhrase);
		String originalPassword=BMJTokenUtility.getGeneratedDecodedToken(password,
				BMJTokenUtility.getGeneratedDecodedToken(encodedPassPhrase,passPhrase));
		log.debug("Decrypted password is =  "+originalPassword);
		return originalPassword;
	}
	
	
	/**
	 * This method handling
	 * 404 error
	 * @param request
	 */
	
	@RequestMapping("/httpErrors")  
	public ModelAndView handlingNotFoundPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("ErrorPage");
		mv.addObject("error_message", messagesProperties.getString(InsightsConstants.ERROR_MESSAGE_HTTP_ERROR));
		return mv;
	}
	
	
}
