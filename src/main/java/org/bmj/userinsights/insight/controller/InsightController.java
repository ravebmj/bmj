package org.bmj.userinsights.insight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.dto.TagEditorJson;
import org.bmj.userinsights.insight.dto.InsightDTO;
import org.bmj.userinsights.insight.service.IInsightService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class InsightController {
	private static final  Logger log = Logger.getLogger(InsightController.class);
	
	@Autowired
	private IInsightService insightService; 
	
	
	@RequestMapping("/createinsight")  
    public ModelAndView createInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto) throws Exception {
		System.out.println("in the showCreateInsight");
		InsightDTO insightDTO=new InsightDTO();
       return new ModelAndView("createInsight","mInsightDTO",insightDTO);
    } 
	
	/*
	 * Display data of
	 * particular insight in 
	 * view mode
	 */
	@RequestMapping(value="/viewinsight",method=RequestMethod.POST)  
    public ModelAndView viewInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto, @RequestParam("insightId") String insightId) throws Exception {
		
		System.out.println("in the showCreateInsight");
		
       return new ModelAndView("viewInsight","dashboardDto",dashboardDto);
    }  
	
	/*
	 * Display data of
	 * particular insight in 
	 * view mode
	 */
	@RequestMapping(value="/editinsight",method=RequestMethod.POST)  
    public ModelAndView editInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto, @RequestParam("insightId") String insightId) throws Exception {
		
		System.out.println("in the showCreateInsight");
		
       return new ModelAndView("viewInsight","dashboardDto",dashboardDto);
    }  
	
	/**
	 * Display auto complete list of product when user type product name.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listProduct", method = RequestMethod.POST)
	public @ResponseBody String listProduct(HttpServletRequest request, HttpServletResponse response) {
		
		String prodName=request.getParameter("prodName");
		System.out.println(" ........ ************* ........"+prodName);

		
		List lstProduct = new ArrayList();
		
	
		TagEditorJson te1=new TagEditorJson();
		te1.setId("1");
		te1.setText("Journals");
		
		TagEditorJson te2=new TagEditorJson();
		te2.setId("2");
		te2.setText("e-journals");
		

		
		lstProduct.add(te1);
		lstProduct.add(te2);


	
		JSONArray jsonA = new JSONArray();
		jsonA.put(lstProduct);
		
		
		String strJson=jsonA.toString();
		String products=strJson.substring(1, strJson.length() - 1);

		System.out.println("  SUPERB EXCELLENT !!!!!!!!!!!!!   "+products);
		
		return products;
	}

}
