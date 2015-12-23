package org.bmj.userinsights.search.controller;

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
    public ModelAndView advanceSearch(HttpServletRequest request) {
    	
    	ModelAndView model = new ModelAndView("searchresults");  
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
     
    
}