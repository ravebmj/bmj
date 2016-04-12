package org.bmj.userinsights.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.bmj.userinsights.awsbucket.dto.AWSFileDetail;
import org.bmj.userinsights.awsbucket.service.AwsBucketService;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.entity.InsightAttachment;
import org.bmj.userinsights.entity.InsightFoundVia;
import org.bmj.userinsights.entity.InsightGeographies;
import org.bmj.userinsights.entity.InsightMainUserType;
import org.bmj.userinsights.entity.InsightProduct;
import org.bmj.userinsights.entity.InsightProject;
import org.bmj.userinsights.entity.InsightTag;
import org.bmj.userinsights.entity.InsightWeblink;
import org.bmj.userinsights.server.AppContext;
import org.springframework.context.ApplicationContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * This class to generate PDF
 */

public class PdfReportUtility  extends PdfPageEventHelper {
	private static final  Logger log = Logger.getLogger(PdfReportUtility.class);
	/**
     * Messages resource bundle.
     */
    private static final ResourceBundle rmb = ResourceBundle.getBundle("report_messages");

	private List<InsightDetailsDto> insightDetailsDtoList;
	private Map<Integer,SelectValuesDto> insightTypeMap;
    private Map<Integer,SelectValuesDto> insightSeverityMap;
    
    
    public PdfReportUtility(List<InsightDetailsDto> insightDetailsDtoList,
			Map<Integer, SelectValuesDto> insightTypeMap,
			Map<Integer, SelectValuesDto> insightSeverityMap) {
		super();
		this.insightDetailsDtoList = insightDetailsDtoList;
		this.insightTypeMap = insightTypeMap;
		this.insightSeverityMap = insightSeverityMap;
	}
	 
	 public static synchronized PdfReportUtility getInstance(
			 List<InsightDetailsDto> insightDetailsDtoList, Map<Integer,SelectValuesDto> insightTypeMap,
			 Map<Integer,SelectValuesDto> insightSeverityMap) {
			return new PdfReportUtility(insightDetailsDtoList, insightTypeMap, insightSeverityMap);
		}

