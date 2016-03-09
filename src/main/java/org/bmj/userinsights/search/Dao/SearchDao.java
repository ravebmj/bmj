package org.bmj.userinsights.search.Dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * This Search Dao class will handle all search related queries
 * which will take to the database,get the results and return back
 */
public class SearchDao extends HibernateDaoSupport implements ISearchDao{
	/**
	 * This method will execute for all search criteria
	 * it will generate the dynamic query with supplied parameter
	 * and executed the query at the database level and return the results in 
	 * the form of list. this list contains all the required information for search
	 * criteria.
	 */
	@Override
	public List<InsightDetailsDto> getSearchResults(String keyword,
			String insightType, String serverity, String dateRangeOpt,
			String fromDate, String toDate) throws Exception {
		 List<InsightDetail> list = null;	
		 List<InsightDetailsDto> consolidatedList = null;
		 List<String> paramName = new ArrayList<String>();
	     List<Object> paramValue = new ArrayList<Object>();
		 List<SelectValuesDto> lstDateCriteria = InsightsConstants.getDateCriteriaLst();
		 Map<Integer,SelectValuesDto> dateCriteriaMap = getCodeListMap(lstDateCriteria);
			 
		 StringBuffer sbQuery = new StringBuffer();
		 sbQuery.append("select distinct id from InsightDetail id LEFT JOIN id.products as ip  ");
		 sbQuery.append("LEFT JOIN id.projects as ipj LEFT JOIN id.tags as it  ");
		 sbQuery.append("LEFT JOIN ip.product as prod LEFT JOIN ipj.project as proj LEFT JOIN it.tag as tag ");
		 sbQuery.append("where ((id.title like :keyword) OR (id.plainDescription like :keyword) ");
		 sbQuery.append("OR (prod.name like :keyword) OR (proj.name like :keyword) OR (tag.name like :keyword)) ");
		 sbQuery.append("AND id.insightApplicationID = 1 ");
		 sbQuery.append("AND id.deleteDate is null ");

		 
		 paramName.add("keyword");
		 paramValue.add("%"+keyword+"%");
		 
		 if(insightType!=null && Integer.valueOf(insightType)!=0 ){
			 sbQuery.append(" AND id.type = "+Integer.valueOf(insightType) );
		 }
		 
		 if(serverity!=null && Integer.valueOf(serverity)!=0){
			 sbQuery.append(" AND id.insightServerity = "+Integer.valueOf(serverity));
		 }
		 
		 if(dateRangeOpt!=null && !dateRangeOpt.isEmpty() && (Integer.valueOf(dateRangeOpt) == InsightsConstants.CREATED_DATE_VALUE)){
			 if(fromDate!=null && !fromDate.equals("From") && toDate!=null && !toDate.equals("To") ){				
				 sbQuery.append(" AND (id.addedDate BETWEEN :frmdate AND :todate) ");
				 paramName.add("frmdate");				
				 paramValue.add(getLowerTime(CommonUtils.getYYYYMMDD(fromDate)));
				 paramName.add("todate");
				 paramValue.add(getUpperTime(CommonUtils.getYYYYMMDD(toDate))); 
				
			 }else if(fromDate!=null && !fromDate.equals("From") && toDate!=null && toDate.equals("To") ){				
				 sbQuery.append(" AND (id.addedDate BETWEEN :frmdate  AND :todate ) ");
				 paramName.add("frmdate");				
				 paramValue.add(getLowerTime(CommonUtils.getYYYYMMDD(fromDate)));
				 paramName.add("todate");
				 paramValue.add(new Date());
				
			 }else if(fromDate!=null && fromDate.equals("From") && toDate!=null && !toDate.equals("To") ){				
				 sbQuery.append(" AND (id.addedDate <= :todate ) ");				
				 paramName.add("todate");
				 paramValue.add(getUpperTime(CommonUtils.getYYYYMMDD(toDate))); 
				
			 }
			
		 }else if(dateRangeOpt!=null && !dateRangeOpt.isEmpty() && (Integer.valueOf(dateRangeOpt) == InsightsConstants.EDITED_DATE_VALUE)){
			 if(fromDate!=null && !fromDate.equals("From") && toDate!=null && !toDate.equals("To") ){				
				 sbQuery.append(" AND (id.modifiedDate BETWEEN :frmdate  AND :todate ) ");
				 paramName.add("frmdate");				
				 paramValue.add(getLowerTime(CommonUtils.getYYYYMMDD(fromDate)));
				 paramName.add("todate");
				 paramValue.add(getUpperTime(CommonUtils.getYYYYMMDD(toDate)));
			 }else if(fromDate!=null && !fromDate.equals("From") && toDate!=null && toDate.equals("To") ){				
				 sbQuery.append(" AND (id.modifiedDate BETWEEN ':frmdate  AND :todate ) ");
				 paramName.add("frmdate");				
				 paramValue.add(getLowerTime(CommonUtils.getYYYYMMDD(fromDate)));
				 paramName.add("todate");
				 paramValue.add(new Date());
			 }else if(fromDate!=null && fromDate.equals("From") && toDate!=null && !toDate.equals("To") ){				
				 sbQuery.append(" AND (id.modifiedDate <= :todate ) ");
				 paramName.add("todate");
				 paramValue.add(getUpperTime(CommonUtils.getYYYYMMDD(toDate))); 
				
			 }
			
		 }
		 
		 list = (List<InsightDetail>)this.getHibernateTemplate()
					.findByNamedParam(sbQuery.toString(),paramName.toArray(new String[]{}),paramValue.toArray());
		 int i =0;
		 if(list!=null && list.size()>0){
			 consolidatedList = getConsolidatedList(list,keyword,i);			
		 }
		 if(consolidatedList!=null && consolidatedList.size()>0){
			 this.getHibernateTemplate().clear();//clearing session
			 return consolidatedList;
		 }else{			
			 List<InsightDetailsDto> insightDetailDtoList = CommonUtils.copyProperties(list);
		     this.getHibernateTemplate().clear();//clearing session
		    return insightDetailDtoList;
		 }
		
	}
	
