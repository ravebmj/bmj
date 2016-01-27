package org.bmj.userinsights.insight.Dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bmj.userinsights.cache.service.ICacheService;
import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.InsightsConstants;
import org.bmj.userinsights.common.dto.AdminUserDto;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.AWSBucketConfig;
import org.bmj.userinsights.dto.FoundViaDto;
import org.bmj.userinsights.dto.GeographiesDto;
import org.bmj.userinsights.dto.InsightAttachmentDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.MainUserTypeDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.dto.ProjectDto;
import org.bmj.userinsights.dto.TagDto;
import org.bmj.userinsights.entity.AdminUser;
import org.bmj.userinsights.entity.CodelistCodeDecode;
import org.bmj.userinsights.entity.FoundVia;
import org.bmj.userinsights.entity.Geographies;
import org.bmj.userinsights.entity.InsightAttachment;
import org.bmj.userinsights.entity.InsightConfig;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.InsightFoundVia;
import org.bmj.userinsights.entity.InsightGeographies;
import org.bmj.userinsights.entity.InsightMainUserType;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.InsightWeblink;
import org.bmj.userinsights.entity.MainUserType;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.entity.Project;
import org.bmj.userinsights.entity.Tag;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.dto.InsightWeblinkDto;
import org.bmj.userinsights.insight.service.IInsightService;
import org.bmj.userinsights.search.controller.SearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
/**
 * All database operations on insight screen.(create and edit scenario)
 * @author nilesh.kambli
 *
 */
public class InsightDao extends HibernateDaoSupport implements IInsightDao{
	private static final  Logger log = Logger.getLogger(InsightDao.class);
	ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	@Autowired
	private IInsightService insightService; 
	
