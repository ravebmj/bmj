package org.bmj.userinsights.dashboard.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.dashboard.dto.StrongestEvidenceInsightDTO;
import org.bmj.userinsights.entity.CodeListName;
import org.bmj.userinsights.entity.CodelistCodeDecode;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.InsightProject;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class DashboardDao extends HibernateDaoSupport implements IDashboardDao{

	@Override
	public List<InsightTypesDto> getSearchAllInsightsDtoLst()
			throws Exception {
		List<InsightTypesDto> lstSearchAllInsightsDto = new ArrayList<InsightTypesDto>();
    	
    	/*InsightTypesDto obj1 = new InsightTypesDto();
    	obj1.setSearchInsightId(1);
    	obj1.setSearchInsightName("Classic mail");
    	
    	InsightTypesDto obj2 = new InsightTypesDto();
    	obj2.setSearchInsightId(2);
    	obj2.setSearchInsightName("UPS Delivery");
    	
    	InsightTypesDto obj3 = new InsightTypesDto();
    	obj3.setSearchInsightId(3);
    	obj3.setSearchInsightName("Private Jet");
    	
    	lstSearchAllInsightsDto.add(obj1);
    	lstSearchAllInsightsDto.add(obj2);
    	lstSearchAllInsightsDto.add(obj3);*/
    	
		return lstSearchAllInsightsDto;
	}

	@Override
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception {
		List<RecentInsightsDto> lstRecentInsightsDto = new ArrayList<RecentInsightsDto>();
		List<InsightTypesDto> lstInsightTypesDto = null;
    	StringBuffer query = new StringBuffer();
		query.append("select insd ");
		query.append(" from InsightDetail insd  ");
		query.append(" order by insd.modifiedDate desc ");
		
    	
    	List<InsightDetail> insightDetailList = (List<InsightDetail>) this.getHibernateTemplate().find(query.toString());
    	System.out.println("--------------insightDetailList size ------------------"+insightDetailList.size());
    	
    	
    	lstInsightTypesDto = getInsightTypesCodeListDecodedNames(InsightsConstants.INSIGHT_TYPE_CODE_LIST_NAME,InsightsConstants.APPLICATION_ID);// populate the possible values for the insight types dropdown in Advanced Search section
    	
    	
    	
    	Map<Integer,InsightTypesDto> map = getCodeListMap(lstInsightTypesDto);
    	
    	if(insightDetailList!=null && insightDetailList.size()>0){
    		
    		 for(InsightDetail insightDetail : insightDetailList){    			
    			 RecentInsightsDto recentInsightsDto = new RecentInsightsDto();
    			 recentInsightsDto.setInsightId(insightDetail.getId());
    			 recentInsightsDto.setInsightName(insightDetail.getTitle());
    			 recentInsightsDto.setType(map.get(insightDetail.getType()).getInsightTypeName());
    			 recentInsightsDto.setProjects(new ArrayList<InsightProject>(insightDetail.getProjects()));
    			 recentInsightsDto.setLastEdited(getDDMMMYYYY(insightDetail.getModifiedDate()));
    			 System.out.println("******************* date *********************"+insightDetail.getModifiedDate().toString());
    			 lstRecentInsightsDto.add(recentInsightsDto);
    		 }
    	}
    	
    	
    	
    	
    	
		return lstRecentInsightsDto;
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

	@Override
	public List<StrongestEvidenceInsightDTO> getStrongestEvidenceInsights()
			throws Exception {
		List<StrongestEvidenceInsightDTO> lstStrongestEvidenceInsightDTO = new ArrayList<StrongestEvidenceInsightDTO>();
		List<InsightTypesDto> lstInsightTypesDto = null;
    	StringBuffer query = new StringBuffer();
		query.append("select insd ");
		query.append(" from InsightDetail insd  ");
		query.append(" order by insd.foundCount desc ");
		
    	
    	List<InsightDetail> insightDetailList = (List<InsightDetail>) this.getHibernateTemplate().find(query.toString());
    	
    	if(insightDetailList!=null && insightDetailList.size()>0){
    		
   		 for(InsightDetail insightDetail : insightDetailList){    			
   			StrongestEvidenceInsightDTO strongestEvidenceInsightDTO = new StrongestEvidenceInsightDTO();
   			strongestEvidenceInsightDTO.setInsightId(insightDetail.getId());
   			strongestEvidenceInsightDTO.setInsightName(insightDetail.getTitle());   			
   			strongestEvidenceInsightDTO.setProjects(new ArrayList<InsightProject>(insightDetail.getProjects()));
   			strongestEvidenceInsightDTO.setFoundCount(insightDetail.getFoundCount());
   			lstStrongestEvidenceInsightDTO.add(strongestEvidenceInsightDTO);
   		 }
   	}
    	
    	
		return lstStrongestEvidenceInsightDTO;
	}
	
	
	
	public String getDDMMMYYYY(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		//Date date = null;
		String dateStr = null;
		//try {
			//date = sdf.parse(dateString);
			dateStr = sdf.format(date);
		/*} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return dateStr;
	}
	
	
	  
}
