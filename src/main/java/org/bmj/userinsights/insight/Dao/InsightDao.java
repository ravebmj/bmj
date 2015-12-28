package org.bmj.userinsights.insight.Dao;

import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.dashboard.dto.InsightTypesDto;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.insight.dto.InsightDTO;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class InsightDao extends HibernateDaoSupport implements IInsightDao{
	
	/**
	 * Get insight details and related objects and populate in to view insight page
	 */
	public List<InsightDTO> getInsightDetails(String id) throws Exception{
		List<InsightDTO> insightDtoList = null;
		List<InsightDetail> retList = (List<InsightDetail>) this
				.getHibernateTemplate()
				.findByNamedQueryAndNamedParam(
						"InsightDetail.getInsightDetailsById",
						new String[] { "id" }, new Object[] { new Integer(id) });
		insightDtoList = new ArrayList<InsightDTO>();
		if(retList!=null && retList.size()>0){
			InsightDTO insightDto = new InsightDTO();
			 for(InsightDetail insightDetail : retList){
				 insightDto.setId(insightDetail.getId());
				 insightDto.setTitle(insightDetail.getTitle());
				 insightDto.setDescription(insightDetail.getDescription());
				 insightDto.setProducts(null);
				 insightDto.setProductsList(null);
				 insightDto.setAddedDate(CommonUtils.getDDMMMYYYY(insightDetail.getAddedDate()));
				 insightDto.setFoundDate(CommonUtils.getDDMMMYYYY(insightDetail.getFoundDate()));
				 insightDto.setFoundCount(insightDetail.getFoundCount());
				 insightDto.setInsightServerity(insightDetail.getInsightServerity());
				 insightDto.setProductsSet(insightDetail.getProducts());
				 insightDto.setProjects(insightDetail.getProjects());
				 insightDto.setTags(insightDetail.getTags());
				 
				 
				 insightDtoList.add(insightDto);
			 }
		}
		
		return insightDtoList;
		
	}

	@Override
	public List<InsightTypesDto> getInsightTypes(String id)
			throws Exception {
		
		List<InsightDetail> retList = (List<InsightDetail>) this
				.getHibernateTemplate()
				.findByNamedQueryAndNamedParam(
						"CodelistCodeDecode.getInsightTypesById",
						new String[] { "id" }, new Object[] { new Integer(id) });
		return null;
	}
}
