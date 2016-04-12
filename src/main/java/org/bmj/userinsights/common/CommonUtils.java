package org.bmj.userinsights.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.bmj.userinsights.common.dto.SelectValuesDto;
import org.bmj.userinsights.dto.InsightDetailsDto;
import org.bmj.userinsights.dto.ProductDto;
import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.Product;
import org.bmj.userinsights.insight.service.IInsightService;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;


// this is a test commit for Atif
// CommonUtil class to access common functionality
public class CommonUtils {
	static ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	private static final  Logger log = Logger.getLogger(CommonUtils.class);
	private static Map<Integer, SelectValuesDto> codeListInsightMap;	
	
	private static IInsightService insightService;
	// This is an array for creating hex chars
	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	public static final String VALID_USER = "ValidUser";
	public static final String INVALID_USER = "InValidUser";
	public static final String CONFIG_VALUE_PASSWORD_PASS_PHRASE = "PASSWORD_PASS_PHRASE";
	public static final String ENCODING_KEY = "BMJ_userinsight@2016#rAveDev2015";
	public static final String CONFIG_VALUE_RECENT_INSIGHT_LIST_SIZE = "RECENT_INSIGHT_LIST_SIZE";
	public static final String VALID_USER_ADVANCE_SEARCH = "ValidUser AdvSearch";
	public static final Integer INSIGHT_DELETE_SCHEDULAR_TIME_IN_SECONDS = 180;
	public static final String CONFIG_VALUE_PRODUCT_LIST_SIZE = "CONFIG_VALUE_PRODUCT_LIST_SIZE";


