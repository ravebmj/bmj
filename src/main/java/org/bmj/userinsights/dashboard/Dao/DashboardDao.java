package org.bmj.userinsights.dashboard.Dao;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.DeleteInsightSchedular;
import org.bmj.userinsights.dashboard.dto.DashboardDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
/**
 * This Dashboard Dao class will handle all dashboard related queries
 * which will be executed on database,get the results and return back
 */
public class DashboardDao extends HibernateDaoSupport implements IDashboardDao{

	/**
	 * this method will get the recently added insights executing the query by modified date 
	 * and limit the results as per configuration parameter
	 */
	@Override
	public List<InsightDetailsDto> getRecentlyAddedInsights() throws Exception {
		
		String limit = CommonUtils.getConfigValue(CommonUtils.CONFIG_VALUE_RECENT_INSIGHT_LIST_SIZE);		
		List<InsightDetail> insightDetailList =  this.getHibernateTemplate().getSessionFactory().getCurrentSession().
		createQuery("select insd  from InsightDetail insd where deleteDate is null order by insd.modifiedDate desc ").setMaxResults(Integer.parseInt(limit)).list(); 
		
    	List<InsightDetailsDto> insightDetailDtoList = CommonUtils.copyProperties(insightDetailList);
    	this.getHibernateTemplate().clear();//clearing session
		return  insightDetailDtoList;
	}
	
	/**
	 * this method will get the strongest evidence insights executing the query by user count
	 * and limit the results as per configuration parameter
	 */
	@Override
	public List<InsightDetailsDto> getStrongestEvidenceInsights()
			throws Exception {
				
		String limit = CommonUtils.getConfigValue(CommonUtils.CONFIG_VALUE_RECENT_INSIGHT_LIST_SIZE);		
		List<InsightDetail> insightDetailList =  this.getHibernateTemplate().getSessionFactory().getCurrentSession().
		createQuery("select insd  from InsightDetail insd  where deleteDate is null order by insd.foundCount desc ").setMaxResults(Integer.parseInt(limit)).list();
			
		List<InsightDetailsDto> insightDetailDtoList = CommonUtils.copyProperties(insightDetailList);
    	this.getHibernateTemplate().clear();//clearing session
		
		return insightDetailDtoList;
	}
	
	
	/**
	 * Set delete date for insight
	 * @return
	 * @throws Exception
	 */
	@Override
	public void saveInsightForDeleteDate(DashboardDto dashboardDto) throws Exception {
		InsightDetail insightDetail = new InsightDetail();
		insightDetail.setId(Integer.parseInt(dashboardDto.getInsightId()));
		insightDetail = (InsightDetail)this.getHibernateTemplate().get(InsightDetail.class, insightDetail.getId());
		insightDetail.setDeleteDate(new Date());
		this.getHibernateTemplate().update(insightDetail);
			
		ApplicationContext ctx = null; 
		ctx = AppContext.getApplicationContext();
		DeleteInsightSchedular delInsightsch=(DeleteInsightSchedular)ctx.getBean("refDeleteInsightSchedular");
		delInsightsch.setId(dashboardDto.getInsightId());
		ScheduledExecutorService insightDeleteScheduler = Executors.newSingleThreadScheduledExecutor();
		insightDeleteScheduler.schedule(delInsightsch, CommonUtils.INSIGHT_DELETE_SCHEDULAR_TIME_IN_SECONDS, TimeUnit.SECONDS);
		
	}
	
	
	
	
	
	  
}
