package org.bmj.userinsights.search.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.entity.CodeListName;
import org.bmj.userinsights.entity.CodelistCodeDecode;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.search.dto.SearchResultDetailDto;
import org.bmj.userinsights.search.dto.SearchResultDto;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class SearchDao extends HibernateDaoSupport implements ISearchDao{
	public void searchProduct(){
		 List<InsightDetail> list = null;
		   System.out.println("Searching for products...");
		    try {
		    	list = (List<InsightDetail>)this.getHibernateTemplate()
						.find("select distinct id from "+
                              "InsightDetail id LEFT JOIN id.products as ip " +
                              "LEFT JOIN id.projects as ipj " +
                              "LEFT JOIN id.tags as it " +
                              "INNER JOIN ip.product as prod INNER JOIN ipj.project as proj INNER JOIN it.tag as tag " +
                              "where ((id.title like '%insight1%') OR (id.description like '%insight1%') " +
                              "OR (prod.name like '%journal%') OR (proj.name like '%journal%') OR (tag.name like '%journal%')) " +
                              "AND id.insightApplicationID = 1 " +
                              //Below are the conditional additions.
                              "AND id.type = 1 AND id.insightServerity = 1 " +
                              "AND (id.addedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE)) " +
                              "AND (id.modifiedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE))");        
		       
		       /* for(InsightDetail employee : list) {
		            System.out.println("Emaployee Name: " + employee.getTitle());
		            Set<InsightProduct> mobileSet = employee.getProducts();
		            System.out.println("Mobile Numbers: ");
		            for(InsightProduct mobile : mobileSet) {
		                System.out.println(mobile.getProduct().getName());
		            }
		        }*/
		    	if(null != list){
		    		System.out.println(list.size());
		    	}
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {		       
		    }
		/*List<InsightProduct> list = null;
		   
	    try {
	    	list = (List<InsightProduct>)this.getHibernateTemplate()
					.find("SELECT ids FROM InsightProduct ids");        
	       
	        for(InsightProduct employee : list) {
	            System.out.println("Emaployee Name: " + employee.getProductId());
	            System.out.println("Emaployee Name1: " + employee.getInsightDetail().getTitle());	            
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {		       
	    }*/
	}

	@Override
	public List<SearchResultDetailDto> getSearchResults(String keyword,
			String insightType, String serverity, String dateRangeOpt,
			String fromDate, String toDate) throws Exception {
		 List<InsightDetail> list = null;
		 List<InsightTypesDto> lstInsightTypesDto = null;
		 List<SearchResultDetailDto> searchResultDetailDtoList = new ArrayList<SearchResultDetailDto>();
		 String journal = "";
		 StringBuffer sbQuery = new StringBuffer();
		 
		 sbQuery.append("select distinct id from InsightDetail id LEFT JOIN id.products as ip  ");
		 sbQuery.append("LEFT JOIN id.projects as ipj LEFT JOIN id.tags as it  ");
		 sbQuery.append("INNER JOIN ip.product as prod INNER JOIN ipj.project as proj INNER JOIN it.tag as tag ");
		 sbQuery.append("where ((id.title like '%"+keyword+"%') OR (id.description like '%"+keyword+"%') ");
		 sbQuery.append("OR (prod.name like '%"+journal+"%') OR (proj.name like '%"+journal+"%') OR (tag.name like '%"+journal+"%')) ");
		 sbQuery.append("AND id.insightApplicationID = 1 ");
		 
		/* if(insightType!=null && serverity!=null && Integer.valueOf(insightType)!=0 && Integer.valueOf(serverity)!=0){
			 sbQuery.append("AND id.type = 1 AND id.insightServerity = 1 ");
		 }*/
		 
		 
		 
		/* list = (List<InsightDetail>)this.getHibernateTemplate()
					.find("select distinct id from "+
                       "InsightDetail id LEFT JOIN id.products as ip " +
                       "LEFT JOIN id.projects as ipj " +
                       "LEFT JOIN id.tags as it " +
                       "INNER JOIN ip.product as prod INNER JOIN ipj.project as proj INNER JOIN it.tag as tag " +
                       "where ((id.title like '%insight1%') OR (id.description like '%insight1%') " +
                       "OR (prod.name like '%journal%') OR (proj.name like '%journal%') OR (tag.name like '%journal%')) " +
                       "AND id.insightApplicationID = 1 " +
                       //Below are the conditional additions.
                       "AND id.type = 1 AND id.insightServerity = 1 " +
                       "AND (id.addedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE)) " +
                       "AND (id.modifiedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE))"); */
		 
		 list = (List<InsightDetail>)this.getHibernateTemplate()
					.find(sbQuery.toString());
		 
		 lstInsightTypesDto = getInsightTypesCodeListDecodedNames(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);// populate the possible values for the insight types dropdown in Advanced Search section
	    	
	    	
	    	
	    	Map<Integer,InsightTypesDto> map = getCodeListMap(lstInsightTypesDto);
		 
		 if(list!=null && list.size()>0){
			 for(InsightDetail insightDetail : list){
				 SearchResultDetailDto searchResultDetailDto = new SearchResultDetailDto();
				 searchResultDetailDto.setInsightId(insightDetail.getId());
				 searchResultDetailDto.setTitle(insightDetail.getTitle());
				 searchResultDetailDto.setType(map.get(insightDetail.getType()).getInsightTypeName());
				 searchResultDetailDto.setProjects(new ArrayList<InsightProject>(insightDetail.getProjects()));
				 searchResultDetailDto.setProducts(new ArrayList<InsightProduct>(insightDetail.getProducts()));
				 searchResultDetailDto.setTags(new ArrayList<InsightTag>(insightDetail.getTags()));
				 searchResultDetailDto.setLastEditedDate(CommonUtils.getDDMMMYYYY(insightDetail.getModifiedDate()));
				 searchResultDetailDto.setFoundCount(insightDetail.getFoundCount());
				 searchResultDetailDtoList.add(searchResultDetailDto);
			 }
		 }
		 
		return searchResultDetailDtoList;
	}
	
	
	public Map<Integer,InsightTypesDto> getCodeListMap(List<InsightTypesDto> lstInsightTypesDto){
		Map<Integer,InsightTypesDto> map = new HashMap<Integer,InsightTypesDto>();
		if(lstInsightTypesDto!=null && lstInsightTypesDto.size()>0 ){
			for(InsightTypesDto insightTypesDto :lstInsightTypesDto){
				map.put(insightTypesDto.getInsightTypeId(), insightTypesDto);
			}
		}
		
		
		
		return map;
	}
	
	
	 private List<InsightTypesDto> getInsightTypesCodeListDecodedNames(String codelistName,String applicationId) throws Exception{
	    	List<SelectValuesDto> decodedNamesDtoLst = getSelectValuesDtoLst(codelistName,applicationId);
	    	List<InsightTypesDto> lstInsighsTypes = new ArrayList<InsightTypesDto>();
	    	if(decodedNamesDtoLst!=null && decodedNamesDtoLst.size()>0){
	    		for(SelectValuesDto decodedObj:decodedNamesDtoLst){
	    			InsightTypesDto insightTypesDto = new InsightTypesDto();    			
	    			insightTypesDto.setInsightTypeId(decodedObj.getCodeDecodedCode());
	    			insightTypesDto.setInsightTypeName(decodedObj.getCodeDecodedName());
	    			lstInsighsTypes.add(insightTypesDto);
	    		}
	    	}
	    	
	    	
	    	return lstInsighsTypes;
	    	
	    }
	
	
	
	public List<SelectValuesDto> getSelectValuesDtoLst(String name,
			String applicationId) throws Exception {
		List<SelectValuesDto> DecodedNamesDtoLst = new ArrayList<SelectValuesDto>();
		CodeListName codelistNameObj = null;
		
		List returnVal = this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("CodelistName.getCodelistName",
						new String[]{"name","applicationId"},new Object[]{name,Integer.valueOf(applicationId)});
		
		if(returnVal!=null && returnVal.size()>0){
			codelistNameObj = (CodeListName) returnVal.get(0);
		}
		
		if(codelistNameObj!=null){
			List<CodelistCodeDecode> lstCodelistCodeDecode = (List<CodelistCodeDecode>) this.getHibernateTemplate()
					.findByNamedQueryAndNamedParam("CodelistCodeDecode.getCodelistCodeDecode",
							new String[]{"codelistId"},new Object[]{codelistNameObj.getCodelistId()});	
			if(lstCodelistCodeDecode!=null && lstCodelistCodeDecode.size()>0){
				
				for(CodelistCodeDecode codeDecode : lstCodelistCodeDecode){
					SelectValuesDto dtoObj = new SelectValuesDto();
					dtoObj.setCodeDecodedCode(codeDecode.getCodeDecodeCode());
					dtoObj.setCodeDecodedName(codeDecode.getCodeDecodeDecode());
					DecodedNamesDtoLst.add(dtoObj);
				}
			}
		}
		
		
		return DecodedNamesDtoLst;
	}
}
