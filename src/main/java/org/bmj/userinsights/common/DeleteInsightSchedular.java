package org.bmj.userinsights.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.service.IInsightService;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class DeleteInsightSchedular extends HibernateDaoSupport implements Runnable {

	private static final  Logger log = Logger.getLogger(DeleteInsightSchedular.class);
	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}




	@Override
	public void run()  {
		try{		
			
		  log.info("Calling DeleteInsightSchedular for insight id = "+id);
		  ApplicationContext ctx = null; 
		  ctx = AppContext.getApplicationContext();
		  IInsightService insightService=(IInsightService)ctx.getBean("insightServiceRef");
		  List<InsightDto> insightDtoList = insightService.getInsightDetailForDelete(id);
		  InsightDto insightDTO = insightDtoList.get(0);
		  if(insightDTO.getInsightDetailsDto().getDeleteDate()!=null){
			  insightService.deleteInsight(id);
		   }
		}catch(Exception e){
			CommonUtils.errorLoggging(log, e,"Error occured while deleting insight");
		}
		
	}

}
