package org.bmj.userinsights.search.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;








import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.search.dto.SearchResultDto;
import org.bmj.userinsights.search.service.ISearchService;
import org.bmj.userinsights.search.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("searchCriteria")
@Controller
public class SearchController {
	
	
	@Autowired
	private ISearchService searchService;
	
	
	/*
	 * Return Search Result Page
	 */
    @RequestMapping("/searchresults")  
    public ModelAndView showDashboard(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto,HttpServletRequest req) { 
    	
    	
    	System.out.println("in the showSearchResults "+dashboardDto.getSearchTxt());
    	
    	System.out.println("in the showSearchResults "+dashboardDto.getSearchAllInsight());
    	//String[] values = req.getParameterValues("insightId");
    	System.out.println("insight id: "+req.getParameter("insightId"));
    	
    	SearchService searchService = new SearchService();
    	SearchResultDto searchDto = searchService.getAllInsight();
    	
        return new ModelAndView("searchresults","searchDto",searchDto);  

    } 
    
    /*
     * Advance Search
     * with Parameter
     */
    @RequestMapping(value= "/advanceSearch", method=RequestMethod.POST)  
    public ModelAndView advanceSearch(HttpServletRequest req) {
    	
    	ModelAndView model = new ModelAndView("searchresults"); 
    	
    	System.out.println("keyword: "+req.getParameter("keyword"));
    	System.out.println("insightType: "+req.getParameter("insightType"));
    	System.out.println("serverity: "+req.getParameter("serverity"));
    	System.out.println("createdDate: "+req.getParameter("createdDate"));
    	System.out.println("fromDate: "+req.getParameter("fromDate"));
    	System.out.println("toDate: "+req.getParameter("toDate"));
    	

    	String fromdate = req.getParameter("fromDate");
    	String todate = req.getParameter("toDate");
    	
    	/*//boolean fromVal = checkDateValidity(fromdate);
    	//boolean toVal = checkDateValidity(todate);
    	
    	System.out.println("From value "+fromVal);
    	System.out.println("To value "+toVal);
    	
    	if(fromVal){
    		ModelAndView mv = new ModelAndView("dashboard");
    		mv.addObject("fromDate", "Please check the date");
    		return mv;
    	}
    	
    	if(fromVal){
    		ModelAndView mv = new ModelAndView("dashboard");
    		mv.addObject("toDate", "Please check the date");
    		return mv;
    	}*/
    	
        return model; 
    }    
    
    /*
     * Advance Search
     * with Parameter
     */
    @RequestMapping(value= "/dashBoardSearch", method=RequestMethod.POST)  
    public ModelAndView dashBoardSearch(HttpServletRequest request) {
    	
    	ModelAndView model = new ModelAndView("searchresults");  
    	
        return model; 
    }
     
    
    private boolean checkDateValidity(String dateString){
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
    	Date currendate = null;
		try {
			date = sdf.parse(dateString);
			currendate = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    	System.out.println(sdf.format(date));
    	System.out.println(sdf.format(currendate));

    	
    	Calendar cal1 = Calendar.getInstance();
    	Calendar cal2 = Calendar.getInstance();
    	
    	cal1.setTime(date);
    	cal2.setTime(currendate);
    	
    	if(cal1.after(cal2)){
    		System.out.println("Date1 is after Date2");
    		return true;
    		
    	}
    	
    	/*if(cal1.before(cal2)){
    		System.out.println("Date1 is before Date2");
    		return -1;
    		
    	}
    	
    	if(cal1.equals(cal2)){
    		System.out.println("Date1 is equal Date2");
    		return 0;
    	}*/


    	return false;
    }
    
}