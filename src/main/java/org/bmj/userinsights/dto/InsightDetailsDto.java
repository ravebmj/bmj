package org.bmj.userinsights.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bmj.userinsights.common.CommonUtils;
import org.bmj.userinsights.common.dto.InsightProductDto;
import org.bmj.userinsights.common.dto.InsightProjectDto;
import org.bmj.userinsights.common.dto.InsightTagDto;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.entity.InsightAttachment;
import org.bmj.userinsights.entity.InsightFoundVia;
import org.bmj.userinsights.entity.InsightGeographies;
import org.bmj.userinsights.entity.InsightMainUserType;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.InsightWeblink;
/**
 * DTO for InsightDetails entity.
 * @author nilesh.kambli
 *
 */
public class InsightDetailsDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5119035759859846280L;
	private Integer id;
	private String title;
	private String companyName;
	private String description;
	private String plainDescription;
	private Integer type;
	private Date foundDate;
	private Integer foundCount;
	private Integer insightServerity;
	private Integer insightApplicationID;
	private Integer addedUser;
	private Date addedDate;
	private Integer modifiedUser;
	private Date modifiedDate;
	private Set<InsightProduct> products = new HashSet<InsightProduct>();
	private Set<InsightProject> projects = new HashSet<InsightProject>();
	private Set<InsightTag> tags = new HashSet<InsightTag>();
	private Set<InsightFoundVia> foundVias = new HashSet<InsightFoundVia>();
	private Set<InsightMainUserType> mainUserTypes = new HashSet<InsightMainUserType>();
	private Set<InsightGeographies> geographies = new HashSet<InsightGeographies>();
	private Set<InsightWeblink> weblinks = new HashSet<InsightWeblink>();
	private Set<InsightAttachment> attachments = new HashSet<InsightAttachment>();
	
	//properties specific to UI start
	
	 private String lastEditedDate;
	 private String insightTypeName;
	 private int searchWeightageOrder;
	 
	 private String escpedTitle;
	 private String escpedCompanyName;
	 private Set<InsightProductDto> insightProductsDto = new HashSet<InsightProductDto>();
	 private Set<InsightProjectDto> insightProjectsDto = new HashSet<InsightProjectDto>();
	 private Set<InsightTagDto> insightTagsDto = new HashSet<InsightTagDto>();
	
	//properties specific to UI end
	
	
	public InsightDetailsDto(){}

	
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}



	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the foundDate
	 */
	public Date getFoundDate() {
		return foundDate;
	}

	/**
	 * @param foundDate the foundDate to set
	 */
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	/**
	 * @return the foundCount
	 */
	public Integer getFoundCount() {
		return foundCount;
	}

	/**
	 * @param foundCount the foundCount to set
	 */
	public void setFoundCount(Integer foundCount) {
		this.foundCount = foundCount;
	}

	/**
	 * @return the insightServerity
	 */
	public Integer getInsightServerity() {
		return insightServerity;
	}

	/**
	 * @param insightServerity the insightServerity to set
	 */
	public void setInsightServerity(Integer insightServerity) {
		this.insightServerity = insightServerity;
	}

	/**
	 * @return the insightApplicationID
	 */
	public Integer getInsightApplicationID() {
		return insightApplicationID;
	}

	/**
	 * @param insightApplicationID the insightApplicationID to set
	 */
	public void setInsightApplicationID(Integer insightApplicationID) {
		this.insightApplicationID = insightApplicationID;
	}

	/**
	 * @return the addedUser
	 */
	public Integer getAddedUser() {
		return addedUser;
	}

	/**
	 * @param addedUser the addedUser to set
	 */
	public void setAddedUser(Integer addedUser) {
		this.addedUser = addedUser;
	}

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the modifiedUser
	 */
	public Integer getModifiedUser() {
		return modifiedUser;
	}

	/**
	 * @param modifiedUser the modifiedUser to set
	 */
	public void setModifiedUser(Integer modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the products
	 */
	public Set<InsightProduct> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Set<InsightProduct> products) {
		this.products = products;
	}

	/**
	 * @return the projects
	 */
	public Set<InsightProject> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<InsightProject> projects) {
		this.projects = projects;
	}

	/**
	 * @return the tags
	 */
	public Set<InsightTag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<InsightTag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the foundVias
	 */
	public Set<InsightFoundVia> getFoundVias() {
		return foundVias;
	}

	/**
	 * @param foundVias the foundVias to set
	 */
	public void setFoundVias(Set<InsightFoundVia> foundVias) {
		this.foundVias = foundVias;
	}

	/**
	 * @return the mainUserTypes
	 */
	public Set<InsightMainUserType> getMainUserTypes() {
		return mainUserTypes;
	}

	/**
	 * @param mainUserTypes the mainUserTypes to set
	 */
	public void setMainUserTypes(Set<InsightMainUserType> mainUserTypes) {
		this.mainUserTypes = mainUserTypes;
	}

	/**
	 * @return the geographies
	 */
	public Set<InsightGeographies> getGeographies() {
		return geographies;
	}

	/**
	 * @param geographies the geographies to set
	 */
	public void setGeographies(Set<InsightGeographies> geographies) {
		this.geographies = geographies;
	}

	/**
	 * @return the weblinks
	 */
	public Set<InsightWeblink> getWeblinks() {
		return weblinks;
	}

	/**
	 * @param weblinks the weblinks to set
	 */
	public void setWeblinks(Set<InsightWeblink> weblinks) {
		this.weblinks = weblinks;
	}

	/**
	 * @return the attachments
	 */
	public Set<InsightAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Set<InsightAttachment> attachments) {
		this.attachments = attachments;
	}

	public String getLastEditedDate() throws Exception {		
		this.setLastEditedDate(CommonUtils.getDDMMMYYYY(this.getModifiedDate()));
		return lastEditedDate;
	}

	public void setLastEditedDate(String lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}

	public String getInsightTypeName() {
		if(CommonUtils.getCodeListInsightMap()!=null){
			 Map<Integer,SelectValuesDto> codeListMap = CommonUtils.getCodeListInsightMap();
			 this.setInsightTypeName(codeListMap.get(this.getType()).getCodeDecodedName());
		}
		return insightTypeName;
	}

	public void setInsightTypeName(String insightTypeName) {
		this.insightTypeName = insightTypeName;
	}

	public String getEscpedTitle() {
		if(this.getTitle()!=null){			
			this.setEscpedTitle(CommonUtils.getReplaceAll(this.getTitle()));
		}
		return escpedTitle;
	}

	public void setEscpedTitle(String escpedTitle) {
		this.escpedTitle = escpedTitle;
	}

	
	/**
	 * @return the insightProductsDto
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Set<InsightProductDto> getInsightProductsDto() {
		insightProductsDto.clear();
		if(this.getProducts()!=null && this.getProducts().size()>0){
			for(InsightProduct insightProduct : this.getProducts()){
				InsightProductDto insightProductDto = new InsightProductDto();
				try {
					CommonUtils.copyProperties(insightProductDto, insightProduct);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(insightProductDto.getProduct().getName()!=null){
					insightProductDto.getProduct().setName(CommonUtils.getReplaceAll(insightProductDto.getProduct().getName()));
				}
				insightProductsDto.add(insightProductDto);
			}
		}
		
		return insightProductsDto;
	}

	/**
	 * @param insightProductsDto the insightProductsDto to set
	 */
	public void setInsightProductsDto(Set<InsightProductDto> insightProductsDto) {
		this.insightProductsDto = insightProductsDto;
	}

	/**
	 * @return the insightProjectsDto
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Set<InsightProjectDto> getInsightProjectsDto() {
		insightProjectsDto.clear();
		if(this.getProjects()!=null && this.getProjects().size()>0){
			for(InsightProject insightProject : this.getProjects()){
				InsightProjectDto insightProjectDto = new InsightProjectDto();
				try {
					CommonUtils.copyProperties(insightProjectDto, insightProject);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(insightProjectDto.getProject().getName()!=null){
					insightProjectDto.getProject().setName(CommonUtils.getReplaceAll(insightProjectDto.getProject().getName()));
				}
				insightProjectsDto.add(insightProjectDto);
			}
		}
		return insightProjectsDto;
	}

	/**
	 * @param insightProjectsDto the insightProjectsDto to set
	 */
	public void setInsightProjectsDto(Set<InsightProjectDto> insightProjectsDto) {
		this.insightProjectsDto = insightProjectsDto;
	}

	/**
	 * @return the insightTagsDto
	 */
	public Set<InsightTagDto> getInsightTagsDto() {
		insightTagsDto.clear();
		if(this.getTags()!=null && this.getTags().size()>0){
			for(InsightTag insightTag : this.getTags()){
				InsightTagDto insightTagDto = new InsightTagDto();
				try {
					CommonUtils.copyProperties(insightTagDto, insightTag);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(insightTagDto.getTag().getName()!=null){
					insightTagDto.getTag().setName(CommonUtils.getReplaceAll(insightTagDto.getTag().getName()));
				}
				insightTagsDto.add(insightTagDto);
			}
		}
		return insightTagsDto;
	}

	/**
	 * @param insightTagsDto the insightTagsDto to set
	 */
	public void setInsightTagsDto(Set<InsightTagDto> insightTagsDto) {
		this.insightTagsDto = insightTagsDto;
	}

	/**
	 * @return the searchWeightageOrder
	 */
	public int getSearchWeightageOrder() {
		return searchWeightageOrder;
	}

	/**
	 * @param searchWeightageOrder the searchWeightageOrder to set
	 */
	public void setSearchWeightageOrder(int searchWeightageOrder) {
		this.searchWeightageOrder = searchWeightageOrder;
	}



	/**
	 * @return the escpedCompanyName
	 */
	public String getEscpedCompanyName() {
		if(this.getCompanyName()!=null){			
			this.setEscpedCompanyName(CommonUtils.getReplaceAll(this.getCompanyName()));
		}
		return escpedCompanyName;
	}



	/**
	 * @param escpedCompanyName the escpedCompanyName to set
	 */
	public void setEscpedCompanyName(String escpedCompanyName) {
		this.escpedCompanyName = escpedCompanyName;
	}



	/**
	 * @return the plainDescription
	 */
	public String getPlainDescription() {
		return plainDescription;
	}



	/**
	 * @param plainDescription the plainDescription to set
	 */
	public void setPlainDescription(String plainDescription) {
		this.plainDescription = plainDescription;
	}

	
	
	
	
}