	   /**
		 * To generate report 
		 * @param request
		 * @param response
		 * @return byte 
		 */
	public byte[] generateReport(HttpServletResponse response, HttpServletRequest request) throws Exception {
		// step 1
        Document insightDetailsDocument = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(insightDetailsDocument,outputStream);
        PdfReportUtility event = new PdfReportUtility(this.insightDetailsDtoList, this.insightTypeMap,
		this.insightSeverityMap);
        writer.setPageEvent(event);
        writer.setStrictImageSequence(true);
        // step 3
		
		insightDetailsDocument.open();
		
		generatePageHeader(writer, insightDetailsDocument,request);
		
		for(InsightDetailsDto insightDto: insightDetailsDtoList){
			StringBuilder data = new StringBuilder();
			String uploadFolder=null;
			String fileId=null;
			String fileName=null;
			String fileExt=null;
			String targetPath= null;
			data.append(rmb.getString("insight_html_start"));
			
			
			constructContentSection(data,rmb.getString("insight_title"),escapingScriptTagForString(insightDto.getTitle()));
			constructContentSection(data,rmb.getString("insight_desc"),escapingScriptTagForString(StringEscapeUtils.unescapeHtml(insightDto.getDescription())));
			
			if(null != insightDto.getFoundDate()){
				constructContentSection(data,rmb.getString("insight_foundon"),CommonUtils.getEDDMMMYYYY(insightDto.getFoundDate()));
			}
		
				
			if(insightDto.getType()==1){
				
			if(null != insightDto.getFoundCount()){
				constructContentSection(data,rmb.getString("insight_strength"),insightDto.getFoundCount()+"");
			}}
			
			if(null != insightDto.getType()){
				constructContentSection(data,rmb.getString("insight_type"),insightTypeMap.get(insightDto.getType()).getCodeDecodedName());
			}
			
			if(insightDto.getType()==4){

			if(null != insightSeverityMap.get(insightDto.getInsightServerity())){
				constructContentSection(data,rmb.getString("insight_severity"),insightSeverityMap.get(insightDto.getInsightServerity()).getCodeDecodedName());
			}}
			
			
			if(null != insightDto.getProducts() && insightDto.getProducts().size() > 0){
				
				constructContentSection(data,rmb.getString("insight_products"),constructProducts(insightDto.getProducts()));
			}
			if(insightDto.getType()==1 || insightDto.getType()==2 ){
			if(null != insightDto.getProjects() && insightDto.getProjects().size() > 0){
				constructContentSection(data,rmb.getString("insight_projects"),constructProjects(insightDto.getProjects()));
			}}
			
			if((insightDto.getType()==3 || insightDto.getType()==4) && null != insightDto.getCompanyName() && !"".equals(insightDto.getCompanyName().trim()) ){
				constructContentSection(data,rmb.getString("insight_company_name"),escapingScriptTagForString(insightDto.getCompanyName()));
			}
			
			
			if(null != insightDto.getTags() && insightDto.getTags().size() > 0){
				constructContentSection(data,rmb.getString("insight_tags"),constructTags(insightDto.getTags()));
			}
			if(insightDto.getType() ==1 || insightDto.getType()==4 ){
			if(null != insightDto.getFoundVias() && insightDto.getFoundVias().size() > 0){
				constructContentSection(data,rmb.getString("insight_foundvia"),constructFoundvias(insightDto.getFoundVias()));
			}}
			if(null != insightDto.getMainUserTypes() && insightDto.getMainUserTypes().size() > 0){
				constructContentSection(data,rmb.getString("insight_mainusertype"),constructMainUserTypes(insightDto.getMainUserTypes()));
			}
			if(null != insightDto.getGeographies() && insightDto.getGeographies().size() > 0){
				constructContentSection(data,rmb.getString("insight_geographies"),constructGeographies(insightDto.getGeographies()));
			}
			if(null != insightDto.getWeblinks() && insightDto.getWeblinks().size() > 0){
				constructWeblinks(data,rmb.getString("insight_weblinks"),insightDto.getWeblinks());
			}
			
			if(null != insightDto.getAttachments() && insightDto.getAttachments().size() > 0){
				constructAttachments(data,rmb.getString("insight_attachments"),insightDto.getAttachments());
			}			
			data.append(rmb.getString("insight_html_end"));
			log.debug("Generated HTML for PDF:"+data.toString());
			XMLWorkerHelper.getInstance().parseXHtml(writer, insightDetailsDocument,
	        		new StringReader(data.toString()));
			
			
			if(null != insightDto.getAttachments() && insightDto.getAttachments().size() > 0){
				for(InsightAttachment attachement:insightDto.getAttachments()){
					if(InsightsConstants.getImageFileList().contains(CommonUtils.getFileExtenstion(attachement.getFileName()))){
						uploadFolder = attachement.getFilePath();
						fileId = attachement.getFileId();
						fileName = attachement.getFileName();
						fileExt = CommonUtils.getFileExtenstion(fileName);
						 targetPath=(new StringBuffer(uploadFolder)
							.append(File.separator)
							.append(fileId)
							.append(fileExt)
							.toString());
							
							String image_id  = String.valueOf(attachement.getInsightDetail().getId());
							String image_file_id = String.valueOf(attachement.getFileId());
							ApplicationContext ctx = null; 
							  ctx = AppContext.getApplicationContext();
							   AwsBucketService awsBucketService=(AwsBucketService)ctx.getBean("awsBucketServiceRef");
							   AWSFileDetail aFileDetail=awsBucketService.getFileFromAWSBucket(image_id, image_file_id);

							byte[] imageData = aFileDetail.getDataAsByteArray();
						Image image = Image.getInstance(imageData);
						if(insightDetailsDocument.getPageSize().getWidth() < image.getScaledWidth()){
							float scaler = ((insightDetailsDocument.getPageSize().getWidth() - insightDetailsDocument.leftMargin()
						               - insightDetailsDocument.rightMargin() - 15) / image.getWidth()) * 100;
					        image.scalePercent(scaler);
					        if(insightDetailsDocument.getPageSize().getHeight() < image.getScaledHeight()){
								scaler = ((insightDetailsDocument.getPageSize().getHeight() - insightDetailsDocument.topMargin()
							               - insightDetailsDocument.bottomMargin() - 15) / image.getHeight()) * 100;
						        image.scalePercent(scaler);
							}
						}else{
							 image.scalePercent(100);
						}
						Paragraph paragraph = new Paragraph();
						paragraph.add(fileName);
						insightDetailsDocument.add(paragraph);
						insightDetailsDocument.add( Chunk.NEWLINE );
						insightDetailsDocument.add(image);
					}
					
				}
			}
		  
			insightDetailsDocument.newPage();
		}		
		insightDetailsDocument.close();
		return outputStream.toByteArray();
	}
	
