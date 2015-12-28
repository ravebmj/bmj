package org.bmj.userinsights.insight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bmj.userinsights.dashboard.dto.DashboardDTO;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dto.TagEditorJson;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.entity.Project;
import org.bmj.userinsights.entity.Tag;
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
		insightDTO.setId(0);// New Insight
       return new ModelAndView("createInsight","mInsightDTO",insightDTO);
    } 
	
	/*
	 * Display data of
	 * particular insight in 
	 * view mode
	 */
	@RequestMapping(value="/viewinsight",method=RequestMethod.POST)  
    public ModelAndView viewInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto, @RequestParam("insightId") String insightId) throws Exception {
		
		System.out.println("in the view Insight");
		
		List<Product> lstProduct = new ArrayList<Product>();
		List<Project>  lstProject = new ArrayList<Project>();
		List<Tag>     lstTag = new ArrayList<Tag>();
		
		ModelAndView mav = new ModelAndView("viewInsight");			
		List<InsightDTO> insightDtoList = insightService.getInsightDetails(insightId);//to get insight details
		InsightDTO insightDTO = insightDtoList.get(0);
		//List<InsightTypesDto> insightTypesDtoList = insightService.getInsightTypes(insightId);
		//Map<Integer,InsightTypesDto> insightTypesmap = prepareInsightTypesMap(insightTypesDtoList);
		//InsightDTO insightDto = getInsightDtoObj(insightTypesmap);
		
		
		List<InsightProduct> lstInsightProduct = new ArrayList<InsightProduct>(insightDTO.getProductsSet());		
		if(lstInsightProduct!=null && lstInsightProduct.size()>0){
			for(InsightProduct insightProduct : lstInsightProduct){
				lstProduct.add(insightProduct.getProduct());
			}
		}
		
		List<InsightProject> lstInsightProjects = new ArrayList<InsightProject>(insightDTO.getProjects());		
		if(lstInsightProjects!=null && lstInsightProjects.size()>0){
			for(InsightProject insightProject : lstInsightProjects){
				lstProject.add(insightProject.getProject());
			}
		}
		
		List<InsightTag> lstInsightTag = new ArrayList<InsightTag>(insightDTO.getTags());		
		if(lstInsightTag!=null && lstInsightTag.size()>0){
			for(InsightTag insightTag : lstInsightTag){
				lstTag.add(insightTag.getTag());
			}
		}
		
		insightDTO.setProductsList(lstProduct);
		insightDTO.setProjectsList(lstProject);
		insightDTO.setTagList(lstTag);
		mav.addObject("mInsightDTO", insightDTO);
		
       return mav;
    }  
	
	/*
	 * Display data of
	 * particular insight in 
	 * view mode
	 */
	@RequestMapping(value="/editinsight",method=RequestMethod.GET)  
    public ModelAndView editInsight(@ModelAttribute("dashboardDto") DashboardDTO dashboardDto, @RequestParam("insightId") String insightId) throws Exception {
		
		System.out.println("in the showViewInsight"+insightId);
		InsightDTO insightDTO=new InsightDTO();
		insightDTO.setId(new Integer(insightId));// New Insight
        return new ModelAndView("editInsight","mInsightDTO",insightDTO);
    }  
	
	@RequestMapping(value="/saveinsight",method=RequestMethod.POST)  
    public ModelAndView saveInsight(@ModelAttribute("mInsightDTO") InsightDTO insightDTO) throws Exception {
		
		System.out.println("---- Inside Save -----"+insightDTO.getId());
        return new ModelAndView("editInsight","mInsightDTO",insightDTO);
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
	
	
	private Map<Integer,InsightTypesDto> prepareInsightTypesMap(List<InsightTypesDto> insightTypesDtoList){
		Map<Integer,InsightTypesDto> insightTypesMap = null;
		insightTypesMap = new HashMap<Integer,InsightTypesDto>();
		
		
		return insightTypesMap;
		
	}
	
	private InsightDTO getInsightDtoObj(Map<Integer,InsightTypesDto> insightTypesmap){
		InsightDTO insightDTO = null;
		if(insightTypesmap!=null){
			
		}
		
		return insightDTO;
	}
      
}