	/**
	 * This method will return the list of insight deatails as per search weightage
	 * The search weightage priority is title,prdouct,project,tag and description
	 * @param list
	 * @param keyword
	 * @param i
	 * @return
	 * @throws Exception
	 */
	private List<InsightDetailsDto> getConsolidatedList(List<InsightDetail> list,String keyword,int i) throws Exception{
		List<InsightDetailsDto> titleList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> productList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> projectList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> tagsList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> descriptionList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> consolidatedList = new ArrayList<InsightDetailsDto>();
		 List<InsightDetailsDto> finalConsolidatedList = new ArrayList<InsightDetailsDto>();
		 
	 for(InsightDetail insightDetail : list){
		if(keyword!=null && !keyword.isEmpty() && insightDetail.getTitle().toLowerCase().contains(keyword.toLowerCase())){			
			InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
			CommonUtils.copyProperties(insightDetailsDto, insightDetail);
			insightDetailsDto.setSearchWeightageOrder(i);
			titleList.add(insightDetailsDto);
		}else if(insightDetail.getProducts()!=null && insightDetail.getProducts().size()>0){
			for(InsightProduct insightProduct : insightDetail.getProducts()){
				if(insightProduct!=null && keyword!=null && !keyword.isEmpty() && insightProduct.getProduct()!=null 
						&& insightProduct.getProduct().getName().toLowerCase().contains(keyword.toLowerCase())){					
					InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
					CommonUtils.copyProperties(insightDetailsDto, insightDetail);
					insightDetailsDto.setSearchWeightageOrder(i);
					productList.add(insightDetailsDto);
				}
			}
		}else if(insightDetail.getProjects()!=null && insightDetail.getProjects().size()>0){
			for(InsightProject insightProject : insightDetail.getProjects()){
				if(insightProject!=null &&  keyword!=null && !keyword.isEmpty() && insightProject.getProject()!=null
						&& insightProject.getProject().getName().toLowerCase().contains(keyword.toLowerCase())){					
					InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
					CommonUtils.copyProperties(insightDetailsDto, insightDetail);
					insightDetailsDto.setSearchWeightageOrder(i);
					projectList.add(insightDetailsDto);
				}
			}
		}else if(insightDetail.getTags()!=null && insightDetail.getTags().size()>0){
			for(InsightTag insightTag :  insightDetail.getTags()){
				if(insightTag!=null &&  keyword!=null && !keyword.isEmpty() && insightTag.getTag()!=null
						&& insightTag.getTag().getName().toLowerCase().contains(keyword.toLowerCase())){					
					InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
					CommonUtils.copyProperties(insightDetailsDto, insightDetail);
					insightDetailsDto.setSearchWeightageOrder(i);
					tagsList.add(insightDetailsDto);
				}
			}
		}else if(insightDetail.getPlainDescription()!=null && keyword!=null && !keyword.isEmpty() && insightDetail.getPlainDescription().toLowerCase().contains(keyword.toLowerCase())){			
			InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
			CommonUtils.copyProperties(insightDetailsDto, insightDetail);
			insightDetailsDto.setSearchWeightageOrder(i);
			descriptionList.add(insightDetailsDto);
		}
	 }
	 
	 consolidatedList.addAll(titleList);
	 consolidatedList.addAll(productList);
	 consolidatedList.addAll(projectList);
	 consolidatedList.addAll(tagsList);
	 consolidatedList.addAll(descriptionList);
	 
	 for(InsightDetailsDto insightDetailsDto : consolidatedList){
			if(keyword!=null && !keyword.isEmpty() && insightDetailsDto.getTitle().toLowerCase().contains(keyword.toLowerCase())){			
				i++;
				InsightDetailsDto titleInsightDetailsDto = new InsightDetailsDto();
				CommonUtils.copyProperties(titleInsightDetailsDto, insightDetailsDto);
				insightDetailsDto.setSearchWeightageOrder(i);
				finalConsolidatedList.add(insightDetailsDto);
			}else if(insightDetailsDto.getProducts()!=null && insightDetailsDto.getProducts().size()>0){
				for(InsightProduct insightProduct : insightDetailsDto.getProducts()){
					if(insightProduct!=null && keyword!=null && !keyword.isEmpty() && insightProduct.getProduct()!=null 
							&& insightProduct.getProduct().getName().toLowerCase().contains(keyword.toLowerCase())){
						i++;
						InsightDetailsDto prodInsightDetailsDto = new InsightDetailsDto();
						CommonUtils.copyProperties(prodInsightDetailsDto, insightDetailsDto);
						prodInsightDetailsDto.setSearchWeightageOrder(i);
						finalConsolidatedList.add(prodInsightDetailsDto);
					}
				}
			}else if(insightDetailsDto.getProjects()!=null && insightDetailsDto.getProjects().size()>0){
				for(InsightProject insightProject : insightDetailsDto.getProjects()){
					if(insightProject!=null &&  keyword!=null && !keyword.isEmpty() && insightProject.getProject()!=null
							&& insightProject.getProject().getName().toLowerCase().contains(keyword.toLowerCase())){
						i++;
						InsightDetailsDto projInsightDetailsDto = new InsightDetailsDto();
						CommonUtils.copyProperties(projInsightDetailsDto, insightDetailsDto);
						projInsightDetailsDto.setSearchWeightageOrder(i);
						finalConsolidatedList.add(projInsightDetailsDto);
					}
				}
			}else if(insightDetailsDto.getTags()!=null && insightDetailsDto.getTags().size()>0){
				for(InsightTag insightTag :  insightDetailsDto.getTags()){
					if(insightTag!=null &&  keyword!=null && !keyword.isEmpty() && insightTag.getTag()!=null
							&& insightTag.getTag().getName().toLowerCase().contains(keyword.toLowerCase())){
						i++;
						InsightDetailsDto tagInsightDetailsDto = new InsightDetailsDto();
						CommonUtils.copyProperties(tagInsightDetailsDto, insightDetailsDto);
						tagInsightDetailsDto.setSearchWeightageOrder(i);
						finalConsolidatedList.add(tagInsightDetailsDto);
					}
				}
			}else if(insightDetailsDto.getPlainDescription()!=null && keyword!=null && !keyword.isEmpty() && insightDetailsDto.getPlainDescription().toLowerCase().contains(keyword.toLowerCase())){
				i++;
				InsightDetailsDto plainDesInsightDetailsDto = new InsightDetailsDto();
				CommonUtils.copyProperties(plainDesInsightDetailsDto, insightDetailsDto);
				plainDesInsightDetailsDto.setSearchWeightageOrder(i);
				finalConsolidatedList.add(plainDesInsightDetailsDto);
			}
		 }
	    	 
	    	
		 return finalConsolidatedList;
	}
	
