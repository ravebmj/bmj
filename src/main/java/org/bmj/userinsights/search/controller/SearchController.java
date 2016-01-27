package org.bmj.userinsights.search.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.PdfReportUtility;
import org.bmj.userinsights.dashboard.dto.DashboardDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.insight.service.IInsightService;
import org.bmj.userinsights.search.dto.SearchResultDto;
import org.bmj.userinsights.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
/**
 * This controller class is used for all search related actions
 */
@SessionAttributes({"searchCriteria","dashboardDto"})
@Controller
public class SearchController {
	
	private static final  Logger log = Logger.getLogger(SearchController.class);
	/**
     * Messages resource bundle.
     */
    private static final ResourceBundle rmb = ResourceBundle.getBundle("report_messages");
    ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
    /**
     * injecting search service class with autowired, spring container will take of dependency injection
     */
	@Autowired
	private ISearchService searchService;
	/**
     * injecting insight service class with autowired, spring container will take of dependency injection
     */
	@Autowired
	private IInsightService insightService;
	
	private byte[] pdfContent; 
	
	/**
	 * This method will call when click on viewall link on dashboard page.
	 *  either recently added insights or strongest evidence insights and 
	 *  get all the search results based recently added or strongest evidence
	 * @param sortType
	 * @param dashboardDto
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchresults", method = RequestMethod.GET)
	public ModelAndView showSearchResults(
			@RequestParam("sortType") String sortType,
			@ModelAttribute("dashboardDto") DashboardDto dashboardDto,
			HttpServletRequest req) {
    	SearchResultDto searchDto=null;
		try {
			log.info("starting of the showSearchResults method in SearchController");
			searchDto = searchService.getAllInsight();
			if(sortType!=null && sortType.equals("recent")){
				log.debug("view all sort type "+sortType);
				searchDto.setSortFlag(sortType); // this sort flag is using in searchresults.js
			}else if(sortType!=null && sortType.equals("evidence")){
				log.debug("view all sort type "+sortType);
				searchDto.setSortFlag(sortType); // this sort flag is using in searchresults.js
			}
			log.info("ending of the showSearchResults method in SearchController");
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_result_search"));
		}
    	
        return new ModelAndView("searchresults","searchDto",searchDto);  

    } 
    
    /**
     * This method will call from either click on quick search or Advance search
     * Get the search results based on selected search criteria
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/advanceSearch", method=RequestMethod.GET)  
    public ModelAndView advanceSearch(HttpServletRequest req) {
    	log.info("starting of the advanceSearch method in SearchController");
    	List<InsightDetailsDto> lstInsightDetailsDto = null;
    	ModelAndView model = new ModelAndView("searchresults");    	
    	SearchResultDto searchDto = new SearchResultDto();    	
    	String keyword = req.getParameter("keyword");
    	String insightType = req.getParameter("insightType");
    	String serverity = req.getParameter("serverity");
    	String dateRangeOpt = req.getParameter("createdDate");
    	String fromDate = req.getParameter("fromDate");
    	String toDate = req.getParameter("toDate"); 
    	String serachType = req.getParameter("serachType"); 
    	log.debug("keyword "+keyword);
    	log.debug("insightType "+insightType);
    	log.debug("serverity "+serverity);
    	log.debug("dateRangeOpt "+dateRangeOpt);
    	log.debug("fromDate "+dateRangeOpt);
    	log.debug("toDate "+dateRangeOpt);
    	log.debug("serachType "+serachType);
    	
    	try {
    		CommonUtils.setCodeListInsightMap(CommonUtils.getCodeListMap(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID));
    		lstInsightDetailsDto = searchService.getSearchResults(keyword,insightType,serverity,dateRangeOpt,fromDate,toDate);
    		searchDto.setSearchResult(lstInsightDetailsDto);
    		if(keyword != null && !keyword.isEmpty()){
    			searchDto.setWeightageSortFlag("weightageSort");
    		}
    		// resetting selected search or advance search options to the fields
    		searchDto.setKeyword(keyword);
    		searchDto.setInsightType(insightType);
    		searchDto.setServerity(serverity);
    		searchDto.setDateRangeOpt(dateRangeOpt);
    		searchDto.setFromDate(fromDate);
    		searchDto.setToDate(toDate);
    		searchDto.setSerachType(serachType);
    		model.addObject("searchDto", searchDto);    		
		} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_advance_search"));		
		}
    	log.info("ending of the advanceSearch method in SearchController");
        return model; 
    }    
    
    /**
     * This method will call when click on any of the links project,product and tag 
     * on either on dashboard page or on search results page
     * Get the search results related to clicked link
     * @param request
     * @param type
     * @param id
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/dashBoardSearch", method = RequestMethod.GET)
	public ModelAndView dashBoardSearch(HttpServletRequest request,
			@RequestParam("searchType") String type,
			@RequestParam("id") String id) {
		log.info("starting of the dashBoardSearch method in SearchController");
    	ModelAndView model = new ModelAndView("searchresults");
    	SearchResultDto searchDto = new SearchResultDto();
    	try{
	    	List<InsightDetailsDto> insightDetail = null;
	    	String keyword = null;
	    	log.debug("type "+type);
	    	if(type!=null && type.equals("tag")){    		
	    		insightDetail = getInsightForTag(Integer.valueOf(id));
	    		keyword = getInsightKeyWordForTag(Integer.valueOf(id));
	    	}else if(type!=null && type.equals("product")){    		
	    		insightDetail = getInsightForProduct(Integer.valueOf(id));
	    		keyword = getInsightKeyWordForProduct(Integer.valueOf(id));
	    	}else if(type!=null && type.equals("project")){    		
	    		insightDetail = getInsightForProject(Integer.valueOf(id));
	    		keyword = getInsightKeyWordForProject(Integer.valueOf(id));
	    	}	    	
	    	searchDto.setSearchResult(insightDetail);
	    	searchDto.setSerachType(type);
	    	model.addObject("searchKeyword", keyword);
	    	model.addObject("searchDto", searchDto);
    	} catch (Exception e) {
			CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_dashboard_search"));		
		}
    	log.info("ending of the dashBoardSearch method in SearchController");
        return model; 
    }
     
       
   /**
    * This method will call when click on Download option on search results page
    * Get the data for selected items and generate the PDF report
    * @param request
    * @param response
    * @param selectedInsights
    * @return
    * @throws Exception
    */
	@RequestMapping(value = "/downloadReport", method = RequestMethod.POST)
	public @ResponseBody String downloadReport(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("selectedInsights") String selectedInsights) {
		log.info("starting of the downloadReport method in SearchController");
    	List<InsightDetailsDto> insightDetail = null;
    	this.setPdfContent(null);
    	try{
        	insightDetail = searchService.getInsightsForDownloadReport(request.getParameter("selectedInsights"));
        	PdfReportUtility report = PdfReportUtility.getInstance(insightDetail,
    				CommonUtils.getCodeListMap(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID),
    				CommonUtils.getCodeListMap(InsightsConstants.SEVERITY_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID));
    		
        	this.setPdfContent(report.generateReport(response,request));
           return "{\"status\":\"success\"}";
    	}catch(Exception e){
    		CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_download_report"));
    	}
    	log.info("ending of the downloadReport method in SearchController");
    	 return "{\"status\":\"failed\"}";
    }
    /**
     * Generate popup dialog to prompt the user for download report
     * @param request
     * @param response
     * @param selectedInsights
     * @throws Exception
     */
	@RequestMapping(value = "/downloadReportDisplay", method = RequestMethod.POST)
	public void downloadReportDisplay(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("selectedInsights") String selectedInsights) {
		log.info("starting of the downloadReportDisplay method in SearchController");
    	try{
    	String fileName = rmb.getString("report_pdf_file_name")+CommonUtils.getCurrentDateString()+".pdf";
    	CommonUtils.writeFiletoStream(this.getPdfContent(), fileName,
				response,"pdf");
    	}catch(Exception e){
    		CommonUtils.errorLoggging(log, e,messagesProperties.getString("error_display_report"));
    		try {
				CommonUtils.createErrorPDF(response);
			} catch (Exception e1) {
				CommonUtils.errorLoggging(log, e1,messagesProperties.getString("error_display_report"));
			}
    	}
    	log.info("end of the downloadReportDisplay method in SearchController");
    }
    
        
    /**
     * Get Insight List for Product Id
     * @param productId
     * @return
     * @throws Exception
     */
    private List<InsightDetailsDto> getInsightForProduct(Integer productId) throws Exception{
    	
    	return searchService.getInsightForProducts(productId);
    }
    /**
     * Get Insight List for Project Id
     * @param projectId
     * @return
     * @throws Exception
     */
    private List<InsightDetailsDto> getInsightForProject(Integer projectId) throws Exception{
    	
    	return searchService.getInsightsForProject(projectId);
    }
    
