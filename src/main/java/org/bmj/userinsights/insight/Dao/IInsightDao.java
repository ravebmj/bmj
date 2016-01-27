package org.bmj.userinsights.insight.Dao;

import java.util.List;
import java.util.Map;

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
import org.bmj.userinsights.entity.FoundVia;
import org.bmj.userinsights.entity.Geographies;
import org.bmj.userinsights.entity.MainUserType;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.entity.Project;
import org.bmj.userinsights.entity.Tag;
import org.bmj.userinsights.insight.dto.InsightDto;
import org.bmj.userinsights.insight.dto.InsightWeblinkDto;
/**
 * This interface having the database operation abstract methods and implementation given in Dao class
 */
public interface IInsightDao {
	/**
	 * Get insight details and related objects and populate in to view insight page
	 * @param insightId
	 * @return
	 * @throws Exception
	 */
	public List<InsightDto> getInsightDetails(String insightId) throws Exception;
	/**
	 * return autocomplete list of product
	 * @param prodName
	 * @return
	 * @throws Exception
	 */
	public List<ProductDto> getAutoCompleteProductList(String prodName) throws Exception;
	/**
	 * return autocomplete list of project.
	 * @param projName
	 * @return
	 * @throws Exception
	 */
	public List<ProjectDto> getAutoCompleteProjectList(String projName) throws Exception;
	/**
	 * return autocomplete list of tags.
	 * @param tagName
	 * @return
	 * @throws Exception
	 */
	public List<TagDto> getAutoCompleteTagList(String tagName) throws Exception;
	/**
	 *  return autocomplete list of insights.
	 * @param insightName
	 * @return
	 * @throws Exception
	 */
	public List<InsightDto> getAutoCompleteInsightsList(String insightName) throws Exception;
	/**
	 * Save insight details in case of New and Edit.
	 * @param insightDTO
	 * @return
	 * @throws Exception
	 */
	public Integer saveInsightDetails(InsightDto insightDTO) throws Exception;
	/**
	 * Retrieve master list of "Found Via"
	 * @return
	 * @throws Exception
	 */
	public List<FoundViaDto> getFoundViaDetails() throws Exception;
	/**
	 * Retrieve master list of "Main User Type"
	 * @return
	 * @throws Exception
	 */
	public List<MainUserTypeDto> getMainUserTypeDetails() throws Exception;
	/**
	 * Retrieve master list of "Geographies"
	 * @return
	 * @throws Exception
	 */
	public List<GeographiesDto> getGeographiesDetails() throws Exception;
	/**
	 * get code list map value for the advance search 
	 * @param codelistName
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, SelectValuesDto> getCodeListMap(String codelistName,String applicationId)  throws Exception;
	/**
	 * Save attachments for new insight.
	 * @param insightAttachmentDTO
	 * @return
	 * @throws Exception
	 */
	public Integer saveAttachment(InsightAttachmentDto insightAttachmentDTO) throws Exception;
	/**
	 * Get all attachments associate for Insight.
	 * @param insightId
	 * @return
	 * @throws Exception
	 */
	public List<InsightAttachmentDto> getAttachmentList(String insightId) throws Exception;
	/**
	 * Delete attachment for Insight.
	 * @param insightId
	 * @throws Exception
	 */
	public void deleteAttachment(String insightId) throws Exception ;
	/**
	 * Get single attachment for attachment id.
	 */
	public InsightAttachmentDto getAttachment(String insightId)	throws Exception;
	/**
	 *  get value from config table base on key.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getConfigValue(String key) throws Exception;
	/**
	 * Get all WebLink associate for Insight.
	 * @param insightId
	 * @return
	 * @throws Exception
	 */
	public List<InsightWeblinkDto> getWebLinkList(String insightId)	throws Exception;
	/**
	 * Delete weblink for id.
	 * @param insightId
	 * @throws Exception
	 */
	public void deleteWebLink(Integer insightId) throws Exception;
	/**
	 * get AutoComplete Search TextList
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	public List<InsightDetailsDto> getAutoCompleteSearchTextList(String searchText) throws Exception;
	/**
	 * get AdminUser Details
	 * @param addedUserId
	 * @return
	 * @throws Exception
	 */
	public List<AdminUserDto> getadminUserDetails(Integer addedUserId) throws Exception;
	/**
	 * Get all foundVia entity from database.
	 * @return
	 * @throws Exception
	 */
	public List<FoundVia> getFoundVia() throws Exception;
	/**
	 * Get all MainUserType entity from database.
	 * @return
	 * @throws Exception
	 */
	public List<MainUserType> getAllMainUserTypes() throws Exception;
	/**
	 * Get all Geographis entity from database.
	 * @return
	 * @throws Exception
	 */
	public List<Geographies> getAllGeographis() throws Exception;
	/**
	 * Process newly added list of tags by user on UI screen.
	 * @return
	 * @throws Exception
	 */
	public List<Tag> getAllTags() throws Exception;
	/**
	 * Get all projects data from database.
	 * @return
	 * @throws Exception
	 */
	public List<Project> getAllProjects() throws Exception;
	/**
	 *  Process newly added list of products by user on UI screen.
	 * @return
	 * @throws Exception
	 */
	public List<Product> getAllProducts() throws Exception;
	
	/**
	 * This method will retrieve the dropdown values for user insight types and severity
	 * @param codelistName
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception;
	/**
	 * Get value from "InsightConfig" table which is based on "name" column.
	 * This method will return the config value for respective config key
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getConfigValueByKey(String key) throws Exception;
	/**
	 * Get Configuration detail to connect AWS bucket.
	 * This method will return all Amazon Webservices configuration parameters as AWSBucketConfig object
	 * @return
	 * @throws Exception
	 */
	public AWSBucketConfig getAWSBucketConfig() throws Exception;
}