	/**
	 * preparing codelist map - key as codelistid and value as SelectValuesDto object
	 * @param lstInsightTypesDto
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,SelectValuesDto> getCodeListMap(List<SelectValuesDto> lstInsightTypesDto) throws Exception{
		Map<Integer,SelectValuesDto> map = new HashMap<Integer,SelectValuesDto>();
		if(lstInsightTypesDto!=null && lstInsightTypesDto.size()>0 ){
			for(SelectValuesDto insightTypesDto :lstInsightTypesDto){
				map.put(insightTypesDto.getCodeDecodedCode(), insightTypesDto);
			}
		}		
		return map;
	}
	
		
	/**
	 * This method call mapping object of InsightProduct and 
	 * Return Insight List
	 * @see org.bmj.userinsights.search.Dao.ISearchDao#getInsightForProduct(java.lang.Integer)
	 */
	@Override
	@Cacheable(value="searchCache",key="#idProduct")
	public List<InsightDetailsDto> getInsightForProduct(Integer idProduct) throws Exception{
		//InsightProduct Entity Obtained from DB
		List<InsightProduct> insightDetails = (List<InsightProduct>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Product.getInsightForProduct", "productId", idProduct);
		
		//InsightList to be populated from InsightProduct Entity
		List<InsightDetail> insightList = new ArrayList<InsightDetail>();
		
		for(InsightProduct insightProduct : insightDetails){//Adding Insight to list from Persistence Object
			insightList.add(insightProduct.getInsightDetail());
		}
		
		List<InsightDetailsDto> insightDtoList = CommonUtils.copyProperties(insightList);
		this.getHibernateTemplate().clear();//clearing session
		
		return insightDtoList;
	}
	
	

	/**
	 * This method call mapping object of InsightProject and 
	 * Return Insight List
	 * @see org.bmj.userinsights.search.Dao.ISearchDao#getInsightForProduct(java.lang.Integer)
	 */
	@Override
	@Cacheable(value="searchCache",key="#idProject")
	public List<InsightDetailsDto> getInsightForProject(Integer idProject)
			throws Exception {
		//InsightProject Entity Obtained from DB
		List<InsightProject> insightDetails = (List<InsightProject>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Project.getInsightForProject", "projectId", idProject);
		
		//InsightList to be populated from InsightProject Entity
		List<InsightDetail> insightList = new ArrayList<InsightDetail>();
		
		for(InsightProject insightProject : insightDetails){//Adding Insight to list from Persistence Object
			insightList.add(insightProject.getInsightDetail());
		}
		
		List<InsightDetailsDto> insightDtoList = CommonUtils.copyProperties(insightList);
		this.getHibernateTemplate().clear();//clearing session
		
		return insightDtoList;
	}

	/**
	 * This method call mapping object of InsightTag and 
	 * Return Insight List
	 * @see org.bmj.userinsights.search.Dao.ISearchDao#getInsightForProduct(java.lang.Integer)
	 */
	@Override
	@Cacheable(value="searchCache",key="#idTag")
	public List<InsightDetailsDto> getInsightForTag(Integer idTag)
			throws Exception {
		//InsightProject Entity Obtained from DB
		List<InsightTag> insightDetails = (List<InsightTag>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Tag.getInsightForTag", "tagId", idTag);
		
		//InsightList to be populated from InsightTag Entity
		List<InsightDetail> insightList = new ArrayList<InsightDetail>();
		
		for(InsightTag insightTag : insightDetails){//Adding Insight to list from Persistence Object
			insightList.add(insightTag.getInsightDetail());
		}
		
		List<InsightDetailsDto> insightDtoList = CommonUtils.copyProperties(insightList);
		this.getHibernateTemplate().clear();//clearing session
		
		return insightDtoList;
	}

	/**
	 * Get all insights
	 * @see org.bmj.userinsights.search.Dao.ISearchDao#getAllInsight()
	 */
	@Override
	public List<InsightDetailsDto> getAllInsight() throws Exception {
		List<InsightDetail> insightDetails = (List<InsightDetail>) this.getHibernateTemplate().findByNamedQuery("InsightDetail.getAllInsight");
		
		List<InsightDetailsDto> insightDtoList = CommonUtils.copyProperties(insightDetails);
		this.getHibernateTemplate().clear();//clearing session
		return insightDtoList;
	}
	
	/**
	 * Get the list of InsightDetailsDto by passing insight id
	 * this list contains all the required information to generate PDF report.
	 */
	public List<InsightDetailsDto> getInsightsForDownloadReport(String insightIds) throws Exception{
		List<String> list = new ArrayList<String>(Arrays.asList(insightIds.split(",")));
		List<Integer> IdList = new ArrayList<Integer>();
		for(String number : list) {
			IdList.add(Integer.parseInt(number+"")); 
		}
		List<InsightDetail> insightDetails = (List<InsightDetail>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("InsightDetail.getInsightsById", "insightIds", IdList);
		List<InsightDetailsDto> insightDtoList = null;
		if(null != insightDetails && insightDetails.size() > 0){
			insightDtoList = CommonUtils.copyProperties(insightDetails);
		}		
		this.getHibernateTemplate().clear();//clearing session
		return insightDtoList;
	}
	
	/**
	 * set the lower time for from date
	 * @param date
	 * @return
	 */
	private Date getLowerTime(Date date){
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		return date;
	}
	/**
	 * set the upper time for to date
	 * @param date
	 * @return
	 */
	private Date getUpperTime(Date date){
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		
		return date;
	}


	/**
	 * To get Tag name by Key
	 */
	@Override
	public String getInsightKeyWordForTag(Integer insightIds) throws Exception {
		List<InsightTag> insightDetails = (List<InsightTag>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Tag.getInsightForTag", "tagId", insightIds);
		String tagName = null;
		for(InsightTag insightTag : insightDetails){//Adding Insight to list from Persistence Object
			tagName = insightTag.getTag().getName();
		}
		return tagName;
	}
	
	/**
	 * To get Product name by Key
	 */
	@Override
	public String getInsightKeyWordForProduct(Integer insightIds) throws Exception {
		String tagName = null;
     List<InsightProduct> insightDetails = (List<InsightProduct>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Product.getInsightForProduct", "productId", insightIds);
		
		for(InsightProduct insightProduct : insightDetails){//Adding Insight to list from Persistence Object
			tagName = insightProduct.getProduct().getName();
		}
		return tagName;
	}
	
	/**
	 * To get Project name by Key
	 */
	@Override
	public String getInsightKeyWordForProject(Integer insightIds) throws Exception {
		String tagName = null;
		List<InsightProject> insightDetails = (List<InsightProject>) this.getHibernateTemplate().findByNamedQueryAndNamedParam("Project.getInsightForProject", "projectId", insightIds);
		for(InsightProject insightProject : insightDetails){//Adding Insight to list from Persistence Object
			tagName = insightProject.getProject().getName();
		}

		return tagName;
	}
	
	
}