	public static void errorLoggging(Logger log, Exception e, String message) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		log.error(message + "\n" + stacktrace);
	}

	/**
	 * This method is for sorting the fields and creating an MD5 secure hash.
	 * 
	 * @param fields
	 *            is a map of all the incoming hey-value pairs from the VPC
	 * @param buf
	 *            is the hash being returned for comparison to the incoming hash
	 */
	public static String hashAllFields(Map fields, final String SECURE_SECRET) {
		// create a list and sort it
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);

		// iterate through the list and add the remaining field values
		Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				buf.append(fieldValue);
			}
		}

		MessageDigest md5 = null;
		byte[] ba = null;

		// create the md5 hash and UTF-8 encode it
		try {
			md5 = MessageDigest.getInstance("MD5");
			ba = md5.digest(buf.toString().getBytes("UTF-8"));
		} catch (Exception e) {
		} // wont happen

		return hex(ba);

	}

	/**
	 * Returns Hex output of byte array
	 */
	static String hex(byte[] input) {
		// create a StringBuffer 2x the size of the hash array
		StringBuffer sb = new StringBuffer(input.length * 2);

		// retrieve the byte array data, convert it to hex
		// and add it to the StringBuffer
		for (int i = 0; i < input.length; i++) {
			sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
			sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		return sb.toString();
	}

	/**
	 * Method to generate the current date and time as String.
	 * @return
	 */
	public static String getCurrentDateString() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssS");
		Date date = new Date();
		return (dateFormat.format(date));
	}
	
	public static List<InsightDetailsDto> copyProperties(List<InsightDetail> soruceList){
		List<InsightDetailsDto> destList = new ArrayList<InsightDetailsDto>();
		for(InsightDetail id: soruceList){
			try {
				InsightDetailsDto insightDetailsDto = new InsightDetailsDto();
				CommonUtils.copyProperties(insightDetailsDto, id);
				destList.add(insightDetailsDto);
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return destList;
	}

	/**
     * Out of the box, BeanUtils.copyProperties doesnt handle NULL java.sql.Date fields and throws a
     * conversion error. This utility method registers the correct convertor to handle this. This is
     * done locally for this class to avoid any impact on other classes that might rely on the standard
     * behaviour.
     *
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     *
     */
    public static void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean bub = new BeanUtilsBean();
        bub.getConvertUtils().register(new SqlDateConverter(null), java.sql.Date.class);
        bub.getConvertUtils().register(new BigDecimalConverter(new BigDecimal("0")), java.math.BigDecimal.class);
        bub.getConvertUtils().register(new DateConverter(null), Date.class);
        bub.copyProperties(dest, orig);
    }
    
    /**
	 * Method to generate the current date and time as String.
	 * @return
	 */
    public static String getDDMMMYYYY(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String dateStr = null;
		
			dateStr = sdf.format(date);
		
		return dateStr;
	}
    
    /**
     * Method to format the date in the format Tue Jun 10 2015
     * @param date
     * @return
     */
    public static String getEDDMMMYYYY(Date date){
    	String dateStr = null;
    	SimpleDateFormat format = new SimpleDateFormat("E MMM dd yyyy"); 
    	dateStr = format.format(date); 

    	return dateStr;
    }
    
    /**
	 * Method to generate the current date and time as String.
	 * @return
	 */
    public static Date getYYYYMMDD(String datestr){
		SimpleDateFormat orginalFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat convertedFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Date convertedDate = null;
		try {
			Date date = convertedFormat.parse(datestr);
			convertedDate = orginalFormat.parse(orginalFormat.format(date));
		} catch (ParseException e) {
			
		}
		return convertedDate;
	}
    
    /**
     * write contents of PDF file to output stream.
     * @param contents
     * @param fileName
     */
    public static void writeFiletoStream(byte[] contents, String fileName, HttpServletResponse response,String fileType) throws Exception{           
            response.setContentType("application/"+fileType);
            response.setContentLength(contents.length);
            response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName+"\"");

            ServletOutputStream out = response.getOutputStream();
            out.write(contents);
            out.flush();
            out.close();           
    }
    
    /**
	 * Method to generate the current year
	 * @return
	 */
    public static Integer getCurrentYear() {
  		Calendar cal = new GregorianCalendar();
		Integer currentYear = cal.get(Calendar.YEAR);
		return currentYear;
  	}

    public static Map<Integer, SelectValuesDto> getCodeListMap(String codelistName,String applicationId) throws Exception{
		
		return insightService.getCodeListMap(codelistName,applicationId);
	}

	public static Map<Integer, SelectValuesDto> getCodeListInsightMap() {
		return codeListInsightMap;
	}

	public static void setCodeListInsightMap(
			Map<Integer, SelectValuesDto> codeListInsightMap) {
		CommonUtils.codeListInsightMap = codeListInsightMap;
	}

	public static IInsightService getInsightService() {
		return insightService;
	}

	public static void setInsightService(IInsightService insightService) {
		CommonUtils.insightService = insightService;
	}

	public static String getConfigValue(String key) throws Exception{
		
		try {
			return insightService.getConfigValue(key);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			log.error("Error occured while getting the config values:"+"\n"+stacktrace);
			throw e;
		}
	}

	/**
	 * Get extension of file with dot.
	 * @param filename
	 * @return
	 */
	public static String getFileExtenstion(String filename) {
		return filename.substring(filename.lastIndexOf("."), filename.length());
	}
	/**
	 * This is id with "BMJ" text and current time stamp combination.
	 * Using at Attachments for file id and URLS for urlId.
	 * @return
	 */
	public static String getBMJCurrentTimeStampId() {
	 return (new StringBuffer(InsightsConstants.BMJ_APP_NAME)
		.append(CommonUtils.getCurrentDateString()).toString());
	}
	/**
	 * Round double value to required places.
	 * @param value
	 * @param places
	 * @return
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	
	/**
	 * Method to create a pdf with error message receipt when there is a exception while generating the payment receipt.
	 * @param response
	 * @throws Exception
	 */
	public static void createErrorPDF(HttpServletResponse response) throws Exception{
		Document errorDocument = new Document();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
		PdfWriter.getInstance(errorDocument, outputStream);
		errorDocument.open();
		Paragraph errorMessage = new Paragraph(new Phrase(messagesProperties.getString("report_pdf_error_message"),
				FontFactory.getFont(FontFactory.TIMES_BOLD, 8)));
		errorDocument.add(errorMessage);
		errorDocument.close();
		log.info("Creatng the error pdf ends");
		writeFiletoStream(outputStream.toByteArray(), messagesProperties.getString("report_pdf_error_file_name")+".pdf",
				response,"pdf");
	}	
	
	/**
	 * Return date as Date type for string date.
	 * @param strDate: Date is string format.
	 * @param format: Required format of date. 
	 * @return
	 */
	public static Date getDateByFormat(String strDate,String format) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		Date date = null;
		date = sdf.parse(strDate);
		return date;
	}
	/**
	 * Return date as string type for Date type date.
	 * @param date: Date is Date type.
	 * @param format: Required format of date. 
	 * @return
	 */
	public static String getDateAsStringByFormat(Date date,String format) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat(format);
		return (dateFormat.format(date));
	}  
	
	 /**
	  * create severity codelist map
	  * @param lstSeveritiesDto
	  * @return
	  * @throws Exception
	  */
	 public static Map<Integer,String> getSeverityCodeListMap(List<SelectValuesDto> lstSeveritiesDto) throws Exception{
		 Map<Integer,String> severityMap = new HashMap<Integer,String>();
		 for(SelectValuesDto severitiesDto : lstSeveritiesDto){
			 severityMap.put(severitiesDto.getCodeDecodedCode(), severitiesDto.getCodeDecodedName());
		 }
		 
		return  severityMap;
	 }

	/**
	 * This method is used to replace the script tags	
	 * @param name
	 * @return
	 */
	public static String getReplaceAll(String name){
		if(name!=null){
			return name.replaceAll("<'\'/", "&lt;/").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot");
		}
		
		return "";
	}
	/**
	 * This method is used to replace the \n\r\t with empty string on create insight page title
	 * @param replaceStr
	 * @return
	 */
	public static String replaceNewLineCrraigeReturnTabSpaces(String replaceStr){
		if(replaceStr!=null){
			return replaceStr.replaceAll("\\r\\n|\\r|\\n|\t", "");
		}
		return "";
	}
	
	/**
	 * Method remove all the html tags and return the plain text.
	 * @param htmlFormatedDescription
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static String parseToPlainText(String htmlFormatedDescription) throws IOException, SAXException, TikaException {
		System.out.println(htmlFormatedDescription);
		 	InputStream stream = new ByteArrayInputStream(("<body>"+htmlFormatedDescription+"</body>").getBytes(StandardCharsets.UTF_8));
	        ContentHandler handler = new BodyContentHandler();
	        Metadata metadata = new Metadata();
	        AutoDetectParser parser = new AutoDetectParser();
	        parser.parse(stream, handler, metadata, new ParseContext());
	        String plainText = handler.toString();
	        System.out.println(plainText);
			return plainText;
		   
		}
	/**
	 * This is common method to retrive advance search insight types, severities and datecriteria codelist values
	 * @param codelistName
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public static List<SelectValuesDto> getSelectValuesDtoLst(String codelistName,String applicationId) throws Exception{
		return insightService.getSelectValuesDtoLst(codelistName, applicationId);
		
	}
	/**
	 * Get Base URL of currently requested url.
	 * @param requestURL
	 * @param requestURI
	 * @return
	 */
	public static String getBaseUrl(String  requestURL,String  requestURI){
		String returnUrl = "";
		try{
		
			String appName=requestURI.substring(1, requestURI.indexOf("/", 1));
			String contextPath = requestURL.substring(0, requestURL.indexOf(appName));
			returnUrl=contextPath+appName+"/";
			log.debug(" BaseUrl  "+returnUrl);
		}catch (Exception e){
			CommonUtils.errorLoggging(log, e, "Error while retrieving getBaseUrl");
	    }
		return returnUrl;
	}	
}
