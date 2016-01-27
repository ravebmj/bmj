package org.bmj.userinsights.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.InsightsConstants;

public class RequestMonitorFilter implements Filter {
	private static final  Logger log = Logger.getLogger(RequestMonitorFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean isAuthenticationRequire=false;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session=req.getSession(false);
		String pageRequested = req.getRequestURL().toString();
		isAuthenticationRequire = checkPageAuthentication(
				isAuthenticationRequire, pageRequested);
		if(isAuthenticationRequire){// Page require authentication.
			if(session!=null && session.getAttribute("BMJSessionToken")!=null){//Session is active with current login user details.
				log.debug(" Session is active for Authenticated pages.  "+pageRequested);
				chain.doFilter(request, response);
			}else{// session is deactive.
				log.debug(" Authentication Required "+pageRequested);
				req.setAttribute("pageRequested", pageRequested);
	        	RequestDispatcher dispatcher =
					req.getRequestDispatcher("/login.html");
				dispatcher.forward(req, res);
			}

		}else{//Page not required authentication.
			log.debug(" Authentication Not Required "+pageRequested);
			chain.doFilter(request, response);
		}
		

	}
	/**
	 * Method checks if current page required authentication or not.
	 * @param isAuthenticationRequire : If true page need to be authenticate.
	 * @param pageRequested
	 * @return
	 */
	private boolean checkPageAuthentication(boolean isAuthenticationRequire,
			String pageRequested) {
		// Check if page need authentication.
		for (int i=0;i<InsightsConstants.arrAuthenticationPages.length;i++){//loop through authenticated pages.
			if(pageRequested.indexOf(InsightsConstants.arrAuthenticationPages[i])!=-1){// if url of current page contains text which present inside list of authenticated pages.
				isAuthenticationRequire=true;
				break;
			}
		}
		return isAuthenticationRequire;
	}
	
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