    /**
     * Get Insight List for Tag Id
     * @param tagId
     * @return
     * @throws Exception
     */
    private List<InsightDetailsDto> getInsightForTag(Integer tagId) throws Exception{
    	
    	return searchService.getInsighstForTag(tagId);
    }
    
    /**
     * Get the tag name for tag id
     * @param tagId
     * @return
     * @throws Exception
     */
    private String getInsightKeyWordForTag(Integer tagId) throws Exception{
    	
    	return searchService.getInsightKeyWordForTag(tagId);
    }
    
   /**
    * Get the product name for product id
    * @param productId
    * @return
    * @throws Exception
    */
   private String getInsightKeyWordForProduct(Integer productId) throws Exception{
    	
    	return searchService.getInsightKeyWordForProduct(productId);
    }
   /**
    * Get the project name for project id
    * @param projectId
    * @return
    * @throws Exception
    */
   private String getInsightKeyWordForProject(Integer projectId) throws Exception{
	
	return searchService.getInsightKeyWordForProject(projectId);
   }
    
   
	/**
	 * @return the pdfContent
	 */
	public byte[] getPdfContent() {
		return pdfContent;
	}

	/**
	 * @param pdfContent the pdfContent to set
	 */
	public void setPdfContent(byte[] pdfContent) {
		this.pdfContent = pdfContent;
	}	
   	
   	
}