	/**
	 * To generate header of report 
	 * @param request
	 * @param response
	 */
	 private void generatePageHeader(PdfWriter writer, Document document,HttpServletRequest request) throws Exception{
		// calculate width
		float width = document.getPageSize().getWidth();
		float[] headerColSize = { 350.00F, 250.00F };
		PdfPCell cell = null;
		Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
		PdfPTable headerTable;
		headerTable = new PdfPTable(headerColSize);
		cell = new PdfPCell(new Phrase(" ",ffont));
		cell.disableBorderSide(Rectangle.RIGHT);
		cell.disableBorderSide(Rectangle.LEFT);
		cell.disableBorderSide(Rectangle.BOTTOM);			
		headerTable.addCell(cell);
		headerTable.addCell(cell);
		// set contentTable parameters.
		headerTable.getDefaultCell().setBorder(0);
		headerTable.setHorizontalAlignment(0);
		headerTable.setTotalWidth(width - 72);
		headerTable.setLockedWidth(true);
		ServletContext context = request.getSession().getServletContext();
		String contextPath = context.getRealPath("/");
		Image image = Image.getInstance(contextPath + "/images/logo.jpg");// get the AC logo image
		Date dt = new Date();	
		image.scalePercent(20);		
		image.setAlignment(Element.ALIGN_TOP);
		cell = new PdfPCell(image);
		removeBorder(cell);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		headerTable.addCell(cell);
		cell = new PdfPCell(new Phrase(rmb.getString("report_generated_date")
				+ " "
				+ DateFormat.getDateTimeInstance(DateFormat.LONG,
						DateFormat.SHORT).format(dt),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK)));
		removeBorder(cell);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);			
		headerTable.addCell(cell);
		cell = new PdfPCell(new Phrase(" ",ffont));
		cell.disableBorderSide(Rectangle.RIGHT);
		cell.disableBorderSide(Rectangle.LEFT);
		cell.disableBorderSide(Rectangle.TOP);
		cell.setBorderWidthBottom(1);
		headerTable.addCell(cell);
		headerTable.addCell(cell);
		document.add(headerTable);			
	}
	
	
	   /**
		 * To generate content section 
		 * @param content
		 * @param label
		 * @param value
		 */
	private void constructContentSection(StringBuilder content, String label,
			String value)throws Exception {	
		
		content.append(MessageFormat.format(rmb.getString("insight_html_label"),new Object[]{label})) ;  
		content.append(MessageFormat.format(rmb.getString("insight_html_value"),new Object[]{value})) ;
		
    }
	
	/**
	 * To generate weblinks section 
	 * @param content
	 * @param label
	 * @param weblinks
	 */
	private void constructWeblinks(StringBuilder content, String label,
			Set<InsightWeblink> weblinks)throws Exception {	
		
		content.append(MessageFormat.format(rmb.getString("insight_html_label"),new Object[]{label})) ;  
		for(InsightWeblink weblink:weblinks){
			content.append(MessageFormat.format(rmb.getString("insight_weblink_value"),new Object[]{weblink.getWeblinkValue()})) ;
		}		
    }
	
	/**
	 * To generate attachments section 
	 * @param content
	 * @param label
	 * @param attachments
	 */
	private void constructAttachments(StringBuilder content, String label,
			Set<InsightAttachment> attachments)throws Exception {	
		StringBuilder rows = new StringBuilder();
		content.append(MessageFormat.format(rmb.getString("insight_html_label"),new Object[]{label})) ;  
		for(InsightAttachment attachment:attachments){
			rows.append(MessageFormat.format(rmb.getString("insight_attachment_rows"),new Object[]{attachment.getFileName(),CommonUtils.round(attachment.getFileSize()/(double) (1024),2)+" KB"})) ;
		}
		content.append(MessageFormat.format(rmb.getString("insight_attachment_value"),new Object[]{rows.toString()})) ;  
    }
	
	/**
	 * To generate product section 
	 * @param productList
	 */
	private String constructProducts(Set<InsightProduct> productList)throws Exception {		
		StringBuilder products = new StringBuilder();
		for(InsightProduct product:productList){			
			products.append(product.getProduct().getName()+", ");
		}	
    	return products.substring(0, products.length() - 2).replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "&lt;/script&gt;");
    }
	
	/**
	 * To generate project section 
	 * @param projectList
	 */
	private String constructProjects(Set<InsightProject> projectList)throws Exception {		
		StringBuilder projects = new StringBuilder();
		for(InsightProject project:projectList){			
			projects.append(project.getProject().getName()+", ");
		}	
    	return projects.substring(0, projects.length() - 2).replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "&lt;/script&gt;");
    }
	
	/**
	 * To generate tag section 
	 * @param tagList
	 */
	private String constructTags(Set<InsightTag> tagList)throws Exception {		
		StringBuilder tags = new StringBuilder();
		for(InsightTag tag:tagList){			
			tags.append(tag.getTag().getName()+", ");
		}	
    	return tags.substring(0, tags.length() - 2).replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "&lt;/script&gt;");
    }
	
	/**
	 * To generate foundvia section 
	 * @param foundViaList
	 */
	private String constructFoundvias(Set<InsightFoundVia> foundViaList)throws Exception {
		
		StringBuilder foundVias = new StringBuilder();
		for(InsightFoundVia foundVia:foundViaList){	
			if(null != foundVia.getFoundVia().getName() 
					&& foundVia.getFoundVia().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				foundVias.append(foundVia.getFoundVia().getName()+" - "+foundVia.getFoundViaOtherValue()+", ");
			}else{
				foundVias.append(foundVia.getFoundVia().getName()+", ");
			}
			
		}	
    	return foundVias.substring(0, foundVias.length() - 2);
    }
	
	/**
	 * To generate mainusertype section 
	 * @param mainUserTypeList
	 */
	private String constructMainUserTypes(Set<InsightMainUserType> mainUserTypeList)throws Exception {		
		StringBuilder mainUserTypes = new StringBuilder();
		for(InsightMainUserType mainUserType:mainUserTypeList){	
			if(null != mainUserType.getMainUserType().getName() 
					&& mainUserType.getMainUserType().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				mainUserTypes.append(mainUserType.getMainUserType().getName()+" - "+mainUserType.getMainUserTypeOtherValue()+", ");
			}else{
				mainUserTypes.append(mainUserType.getMainUserType().getName()+", ");
			}
			
		}	
    	return mainUserTypes.substring(0, mainUserTypes.length() - 2);
    }
	
	/**
	 * To generate geographies section 
	 * @param geographieList
	 */
	private String constructGeographies(Set<InsightGeographies> geographieList)throws Exception {		
		StringBuilder geographies = new StringBuilder();
		for(InsightGeographies geographie:geographieList){	
			if(null != geographie.getGeographies().getName() 
					&& geographie.getGeographies().getName().equalsIgnoreCase(InsightsConstants.OTHER_FIELD_VALUE)){
				geographies.append(geographie.getGeographies().getName()+" - "+geographie.getInsightGeographicOtherValue()+", ");
			}else{
				geographies.append(geographie.getGeographies().getName()+", ");
			}
			
		}	
    	return geographies.substring(0, geographies.length() - 2);
    }
	
	private void removeBorder(PdfPCell cell){
		cell.disableBorderSide(Rectangle.TOP);
		cell.disableBorderSide(Rectangle.BOTTOM);	
		cell.disableBorderSide(Rectangle.LEFT);	
		cell.disableBorderSide(Rectangle.RIGHT);	
	}
	
	/**
	 * To generate footer of report 
	 * @param writer
	 * @param document
	 */
	public void onEndPage(PdfWriter writer, Document document){
        PdfContentByte cb = writer.getDirectContent();
        
        Phrase footer = new Phrase(MessageFormat.format(rmb.getString("report_footer"),new Object[]{CommonUtils.getCurrentYear()+""} )
 				+ "                                                                 "
 				+ rmb.getString("report_page") + " - "+document.getPageNumber(),
 				FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.ITALIC, BaseColor.BLACK));
         ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                 footer,
                 (document.right() - document.left()) / 2 ,
                 document.bottom() - 10, 0);
    }
	

	/**
	 * @return the insightDetailsDtoList
	 */
	public List<InsightDetailsDto> getInsightDetailsDtoList() {
		return insightDetailsDtoList;
	}

	/**
	 * @param insightDetailsDtoList the insightDetailsDtoList to set
	 */
	public void setInsightDetailsDtoList(
			List<InsightDetailsDto> insightDetailsDtoList) {
		this.insightDetailsDtoList = insightDetailsDtoList;
	}

	/**
	 * @return the insightTypeMap
	 */
	public Map<Integer, SelectValuesDto> getInsightTypeMap() {
		return insightTypeMap;
	}

	/**
	 * @param insightTypeMap the insightTypeMap to set
	 */
	public void setInsightTypeMap(Map<Integer, SelectValuesDto> insightTypeMap) {
		this.insightTypeMap = insightTypeMap;
	}

	/**
	 * @return the insightSeverityMap
	 */
	public Map<Integer, SelectValuesDto> getInsightSeverityMap() {
		return insightSeverityMap;
	}

	/**
	 * @param insightSeverityMap the insightSeverityMap to set
	 */
	public void setInsightSeverityMap(
			Map<Integer, SelectValuesDto> insightSeverityMap) {
		this.insightSeverityMap = insightSeverityMap;
	}
		
	/**
	 * To replace script tag for PDF
	 */
	private String escapingScriptTagForString(String info)
	{
		return info.replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "&lt;/script&gt;");
	}
	
	
}
