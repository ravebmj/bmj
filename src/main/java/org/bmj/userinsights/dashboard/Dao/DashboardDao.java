package org.bmj.userinsights.dashboard.Dao;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
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
    	
    	/*RecentInsightsDto recent1 = new RecentInsightsDto();    	
    	recent1.setInsightId(1);
    	recent1.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent1.setProjectName("BMJ print design");
    	recent1.setType("User Insight");
    	recent1.setLastEdited("05-Jan-2015");
    	
    	RecentInsightsDto recent2 = new RecentInsightsDto(); 
    	recent2.setInsightId(2);
    	recent2.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent2.setProjectName("BMJ print design");
    	recent2.setType("User Insight");
    	recent2.setLastEdited("05-Jan-2015");
    	
    	RecentInsightsDto recent3 = new RecentInsightsDto(); 
    	recent3.setInsightId(3);
    	recent3.setInsightName("Nobody reads the BMJ cover to cover Nobody reads the BMJ cover to cover...");
    	recent3.setProjectName("BMJ print design");
    	recent3.setType("User Insight");
    	recent3.setLastEdited("05-Jan-2015");
    	
    	lstRecentInsightsDto.add(recent1);
    	lstRecentInsightsDto.add(recent2);
    	lstRecentInsightsDto.add(recent3);*/
    	
    	StringBuffer query = new StringBuffer();
		query.append("select insd ");
		query.append(" from InsightDetail insd ");
		query.append(" order by insd.modifiedDate desc ");
		
    	
    	List<InsightDetail> insightDetailList = (List<InsightDetail>) this.getHibernateTemplate().find(query.toString());
    	System.out.println("--------------insightDetailList size ------------------"+insightDetailList.size());
    	
    	if(insightDetailList!=null && insightDetailList.size()>0){
    		
    		 for(InsightDetail insightDetail : insightDetailList){    			
    			 RecentInsightsDto recentInsightsDto = new RecentInsightsDto();
    			 recentInsightsDto.setInsightId(insightDetail.getId());
    			 recentInsightsDto.setInsightName(insightDetail.getTitle());    			 
    			 recentInsightsDto.setProjects(new ArrayList<InsightProject>(insightDetail.getProjects()));
    			 lstRecentInsightsDto.add(recentInsightsDto);
    		 }
    	}
    	
    	
    	
    	
    	
		return lstRecentInsightsDto;
	}
	
	
	  
}