	@Autowired
	private ICacheService cacheService; 
	
	
	/**
	 * Get insight details and related objects and populate in to view/edit insight page
	 * @param  id
	 */
	public List<InsightDto> getInsightDetails(String id) throws Exception{
		List<InsightDto> insightDtoList = null;
		List<InsightDetail> retList = (List<InsightDetail>) this
				.getHibernateTemplate()
				.findByNamedQueryAndNamedParam(
						"InsightDetail.getInsightDetailsById",
						new String[] { "id" }, new Object[] { new Integer(id) });
		insightDtoList = new ArrayList<InsightDto>();
		
		if(retList!=null && retList.size()>0){
			InsightDto insightDto = new InsightDto();			
			 for(InsightDetail insightDetail : retList){				 
				 CommonUtils.copyProperties(insightDto.getInsightDetailsDto(), insightDetail);
				 insightDto.setLstFoundViaDto(getFoundViaDetails()); // Populate master list of "Found Via"
				 insightDto.setLstMainUserTypeDto(getMainUserTypeDetails()); // Populate master list of "Main User Type"
				 insightDto.setLstGeographiesDto(getGeographiesDetails()); // Populate master list of "Geographies"
				 if(insightDetail.getFoundDate()!=null){
					 insightDto.setFoundDate(CommonUtils.getDateAsStringByFormat(insightDetail.getFoundDate(),InsightsConstants.FOUND_DATE_FORMAT ));
				 }
				 populateAttachments(insightDto, insightDetail);
				 populateWebLinks(insightDto, insightDetail);
				 insightDtoList.add(insightDto);
			 }
		}
		
		return insightDtoList;
		
	}
	/**
	 * Populate WebLinks data in DTO.
	 * @param insightDto
	 * @param insightDetail
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void populateWebLinks(InsightDto insightDto,
			InsightDetail insightDetail) throws IllegalAccessException,
			InvocationTargetException {
		Set<InsightWeblink> setInsightWeblink= insightDetail.getWeblinks();
		 List<InsightWeblinkDto> lstInsightWeblinkDTO=new ArrayList<InsightWeblinkDto>();
			for (InsightWeblink insightWeblink : setInsightWeblink) {
				InsightWeblinkDto insightWeblinkDTO=new InsightWeblinkDto();
				 CommonUtils.copyProperties(insightWeblinkDTO, insightWeblink);//Populates attachments data.
				 insightWeblinkDTO.setInsightId(insightDetail.getId());
				 lstInsightWeblinkDTO.add(insightWeblinkDTO);
			}
			insightDto.getWeblinkDTO().setLstInsightWeblinkDTO(lstInsightWeblinkDTO);
	}
	
	/**
	 * Populate attachment data in DTO.
	 * @param insightDto
	 * @param insightDetail
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void populateAttachments(InsightDto insightDto,
			InsightDetail insightDetail) throws IllegalAccessException,
			InvocationTargetException {
		Set<InsightAttachment> setInsightAttachment= insightDetail.getAttachments();
		 List<InsightAttachmentDto> lstInsightAttachmentDTO=new ArrayList<InsightAttachmentDto>();
			for (InsightAttachment insightAttachment : setInsightAttachment) {
				InsightAttachmentDto insightAttachmentDTO=new InsightAttachmentDto();
				 CommonUtils.copyProperties(insightAttachmentDTO, insightAttachment);//Populates attachments data.
				 insightAttachmentDTO.setInsightId(insightDetail.getId());
				 insightAttachmentDTO.setFileExt(CommonUtils.getFileExtenstion(insightAttachment.getFileName()));
				 lstInsightAttachmentDTO.add(insightAttachmentDTO);
			}
		 insightDto.getAttachmentDTO().setLstInsightAttachmentDTO(lstInsightAttachmentDTO);
	}

	/**
	 * return autocomplete list of product
	 * @param pname
	 */
	@Override
	@Cacheable(value="productLib",key="#pname")
	public List<ProductDto> getAutoCompleteProductList(String pname)
			throws Exception {
		List<ProductDto> lstProductDTO=new  ArrayList<ProductDto>();
		List<Product> returnVal = (List<Product>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("Product.getAutoCompleteList",
						new String[]{"pname"},new Object[]{pname+"%"});
		
		if(returnVal!=null && returnVal.size()>0){
			for (Product product : returnVal) {
				ProductDto productDTO=new ProductDto();
				productDTO.setId(product.getId());
				productDTO.setName(product.getName());
				lstProductDTO.add(productDTO);
			}
		}
		return lstProductDTO;
	}
	/**
	 * return autocomplete list of project.
	 *  @param projName
	 */
	@Override
	@Cacheable(value="projectLib",key="#projName")
	public List<ProjectDto> getAutoCompleteProjectList(String projName)
			throws Exception {
		List<ProjectDto> lstProjectDTO=new  ArrayList<ProjectDto>();
		List<Project> returnVal = (List<Project>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("Project.getAutoCompleteList",
						new String[]{"projname"},new Object[]{projName+"%"});
		
		if(returnVal!=null && returnVal.size()>0){
			for (Project project : returnVal) {
				ProjectDto projectDTO=new ProjectDto();
				projectDTO.setId(project.getId());
				projectDTO.setName(project.getName());
				lstProjectDTO.add(projectDTO);
			}
		}
		return lstProjectDTO;
	}
	/**
	 * return autocomplete list of tags.
	 *  @param tagName
	 */
	@Override
	@Cacheable(value="tagLib",key="#tagName")
	public List<TagDto> getAutoCompleteTagList(String tagName)
			throws Exception {
		List<TagDto> lstTagDTO=new  ArrayList<TagDto>();
		List<Tag> returnVal = (List<Tag>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("Tag.getAutoCompleteList",
						new String[]{"tagname"},new Object[]{tagName+"%"});
		
		if(returnVal!=null && returnVal.size()>0){
			for (Tag tag : returnVal) {
				TagDto tagDTO=new TagDto();
				tagDTO.setId(tag.getId());
				tagDTO.setName(tag.getName());
				lstTagDTO.add(tagDTO);
			}
		}
		return lstTagDTO;
	}
	
	/**
	 * return autocomplete list of insights.
	 * @param insightTitle
	 */
	@Cacheable(value="insightTitleCache",key="#insightTitle")
	@Override
	public List<InsightDto> getAutoCompleteInsightsList(String insightTitle)
			throws Exception {
		List<InsightDto> lstInsightDTO=new  ArrayList<InsightDto>();
		List<InsightDetail> returnVal = (List<InsightDetail>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightDetail.getAutoCompleteList",
						new String[]{"insightTitle"},new Object[]{"%"+insightTitle+"%"});
		
		if(returnVal!=null && returnVal.size()>0){
			for (InsightDetail insightDetail : returnVal) {
				InsightDto insightDTO=new InsightDto();
				insightDTO.getInsightDetailsDto().setId(insightDetail.getId());
				insightDTO.getInsightDetailsDto().setTitle(insightDetail.getTitle());
				lstInsightDTO.add(insightDTO);
			}
		}
		return lstInsightDTO;
	}
	/**
	 * Save insight details in case of New and Edit.
	 * @param insightTitle
	 */
	@Override
	public Integer saveInsightDetails(InsightDto insightDTO) throws Exception {
		Integer returnId=null;
		if(null != insightDTO){
			InsightDetail insightDetail = new InsightDetail();
			
			if(insightDTO.getInsightDetailsDto().getId()==null || (insightDTO.getInsightDetailsDto().getId()!=null && insightDTO.getInsightDetailsDto().getId()==0)){// New Insight
				insightDetail = (InsightDetail) InsightDetail.class.newInstance();
				insightDetail.setAddedUser(insightDTO.getUserId());
				insightDetail.setAddedDate(new Date());
			}else{// Update Insight
				insightDetail = (InsightDetail)this.getHibernateTemplate().get(InsightDetail.class, insightDTO.getInsightDetailsDto().getId());
				returnId=insightDetail.getId();
			}				
			if(insightDTO.getInsightDetailsDto().getInsightServerity()!=null){
				insightDetail.setInsightServerity(insightDTO.getInsightDetailsDto().getInsightServerity());
			}
			if(insightDTO.getFoundDate()!=null && !insightDTO.getFoundDate().equals("")){
				insightDetail.setFoundDate(CommonUtils.getDateByFormat(insightDTO.getFoundDate(),InsightsConstants.FOUND_DATE_FORMAT));
			}
			if(insightDTO.getInsightDetailsDto().getType()!=null){
				insightDetail.setType(insightDTO.getInsightDetailsDto().getType());
			}
			if(insightDTO.getInsightDetailsDto().getTitle()!=null){
				insightDetail.setTitle(insightDTO.getInsightDetailsDto().getTitle());
			}
			if(insightDTO.getInsightDetailsDto().getDescription()!=null){
				insightDetail.setDescription(insightDTO.getInsightDetailsDto().getDescription());
				try{
					insightDetail.setPlainDescription(CommonUtils.parseToPlainText(insightDTO.getInsightDetailsDto().getDescription()));
				}catch(Exception e){
					CommonUtils.errorLoggging(log, e, messagesProperties.getString("error_apache_tika_parse_plain_text"));
					insightDetail.setPlainDescription(insightDTO.getInsightDetailsDto().getDescription());
				}				
			}
			if(insightDTO.getInsightDetailsDto().getFoundCount()!=null){
				insightDetail.setFoundCount(insightDTO.getInsightDetailsDto().getFoundCount());
			}
			insightDetail.setCompanyName(insightDTO.getInsightDetailsDto().getCompanyName());
			insightDetail.setInsightApplicationID(new Integer(InsightsConstants.APPLICATION_ID));
			insightDetail.setModifiedUser(insightDTO.getUserId());

			insightDetail.setModifiedDate(new Date());
			if(null != insightDTO.getNewProdList() && insightDTO.getNewProdList().size() > 0){
				processAddedProducts(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getDelProdList() && insightDTO.getDelProdList().size() > 0){
				processDeletedProducts(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getNewProjList() && insightDTO.getNewProjList().size() > 0){
				processAddedProjects(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getDelProjList() && insightDTO.getDelProjList().size() > 0){
				processDeletedProjects(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getNewTagList() && insightDTO.getNewTagList().size() > 0){
				processAddedTags(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getDelTagList() && insightDTO.getDelTagList().size() > 0){
				processDeletedTags(insightDTO, insightDetail);
			}
			
			if(null != insightDTO.getDelFoundVia() && insightDTO.getDelFoundVia().size() > 0){
				processDeletedFoundVia(insightDTO, insightDetail);
			}			
			if(null != insightDTO.getNewFoundVia() && insightDTO.getNewFoundVia().size() > 0){
				processAddedFoundVia(insightDTO, insightDetail);
			}
			if(null != insightDTO.getDelMainUserType() && insightDTO.getDelMainUserType().size() > 0){
				processDeletedMainUserType(insightDTO, insightDetail);
			}
			if(null != insightDTO.getNewMainUserType() && insightDTO.getNewMainUserType().size() > 0){
				processAddedMainUserType(insightDTO, insightDetail);
			}
			if(null != insightDTO.getDelGeographies() && insightDTO.getDelGeographies().size() > 0){
				processDeletedGeographies(insightDTO, insightDetail);
			}
			if(null != insightDTO.getNewGeographies() && insightDTO.getNewGeographies().size() > 0){
				processAddedGeographies(insightDTO, insightDetail);
			}

			
			
			if(insightDTO.getInsightDetailsDto().getId()==null || (insightDTO.getInsightDetailsDto().getId()!=null && insightDTO.getInsightDetailsDto().getId()==0)){// New Insight
				Integer insightId = (Integer)this.getHibernateTemplate().save(insightDetail);// If New Insight
				saveAttachmentsForNew(insightDTO, insightDetail,insightId);
				saveUpdateWebLinks(insightDTO, insightDetail);
				returnId=insightId;
			}else{// If old insight
				saveUpdateWebLinks(insightDTO, insightDetail);
				this.getHibernateTemplate().update(insightDetail);
			}
			cacheService.clearCacheByName(InsightsConstants.CACHE_NAME_INSIGHT_TITLE);
		}		
		return returnId;
	}
	/**
	 * Save and Delete weblink for new and existing insight.
	 * @param insightDTO
	 * @param insightDetail
	 * @throws Exception 
	 */
	private void saveUpdateWebLinks(InsightDto insightDTO,
			InsightDetail insightDetail) throws Exception {
		List<InsightWeblinkDto> lstInsightWeblinkDTO = insightDTO.getWeblinkDTO().getLstInsightWeblinkDTO();
		for (InsightWeblinkDto insightWeblinkDTO : lstInsightWeblinkDTO) {
			if(insightWeblinkDTO.getId()==null && !insightWeblinkDTO.isFlgDelete()){//Insert New Web Link.nilesh
				InsightWeblink insightWeblink=new InsightWeblink();
				insightWeblinkDTO.setAddedDate(new Date());
				CommonUtils.copyProperties(insightWeblink, insightWeblinkDTO);//Populates attachments data.
				insightWeblink.setInsightDetail(insightDetail);
				insightWeblink.setAddedUser(insightDTO.getUserId());
				this.getHibernateTemplate().save(insightWeblink);
			}else if(insightWeblinkDTO.getId()!=null && insightWeblinkDTO.isFlgDelete()){//Delete existing Web Link.
			    for( Iterator<InsightWeblink> itrInsightWeblinks = insightDetail.getWeblinks().iterator(); itrInsightWeblinks.hasNext() ; )	{
			    	InsightWeblink insightWeblink = itrInsightWeblinks.next();
					if(insightWeblink.getId().intValue()==insightWeblinkDTO.getId().intValue()){// Check if current weblink need to be delete.
						this.getHibernateTemplate().delete(insightWeblink);
						itrInsightWeblinks.remove();
					}
			    }
			}
		}
	}
	/**
	 * Save attachments for new insight.
	 * @param insightDTO
	 * @param insightDetail
	 * @param insightId 
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void saveAttachmentsForNew(InsightDto insightDTO,
			InsightDetail insightDetail, Integer insightId) throws IllegalAccessException,
			InvocationTargetException {
		List<InsightAttachmentDto> lstInsightAttachmentDTO = insightDTO.getAttachmentDTO().getLstInsightAttachmentDTO();
		if (null != lstInsightAttachmentDTO && lstInsightAttachmentDTO.size() > 0) {
			for (InsightAttachmentDto insightAttachmentDTO : lstInsightAttachmentDTO) {
				InsightAttachment insightAttachment=new InsightAttachment();
				CommonUtils.copyProperties(insightAttachment, insightAttachmentDTO);//Populates attachments data.
				insightAttachment.setInsightDetail(insightDetail);
				insightAttachment.setAddedUser(insightDTO.getUserId());
				insightAttachment.setAddedDate(new Date());
				String pathFileUpload=insightAttachment.getFilePath();
				pathFileUpload = pathFileUpload.replace(InsightsConstants.TEMP_FOLDER_NAME, insightId.toString());
				insightAttachment.setFilePath(pathFileUpload);
				String pathExternalUpload=insightAttachment.getFileExternalPath();
				pathExternalUpload = pathExternalUpload.replace(InsightsConstants.TEMP_FOLDER_NAME, insightId.toString());
				insightAttachment.setFileExternalPath(pathExternalUpload);
				insightAttachment.setFileExternalAdditionalInfo(pathExternalUpload);
				this.getHibernateTemplate().save(insightAttachment);
			} 
		}
	}	
	
	
	/**
	 * Database Deletion for deleted list of Found Via by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedFoundVia(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		if(insightDTO.getDelFoundVia().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightFoundVia> itrInsightFV = insightDetail.getFoundVias().iterator(); itrInsightFV.hasNext() ; )
		    {
		    	InsightFoundVia insightFV = itrInsightFV.next();
				if(isFoundViaDeleteRequired(insightDTO, insightFV)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightFV);
					itrInsightFV.remove();
				}
		    }
		}
	}
	/**
	 * Check if current Found Via associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of Found Via by user.
	 * @param insightProduct : ORM Oject of mapping between Insight and Found Via.
	 * @return true mean need to be delete else no.
	 */
	private boolean isFoundViaDeleteRequired(InsightDto insightDTO,
			InsightFoundVia insightFV)throws Exception {
		for (String delFoundVia : insightDTO.getDelFoundVia()) {//loop through deleted list of Found Via by user.
			if(delFoundVia.equalsIgnoreCase(insightFV.getFoundVia().getId()+"")){
				return true;
			}else if(delFoundVia.indexOf(":")!=-1){// It is other field.
				String id=delFoundVia.substring(0, delFoundVia.indexOf(":"));
				if((insightFV.getFoundVia().getId()+"").equals(id)){
					return true;
				}
			}
		}
		return false;
	}	
	/**
	 * Database insertion for newly added list of Found Via by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedFoundVia(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		List<FoundVia> returnVal = insightService.getFoundVia();
		//If new Product list map with  Insight then save into database.
		for (String newFoundVia : insightDTO.getNewFoundVia()) {
			FoundVia foundVia =getFoundViaFromMaster(returnVal,newFoundVia);
			if(null != foundVia){
				InsightFoundVia insightFoundVia=new  InsightFoundVia();
				insightFoundVia.setInsightDetail(insightDetail);
				insightFoundVia.setFoundVia(foundVia);
				if(foundVia.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){// If added type is "Other'
					insightFoundVia.setFoundViaOtherValue(newFoundVia.substring(newFoundVia.indexOf(":")+1, newFoundVia.length())); // set value for other.
				}
				insightFoundVia.setAddedDate(new Date());
				insightFoundVia.setAddedUser(insightDTO.getUserId());
				insightDetail.getFoundVias().add(insightFoundVia);
			}			
		}
	}
	/**
	 * Get FoundVia entity from master list by passing id.
	 * @param returnVal : master list of "Found Via".
	 * @param newFoundVia : value to be compare with master list.
	 * @return
	 */
	private FoundVia getFoundViaFromMaster(List<FoundVia> returnVal,String newFoundVia)throws Exception{
		for (FoundVia foundVia : returnVal) {
			if(foundVia.getId().toString().equals(newFoundVia)){
				return foundVia;
			}else if(newFoundVia.indexOf(":")!=-1){// It is other field.
				String id=newFoundVia.substring(0, newFoundVia.indexOf(":"));
				if(foundVia.getId().toString().equals(id)){
					return foundVia;
				}
			}
		}
		return null;
	}
	
	/**
	 * Database Deletion for deleted list of Main User Type by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedMainUserType(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		if(insightDTO.getDelMainUserType().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightMainUserType> itrMainUserType = insightDetail.getMainUserTypes().iterator(); itrMainUserType.hasNext() ; )
		    {
		    	InsightMainUserType insightMainUserType = itrMainUserType.next();
				if(isMainUserTypeDeleteRequired(insightDTO, insightMainUserType)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightMainUserType);
					itrMainUserType.remove();
				}
		    }
		}
	}
	/**
	 * Check if current Main User Type associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of Main User Type by user.
	 * @param insightMainUserType : ORM Oject of mapping between Insight and Main User Type.
	 * @return true mean need to be delete else no.
	 */
	private boolean isMainUserTypeDeleteRequired(InsightDto insightDTO,
			InsightMainUserType insightMainUserType) throws Exception{
		for (String delMainUserType : insightDTO.getDelMainUserType()) {//loop through deleted list of Found Via by user.
			if(delMainUserType.equalsIgnoreCase(insightMainUserType.getMainUserType().getId()+"")){
				return true;
			}else if(delMainUserType.indexOf(":")!=-1){// It is other field.
				String id=delMainUserType.substring(0, delMainUserType.indexOf(":"));
				if((insightMainUserType.getMainUserType().getId()+"").equals(id)){
					return true;
				}
			}
		}
		return false;
	}	
	/**
	 *  Database Insertion for  newly added list of Main User Type by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedMainUserType(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		List<MainUserType> returnVal = insightService.getAllMainUserTypes();
		//If new Product list map with  Insight then save into database.
		for (String newMainUserType : insightDTO.getNewMainUserType()) {
			MainUserType mainUserType =getMainUserTypeFromMaster(returnVal,newMainUserType);
			if(null != mainUserType){
				InsightMainUserType insightMainUserType=new  InsightMainUserType();
				insightMainUserType.setInsightDetail(insightDetail);
				insightMainUserType.setMainUserType(mainUserType);
				if(mainUserType.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){// If added type is "Other'
					insightMainUserType.setMainUserTypeOtherValue(newMainUserType.substring(newMainUserType.indexOf(":")+1, newMainUserType.length())); // set value for other.
				}
				insightMainUserType.setAddedDate(new Date());
				insightMainUserType.setAddedUser(insightDTO.getUserId());
				insightDetail.getMainUserTypes().add(insightMainUserType);
			}			
		}
	}
	/**
	 * Get Main User Type entity from master list by passing id.
	 * @param returnVal : master list of "Main User Type".
	 * @param newMainUserType : value to be compare with master list.
	 * @return
	 */
	private MainUserType getMainUserTypeFromMaster(List<MainUserType> returnVal,String newMainUserType)throws Exception{
		for (MainUserType mainUserType : returnVal) {
			if(mainUserType.getId().toString().equals(newMainUserType)){
				return mainUserType;
			}else if(newMainUserType.indexOf(":")!=-1){// It is other field.
				String id=newMainUserType.substring(0, newMainUserType.indexOf(":"));
				if(mainUserType.getId().toString().equals(id)){
					return mainUserType;
				}
			}
		}
		return null;
	}
	
	/**
	 *  Database Deletion for deleted list of Geographies by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedGeographies(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		if(insightDTO.getDelGeographies().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightGeographies> itrGeographies = insightDetail.getGeographies().iterator(); itrGeographies.hasNext() ; )
		    {
		    	InsightGeographies insightGeographies = itrGeographies.next();
				if(isGeographiesDeleteRequired(insightDTO, insightGeographies)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightGeographies);
					itrGeographies.remove();
				}
		    }
		}
	}
	/**
	 * Check if current Main User Type associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of Main User Type by user.
	 * @param insightMainUserType : ORM Oject of mapping between Insight and Main User Type.
	 * @return true mean need to be delete else no.
	 */
	private boolean isGeographiesDeleteRequired(InsightDto insightDTO,
			InsightGeographies insightGeographies) {
		for (String delGeographies : insightDTO.getDelGeographies()) {//loop through deleted list of Found Via by user.
			if(delGeographies.equalsIgnoreCase(insightGeographies.getGeographies().getId()+"")){
				return true;
			}else if(delGeographies.indexOf(":")!=-1){// It is other field.
				String id=delGeographies.substring(0, delGeographies.indexOf(":"));
				if((insightGeographies.getGeographies().getId()+"").equals(id)){
					return true;
				}
			}
		}
		return false;
	}	
	/**
	 *  Database Insertion for  newly added list of Geographies by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedGeographies(InsightDto insightDTO,
			InsightDetail insightDetail) throws Exception{
		List<Geographies> returnVal = insightService.getAllGeographis();
		//If new Product list map with  Insight then save into database.
		for (String newGeographies : insightDTO.getNewGeographies()) {
			Geographies geographies =getGeographiesFromMaster(returnVal,newGeographies);
			if(null != geographies){
				InsightGeographies insightGeographies=new  InsightGeographies();
				insightGeographies.setInsightDetail(insightDetail);
				insightGeographies.setGeographies(geographies);
				if(geographies.getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){// If added type is "Other'
					insightGeographies.setInsightGeographicOtherValue(newGeographies.substring(newGeographies.indexOf(":")+1, newGeographies.length())); // set value for other.
				}
				insightGeographies.setAddedDate(new Date());
				insightGeographies.setAddedUser(insightDTO.getUserId());
				insightDetail.getGeographies().add(insightGeographies);
			}			
		}
	}
	/**
	 * Get Geographies entity from master list by passing id.
	 * @param returnVal : master list of "Geographies".
	 * @param newGeographies : value to be compare with master list.
	 * @return
	 */
	private Geographies getGeographiesFromMaster(List<Geographies> returnVal,String newGeographies)throws Exception{
		for (Geographies geographies : returnVal) {
			if(geographies.getId().toString().equals(newGeographies)){
				return geographies;
			}else if(newGeographies.indexOf(":")!=-1){// It is other field.
				String id=newGeographies.substring(0, newGeographies.indexOf(":"));
				if(geographies.getId().toString().equals(id)){
					return geographies;
				}
			}
		}
		return null;
	}
	
	/**
	 *  Database Deletion for  deleted list of products by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedProducts(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		if(insightDTO.getDelProdList().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightProduct> itrInsightProduct = insightDetail.getProducts().iterator(); itrInsightProduct.hasNext() ; )
		    {
		    	InsightProduct insightProduct = itrInsightProduct.next();
				if(isProductDeleteRequired(insightDTO, insightProduct)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightProduct);
					itrInsightProduct.remove();
				}
		    }
		}
	}
	
	/**
	 *  Database Insertion for newly added list of products by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedProducts(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		List<Product> productList = insightService.getAllProducts();
		List<String> productNameList = getProductNameList(productList);
		//If new Product list map with  Insight then save into database.
		for (String newProd : insightDTO.getNewProdList()) {
			Product product =null;			
			if(productNameList.contains(newProd)){// Product already exist in Master
				product = getCurrentProduct(productList, newProd);
			}else{// Create new product
				product =new Product();
				product.setName(newProd.trim());
				product.setDescription(newProd);
				product.setAddedDate(new Date());
				product.setAddedUser(insightDTO.getUserId());
				product.setApplicationId(Integer.parseInt(InsightsConstants.APPLICATION_ID));
				this.getHibernateTemplate().save(product);		
				
				cacheService.clearCacheByName(InsightsConstants.CACHE_NAME_PRODUCT);
			}
			
			// save in mapping table of product and insight.
			if(null!= product){
				InsightProduct insightProduct=new  InsightProduct();
				insightProduct.setInsightDetail(insightDetail);
				insightProduct.setProduct(product);
				insightProduct.setAddedDate(new Date());
				insightProduct.setAddedUser(insightDTO.getUserId());
				insightDetail.getProducts().add(insightProduct);
			}			
		}
	}

	
	/**
	 *Prepares the list of product names to identify the product already exist or not 
	 * @param productList : Product(product library) list from the DB.
	 * @return the list of product names which are in DB.
	 */
	private List<String> getProductNameList(List<Product> productList)throws Exception{
		List<String> productNameList = new ArrayList<String>();
		if(null != productList && productList.size() > 0){
			for (Product product : productList) {
				productNameList.add(product.getName().trim());
			}			
		}
		return productNameList;
	}
	
	/**
	 * Get the product object for the given product name.
	 * @param productList : Product(product library) list from the DB.
	 * @param productName : Name of product to get the product object from master list.
	 * @return product object for the product name
	 */
	private Product getCurrentProduct(List<Product> productList, String productName)throws Exception{
		for (Product product : productList) {
			if(productName.trim().equals(product.getName().trim())){
				return product;
			}
		}
		return null;
	}
	
	/**
	 * Check if current product associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of product by user.
	 * @param insightProduct : ORM Oject of mapping between Insight and Product.
	 * @return true mean need to be delete else no.
	 */
	private boolean isProductDeleteRequired(InsightDto insightDTO,
			InsightProduct insightProduct) throws Exception{
		for (String delProd : insightDTO.getDelProdList()) {//loop through deleted list of product by user.
			if(delProd.equalsIgnoreCase(insightProduct.getProduct().getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Database Deletion for deleted list of projects by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedProjects(InsightDto insightDTO,
			InsightDetail insightDetail) throws Exception{
		if(insightDTO.getDelProjList().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightProject> itrInsightProject = insightDetail.getProjects().iterator(); itrInsightProject.hasNext() ; )
		    {
		    	InsightProject insightProject = itrInsightProject.next();
				if(isProjectDeleteRequired(insightDTO, insightProject)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightProject);
					itrInsightProject.remove();
				}
		    }
		}
	}
	
	/**
	 *  Database Insertion for newly added list of products by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedProjects(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception {
		List<Project> projectList = insightService.getAllProjects();
		List<String> projectNameList = getProjectNameList(projectList);
		//If new Project list map with  Insight then save into database.
		for (String newProj : insightDTO.getNewProjList()) {
			Project project =null;			
			if(projectNameList.contains(newProj)){// Product already exist in Master
				project = getCurrentProject(projectList, newProj);
			}else{// Create new project
				project =new Project();
				project.setName(newProj.trim());
				project.setDescription(newProj);
				project.setAddedDate(new Date());
				project.setAddedUser(insightDTO.getUserId());
				project.setApplicationId(Integer.parseInt(InsightsConstants.APPLICATION_ID));
				this.getHibernateTemplate().save(project);		
				cacheService.clearCacheByName(InsightsConstants.CACHE_NAME_PROJECT);
			}
			
			// save in mapping table of project and insight.
			if(null!= project){
				InsightProject insightProject=new  InsightProject();
				insightProject.setInsightDetail(insightDetail);
				insightProject.setProject(project);
				insightProject.setAddedDate(new Date());
				insightProject.setAddedUser(insightDTO.getUserId());
				insightDetail.getProjects().add(insightProject);
			}			
		}
	}

	
	/**
	 *Prepares the list of project names to identify the project already exist or not 
	 * @param projectList : Project(project library) list from the DB.
	 * @return the list of project names which are in DB.
	 */
	private List<String> getProjectNameList(List<Project> projectList)throws Exception{
		List<String> projectNameList = new ArrayList<String>();
		if(null != projectList && projectList.size() > 0){
			for (Project project : projectList) {
				projectNameList.add(project.getName().trim());
			}			
		}
		return projectNameList;
	}
	
	/**
	 * Get the project object for the given project name.
	 * @param projectList : Project(project library) list from the DB.
	 * @param projectName : Name of project to get the project object from master list.
	 * @return project object for the project name
	 */
	private Project getCurrentProject(List<Project> projectList, String projectName)throws Exception{
		for (Project project : projectList) {
			if(projectName.trim().equals(project.getName().trim())){
				return project;
			}
		}
		return null;
	}
	
	/**
	 * Check if current project associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of project by user.
	 * @param insightProject : ORM Object of mapping between Insight and Project.
	 * @return true mean need to be delete else no.
	 */
	private boolean isProjectDeleteRequired(InsightDto insightDTO,
			InsightProject insightProject)throws Exception {
		for (String delProj : insightDTO.getDelProjList()) {//loop through deleted list of product by user.
			if(delProj.equalsIgnoreCase(insightProject.getProject().getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Database Deletion for deleted list of tags by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processDeletedTags(InsightDto insightDTO,
			InsightDetail insightDetail) throws Exception{
		if(insightDTO.getDelTagList().size()>0){// If there is deleted list by user.
		    for( Iterator<InsightTag> itrInsightTag = insightDetail.getTags().iterator(); itrInsightTag.hasNext() ; )
		    {
		    	InsightTag insightTag = itrInsightTag.next();
				if(isTagDeleteRequired(insightDTO, insightTag)){// Check if current product need to be delete.
					this.getHibernateTemplate().delete(insightTag);
					itrInsightTag.remove();
				}
		    }
		}
	}
	
	/**
	 *  Database Insertion for newly added list of tags by user on UI screen.
	 * @param insightDTO
	 * @param insightDetail
	 */
	private void processAddedTags(InsightDto insightDTO,
			InsightDetail insightDetail)throws Exception{
		List<Tag> tagList = insightService.getAllTags();
		List<String> tagNameList = getTagNameList(tagList);
		//If new Tag list map with  Insight then save into database.
		for (String newTag : insightDTO.getNewTagList()) {
			Tag tag =null;			
			if(tagNameList.contains(newTag)){// Product already exist in Master
				tag = getCurrentTag(tagList, newTag);
			}else{// Create new product
				tag =new Tag();
				tag.setName(newTag.trim());
				tag.setDescription(newTag);
				tag.setAddedDate(new Date());
				tag.setAddedUser(insightDTO.getUserId());
				tag.setApplicationId(Integer.parseInt(InsightsConstants.APPLICATION_ID));
				this.getHibernateTemplate().save(tag);			
				cacheService.clearCacheByName(InsightsConstants.CACHE_NAME_TAG);
			}
			
			// save in mapping table of tag and insight.
			if(null!= tag){
				InsightTag insightTag=new  InsightTag();
				insightTag.setInsightDetail(insightDetail);
				insightTag.setTag(tag);
				insightTag.setAddedDate(new Date());
				insightTag.setAddedUser(insightDTO.getUserId());
				insightDetail.getTags().add(insightTag);
			}			
		}
	}

	
	/**
	 *Prepares the list of tag names to identify the tag already exist or not 
	 * @param tagList : Tag(tag library) list from the DB.
	 * @return the list of tag names which are in DB.
	 */
	private List<String> getTagNameList(List<Tag> tagList)throws Exception{
		List<String> tagNameList = new ArrayList<String>();
		if(null != tagList && tagList.size() > 0){
			for (Tag tag : tagList) {
				tagNameList.add(tag.getName().trim());
			}			
		}
		return tagNameList;
	}
	
	/**
	 * Get the tag object for the given product name.
	 * @param tagList : Tag(tag library) list from the DB.
	 * @param tagName : Name of tag to get the tag object from master list.
	 * @return tag object for the tag name
	 */
	private Tag getCurrentTag(List<Tag> tagList, String tagName)throws Exception{
		for (Tag tag : tagList) {
			if(tagName.trim().equals(tag.getName().trim())){
				return tag;
			}
		}
		return null;
	}
	
	/**
	 * Check if current tag associate with insight need to be delete.
	 * @param insightDTO : Insight DTO object which holds deleted list of tag by user.
	 * @param insightTag : ORM Oject of mapping between Insight and tag.
	 * @return true mean need to be delete else no.
	 */
	private boolean isTagDeleteRequired(InsightDto insightDTO,
			InsightTag insightTag)throws Exception {
		for (String delTag : insightDTO.getDelTagList()) {//loop through deleted list of product by user.
			if(delTag.equalsIgnoreCase(insightTag.getTag().getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieve master list of "Found Via"
	 */
	public List<FoundViaDto> getFoundViaDetails() throws Exception {
		ArrayList<FoundViaDto> lstFoundViaDto=new ArrayList<FoundViaDto>();// Master list of Found Via.
		List<FoundVia> returnVal = insightService.getFoundVia();
		
		for (FoundVia foundVia : returnVal) {
			FoundViaDto foundViaDTO=new FoundViaDto();
			foundViaDTO.setId(foundVia.getId());
			foundViaDTO.setName(foundVia.getName());
			lstFoundViaDto.add(foundViaDTO);
		}
		
		return lstFoundViaDto;
	}

	
	/**
	 * Retrieve master list of "Main User Type"
	 */
	public List<MainUserTypeDto> getMainUserTypeDetails() throws Exception {
		ArrayList<MainUserTypeDto> lsMainUserTypeDto=new ArrayList<MainUserTypeDto>();// Master list of Found Via.
		List<MainUserType> returnVal = insightService.getAllMainUserTypes();
		
		for (MainUserType mainUserType : returnVal) {
			MainUserTypeDto mainUserTypeDto=new MainUserTypeDto();
			mainUserTypeDto.setId(mainUserType.getId());
			mainUserTypeDto.setName(mainUserType.getName());
			lsMainUserTypeDto.add(mainUserTypeDto);
		}
		
		return lsMainUserTypeDto;
	}

	
	/**
	 * Retrieve master list of "Geographies"
	 */
	public List<GeographiesDto> getGeographiesDetails() throws Exception {
		ArrayList<GeographiesDto> lsGeographiesDto=new ArrayList<GeographiesDto>();// Master list of Geographies.
		List<Geographies> returnVal = insightService.getAllGeographis();
		
		for (Geographies geographies : returnVal) {
			GeographiesDto geographiesDto=new GeographiesDto();
			geographiesDto.setId(geographies.getId());
			geographiesDto.setName(geographies.getName());
			lsGeographiesDto.add(geographiesDto);
		}
		
		return lsGeographiesDto;
	}

	/**
	 * Insert new attachment for Insight into database.
	 * @param insightAttachmentDTO
	 */
	@Override
	public Integer saveAttachment(InsightAttachmentDto insightAttachmentDTO)
			throws Exception {
		//Get Insight from database for given id.
		InsightDetail insightDetail=null;
		List<InsightDetail> retList = (List<InsightDetail>) this
				.getHibernateTemplate()
				.findByNamedQueryAndNamedParam(
						"InsightDetail.getInsightDetailsById",
						new String[] { "id" }, new Object[] { insightAttachmentDTO.getInsightId() });
		
		if(retList!=null && retList.size()>0){
			insightDetail=retList.get(0);
		}
		
		// Save new "InsightAttachment"
		InsightAttachment insightAttachment=new InsightAttachment();
		CommonUtils.copyProperties(insightAttachment, insightAttachmentDTO);
		insightAttachment.setInsightDetail(insightDetail);
		insightAttachment.setAddedDate(new Date());
		Integer id = (Integer) this.getHibernateTemplate().save(insightAttachment);
		return id;
	}
	
	//************
	
	/**
	 * Get all WebLink associate for Insight.
	 * @param insightId
	 */
	@Override
	public List<InsightWeblinkDto> getWebLinkList(String insightId)	throws Exception {
		List<InsightWeblinkDto> lstInsightWeblinkDTO = new ArrayList<>();
		List<InsightWeblink> returnVal = (List<InsightWeblink>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightWeblink.getWeblinksForInsight",
						"id", new Integer(insightId));
		if (null != returnVal && returnVal.size() > 0) {
			for (InsightWeblink insightWeblink : returnVal) {
				InsightWeblinkDto insightWeblinkDTO=new InsightWeblinkDto();
				CommonUtils.copyProperties(insightWeblinkDTO, insightWeblink);
				insightWeblinkDTO.setInsightId(insightWeblink.getInsightDetail().getId());
				
				lstInsightWeblinkDTO.add(insightWeblinkDTO);
			}

		}
		return lstInsightWeblinkDTO;
	}


	
	/**
	 * Delete weblink data from database for id.
	 * @param weblinkId
	 */
	@Override
	public void deleteWebLink(Integer weblinkId) throws Exception {
		List<InsightWeblink> returnVal = (List<InsightWeblink>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightWeblink.getWeblinkForId",
						"id", weblinkId);
		if (null != returnVal && returnVal.size() > 0) {
			for (InsightWeblink insightWeblink : returnVal) {
				this.getHibernateTemplate().delete(insightWeblink);
			}
		}

	}
	
	
	/**
	 * Get all attachments associate for Insight from database.
	 * @param insightId
	 */
	@Override
	public List<InsightAttachmentDto> getAttachmentList(String insightId)
			throws Exception {
		List<InsightAttachmentDto> lstInsightAttachmentDTO = new ArrayList<>();
		List<InsightAttachment> returnVal = (List<InsightAttachment>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightAttachment.getAttachmentsForInsight",
						"id", new Integer(insightId));
		if (null != returnVal && returnVal.size() > 0) {
			for (InsightAttachment insightAttachment : returnVal) {
				InsightAttachmentDto insightAttachmentDTO=new InsightAttachmentDto();
				CommonUtils.copyProperties(insightAttachmentDTO, insightAttachment);
				insightAttachmentDTO.setInsightId(insightAttachment.getInsightDetail().getId());
				insightAttachmentDTO.setFileExt(CommonUtils.getFileExtenstion(insightAttachment.getFileName()));
				lstInsightAttachmentDTO.add(insightAttachmentDTO);
			}

		}
		return lstInsightAttachmentDTO;
	}
	/**
	 * Get single attachment for attachment id from database.
	 *  @param insightId
	 */
	@Override
	public InsightAttachmentDto getAttachment(String insightId)	throws Exception {
		InsightAttachmentDto insightAttachmentDTO=new InsightAttachmentDto();
		List<InsightAttachment> returnVal = (List<InsightAttachment>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightAttachment.getAttachmentsForId",
						"id", new Integer(insightId));
		if (null != returnVal && returnVal.size() > 0) {
			InsightAttachment insightAttachment =(InsightAttachment) returnVal.get(0);
			CommonUtils.copyProperties(insightAttachmentDTO, insightAttachment);
		}
		return insightAttachmentDTO;
	}
	
	/**
	 * Delete attachment for Insight.
	 * @param insightId
	 */
	@Override
	public void deleteAttachment(String insightId) throws Exception {
		List<InsightAttachment> returnVal = (List<InsightAttachment>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightAttachment.getAttachmentsForId",
						"id", new Integer(insightId));
		if (null != returnVal && returnVal.size() > 0) {
			for (InsightAttachment insightAttachment : returnVal) {
				this.getHibernateTemplate().delete(insightAttachment);
			}
		}

	}

	/**
	 * get code list map value
	 * @param codelistName
	 * @param applicationId
	 */
	@Override
	@Cacheable(value="insightCache",key="#codelistName")
	public Map<Integer, SelectValuesDto> getCodeListMap(String codelistName,String applicationId) throws Exception {
		List<SelectValuesDto> lstInsightTypesDto = null;		
		lstInsightTypesDto =  CommonUtils.getSelectValuesDtoLst(codelistName,applicationId); // populate the possible values for the insight types dropdown in Advanced Search section
		return getCodeListMap(lstInsightTypesDto);
	}
	
		
	/**
	 * get code list map value
	 * @param lstInsightTypesDto
	 */
	public Map<Integer,SelectValuesDto> getCodeListMap(List<SelectValuesDto> lstInsightTypesDto)throws Exception{
		Map<Integer,SelectValuesDto> map = new HashMap<Integer,SelectValuesDto>();
		if(lstInsightTypesDto!=null && lstInsightTypesDto.size()>0 ){
			for(SelectValuesDto insightTypesDto :lstInsightTypesDto){
				map.put(insightTypesDto.getCodeDecodedCode(), insightTypesDto);
			}
		}
		
		
		
		return map;
	}

	
	/**
	 * get value from config table base on key.
	 * @param configKey
	 */
	@Cacheable(value="searchCache",key="#configKey")
	public String getConfigValue(String configKey) throws Exception{
		//log.debug("*************AuthorChoiceDao getConfigValue key****************"+key);
		List returnVal = this.getHibernateTemplate()
		.findByNamedQueryAndNamedParam("InsightConfig.getValueByName","configKey", configKey);
		InsightConfig insightConfig=null;
		
		if (null != returnVal && returnVal.size() > 0) {
			insightConfig = (InsightConfig) returnVal.get(0);
			return insightConfig.getValue();
		}	
		return null;	
	}
	
	/**
	 * get AutoComplete Search TextList
	 * @param searchText
	 */
	@Override
	public List<InsightDetailsDto> getAutoCompleteSearchTextList(String searchText)
			throws Exception {
				
		 List<InsightDetail> list = null;		
		 List<String> paramName = new ArrayList<String>();
	     List<Object> paramValue = new ArrayList<Object>();		
		 
		 StringBuffer sbQuery = new StringBuffer();
		 sbQuery.append("select distinct id from InsightDetail id LEFT JOIN id.products as ip  ");
		 sbQuery.append("LEFT JOIN id.projects as ipj LEFT JOIN id.tags as it  ");
		 sbQuery.append("LEFT JOIN ip.product as prod LEFT JOIN ipj.project as proj LEFT JOIN it.tag as tag ");
		 sbQuery.append("where ((id.title like :keyword) OR (id.plainDescription like :keyword) ");
		 sbQuery.append("OR (prod.name like :keyword) OR (proj.name like :keyword) OR (tag.name like :keyword)) ");
		 sbQuery.append("AND id.insightApplicationID = 1 ");
		 
		 paramName.add("keyword");
		 paramValue.add("%"+searchText+"%");
		 
		 list = (List<InsightDetail>)this.getHibernateTemplate()
					.findByNamedParam(sbQuery.toString(),paramName.toArray(new String[]{}),paramValue.toArray());
		 
		 List<InsightDetailsDto> insightDetailDtoList = CommonUtils.copyProperties(list);
	    	this.getHibernateTemplate().clear();//clearing session
		
		return insightDetailDtoList;
	}
	
	/**
	 * get AdminUser Details
	 * @param addedUserId
	 */
	@Override
	public List<AdminUserDto> getadminUserDetails(Integer addedUserId)
			throws Exception {
		List<AdminUserDto> adminUserDTOList = new ArrayList<AdminUserDto>();
		List<AdminUser>  adminUserList = (List<AdminUser>)this.getHibernateTemplate().findByNamedQueryAndNamedParam("AdminUser.getAdminUser",new String[]{"addedUserId"},new Object[]{addedUserId});
		if (null != adminUserList && adminUserList.size() > 0) {
			for (AdminUser adminUser : adminUserList) {
				AdminUserDto  adminUserDTO=new AdminUserDto();
				CommonUtils.copyProperties(adminUserDTO, adminUser);
				adminUserDTOList.add(adminUserDTO);
			}

		}
		
		return adminUserDTOList;
	}

	/**
	 * Get  master list products from database.
	 * @return
	 */
	@Override
	@Cacheable(value="productLib")	
	public List<Product> getAllProducts() throws Exception{
		List<Product> productList = (List<Product>) this.getHibernateTemplate()
				.findByNamedQuery("Product.getAllProducts");
		return productList;
	}

	/**
	 * Get master list  projects data from database.
	 * @return
	 */
	@Override
	@Cacheable(value="projectLib")		
	public List<Project> getAllProjects() throws Exception{
		List<Project> projectList = (List<Project>) this.getHibernateTemplate()
				.findByNamedQuery("Project.getAllProjects");
		return projectList;
	}

	/**
	 * Get master list  Tag entity from database.
	 * @return
	 */
	@Override
	@Cacheable(value="tagLib")		
	public List<Tag> getAllTags() throws Exception{
		List<Tag> tagList = (List<Tag>) this.getHibernateTemplate()
				.findByNamedQuery("Tag.getAllTags");
		return tagList;
	}
	/**
	 * Get master list  foundVia entity from database.
	 * @return
	 */
	@Override
	@Cacheable(value="foundVia")
	public List<FoundVia> getFoundVia() throws Exception{
		List<FoundVia> returnVal = (List<FoundVia>) this.getHibernateTemplate()
				.findByNamedQuery("FoundVia.getAll");
		return returnVal;
	}
	/**
	 * Get master list  MainUserType entity from database.
	 * @return
	 */
	@Override
	@Cacheable(value="mainUserType")	
	public List<MainUserType> getAllMainUserTypes() throws Exception{
		List<MainUserType> returnVal = (List<MainUserType>) this.getHibernateTemplate()
				.findByNamedQuery("MainUserType.getAll");
		return returnVal;
	}
	/**
	 * Get master list  Geographis entity from database.
	 * @return
	 */
	@Override
	@Cacheable(value="geographies")	
	public List<Geographies> getAllGeographis() throws Exception{
		List<Geographies> returnVal = (List<Geographies>) this.getHibernateTemplate()
				.findByNamedQuery("Geographies.getAll");
		return returnVal;
	}
	
	/**
	 * Get the list of objects for insight types and severities by passing type of the name and application id
	 */
	@Override
	@Cacheable(value="insightConfig",key="#name")
	public List<SelectValuesDto> getSelectValuesDtoLst(String name,
			String applicationId) throws Exception {
		List<SelectValuesDto> decodedNamesDtoLst = new ArrayList<SelectValuesDto>();

		List<CodelistCodeDecode> returnVal= (List<CodelistCodeDecode>) this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("CodelistCodeDecode.codelistCodeDecode",
						new String[]{"name","applicationId"},new Object[]{name,Integer.valueOf(applicationId)});
		
		if(returnVal!=null && returnVal.size()>0){
			for(CodelistCodeDecode codeDecode : returnVal){
				SelectValuesDto dtoObj = new SelectValuesDto();
				dtoObj.setCodeDecodedCode(codeDecode.getCodeDecodeCode());
				dtoObj.setCodeDecodedName(codeDecode.getCodeDecodeDecode());
				dtoObj.setDisplayOrder(codeDecode.getDisplayOrder());
				decodedNamesDtoLst.add(dtoObj);
			}
			}

		return decodedNamesDtoLst;
	}
	/**
	 * Get value from "InsightConfig" table which is based on "name" column.
	 */
	@Cacheable(value="insightCache",key="#key")
	public String getConfigValueByKey(String key) throws Exception {
		List returnVal = this.getHibernateTemplate()
				.findByNamedQueryAndNamedParam("InsightConfig.getValueByName",
						"configKey", key);
		InsightConfig config = null;

		if (null != returnVal && returnVal.size() > 0) {
			config = (InsightConfig) returnVal.get(0);
			return config.getValue();
		}
		return null;

	}
	/**
	 * Get Configuration detail to connect AWS bucket.
	 * @return
	 * @throws Exception 
	 */
	public AWSBucketConfig getAWSBucketConfig() throws Exception{
		String bucketName=getConfigValueByKey(InsightsConstants.AWS_BUCKET_NAME);
		String accessKey=getConfigValueByKey(InsightsConstants.AWS_ACCESS_KEY);
		String secretKey=getConfigValueByKey(InsightsConstants.AWS_SECRET_KEY);
		AWSBucketConfig awsBucketConfig=new AWSBucketConfig();
		awsBucketConfig.setAccessKey(accessKey);
		awsBucketConfig.setBucketName(bucketName);
		awsBucketConfig.setSecretKey(secretKey);
		return awsBucketConfig;
	}

}
