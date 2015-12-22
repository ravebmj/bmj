package org.bmj.userinsights.dashboard.Dao;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.dashboard.dto.RecentInsightsDto;
import org.bmj.userinsights.dashboard.dto.SearchAllInsightsDto;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class DashboardDao extends HibernateDaoSupport implements IDashboardDao{

	@Override
	public List<SearchAllInsightsDto> getSearchAllInsightsDtoLst()
			throws Exception {
		List<SearchAllInsightsDto> lstSearchAllInsightsDto = new ArrayList<SearchAllInsightsDto>();
    	
    	SearchAllInsightsDto obj1 = new SearchAllInsightsDto();
    	obj1.setSearchInsightId(1);
    	obj1.setSearchInsightName("Classic mail");
    	
    	SearchAllInsightsDto obj2 = new SearchAllInsightsDto();
    	obj2.setSearchInsightId(2);
    	obj2.setSearchInsightName("UPS Delivery");
    	
    	SearchAllInsightsDto obj3 = new SearchAllInsightsDto();
    	obj3.setSearchInsightId(3);
    	obj3.setSearchInsightName("Private Jet");
    	
    	lstSearchAllInsightsDto.add(obj1);
    	lstSearchAllInsightsDto.add(obj2);
    	lstSearchAllInsightsDto.add(obj3);
    	
		return lstSearchAllInsightsDto;
	}

	@Override
	public List<RecentInsightsDto> getRecentlyAddedInsights() throws Exception {
		List<RecentInsightsDto> lstRecentInsightsDto = new ArrayList<RecentInsightsDto>();
    	
    	RecentInsightsDto recent1 = new RecentInsightsDto();    	
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
    	lstRecentInsightsDto.add(recent3);
		return lstRecentInsightsDto;
	}
	
	
	  
}
