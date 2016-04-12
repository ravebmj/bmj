package org.bmj.userinsights.common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.bmj.userinsights.common.dto.SelectValuesDto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;


/**
 * This class constants used across application 
 */
public class InsightsConstants {
	public static final String[] arrAuthenticationPages = {"createInsight"}; // List of pages for which authentication is required.
	public static final String SECRET_KEY = "secretKey";
	public static final String TOKEN_PREFIX = "BMJ";
	public static final String TXN_DTL_REF_PREFIX = "TXN_DTL";
	
	
	public static final String APPLICATION_ID = "1";
	public static final String INSIGHT_TYPE_CODE_LIST_NAME = "InsightType";
	public static final String SEVERITY_CODE_LIST_NAME = "Severity";
	
	public static final String OTHER_FIELD_VALUE="other";
	/*PDF report constants*/
	public static final Font HEADERFONTROW = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
	public static final Font FOOTERFONTROW = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.ITALIC, BaseColor.BLACK);
	public static final Font BLACKFONTROW = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
	public static final Color HEADERCOLOR = Color.decode("#D8D8D8");
	
	public static final String DOC_ROOT="DOC_ROOT";
	public static final String FILE_STORAGE_FOLDER="FILE_STORAGE_FOLDER";
	public static final String BMJ_APP_NAME="bmjinsight";
	public static final String TEMP_FOLDER_NAME = "temp";
	public static final String FOUND_DATE_FORMAT = "MMM dd yyyy";
	public static final double maxFileSizeInBytes=15728640;
	public static final String SEVERITY_IMAGE_PATH="/resources/images/";
	public static final String SEVERITY_IMAGES_FILE_EXT=".jpg";
	public static final String FILE_IMAGE_EXT=".png";
	public static final String ZIP_FILE_EXT = ".zip";
	public static final List<String> lstValidFIleExtensions = new ArrayList<String>();	
	public static final String ERROR_MESSAGE_HTTP_ERROR="error_message_http_error"; 
	public static final String AWS_BUCKET_NAME="AWS_BUCKET_NAME";
	public static final String AWS_ACCESS_KEY="AWS_ACCESS_KEY";
	public static final String AWS_SECRET_KEY="AWS_SECRET_KEY";
	public static final String CACHE_NAME_PRODUCT="productLib";
	public static final String CACHE_NAME_PROJECT="projectLib";
	public static final String CACHE_NAME_TAG="tagLib";
	public static final String CACHE_NAME_INSIGHT_TITLE="insightTitleCache";
	public static final String CACHE_NAME_SEARCH="searchCache";
	public static final Integer CREATED_DATE_VALUE=1;
	public static final Integer EDITED_DATE_VALUE=2;
	
	
	public static final List<String> getImageFileList(){
		List<String> imageFileList = new ArrayList<String>();		
		imageFileList.add(".jpg");
		imageFileList.add(".jpeg");
		imageFileList.add(".png");
		imageFileList.add(".bmp");
		imageFileList.add(".tiff");
		imageFileList.add(".tif");
		imageFileList.add(".gif");		
		imageFileList.add(".JPG");
		imageFileList.add(".JPEG");
		imageFileList.add(".PNG");
		imageFileList.add(".BMP");
		imageFileList.add(".TIFF");
		imageFileList.add(".TIF");
		imageFileList.add(".GIF");	
		return imageFileList;
	}
	
	public static List<SelectValuesDto> getDateCriteriaLst(){
		
		List<SelectValuesDto> dateCriteriaDtoLst = new ArrayList<SelectValuesDto>();
		
		SelectValuesDto createDateCriteriaDtoObj = new SelectValuesDto();
		createDateCriteriaDtoObj.setCodeDecodedCode(1);
		createDateCriteriaDtoObj.setCodeDecodedName("Created Date");
		
		SelectValuesDto endDateCriteriaDtoObj = new SelectValuesDto();
		endDateCriteriaDtoObj.setCodeDecodedCode(2);
		endDateCriteriaDtoObj.setCodeDecodedName("Edited Date");
		
		dateCriteriaDtoLst.add(createDateCriteriaDtoObj);
		dateCriteriaDtoLst.add(endDateCriteriaDtoObj);
		
		return dateCriteriaDtoLst;
		
	}
	
		
	public static final List<String> getValidFileExtension(){
		lstValidFIleExtensions.add(".jpg");
		lstValidFIleExtensions.add(".jpeg");
		lstValidFIleExtensions.add(".png");
		lstValidFIleExtensions.add(".bmp");
		lstValidFIleExtensions.add(".tiff");
		lstValidFIleExtensions.add(".tif");
		lstValidFIleExtensions.add(".gif");		
		lstValidFIleExtensions.add(".doc");
		lstValidFIleExtensions.add(".docx");
		lstValidFIleExtensions.add(".xls");
		lstValidFIleExtensions.add(".xlsx");
		lstValidFIleExtensions.add(".ppt");
		lstValidFIleExtensions.add(".pptx");
		lstValidFIleExtensions.add(".csv");	
		lstValidFIleExtensions.add(".pdf");	
		
		lstValidFIleExtensions.add(".JPG");
		lstValidFIleExtensions.add(".JPEG");
		lstValidFIleExtensions.add(".PNG");
		lstValidFIleExtensions.add(".BMP");
		lstValidFIleExtensions.add(".TIFF");
		lstValidFIleExtensions.add(".TIF");
		lstValidFIleExtensions.add(".GIF");		
		lstValidFIleExtensions.add(".DOC");
		lstValidFIleExtensions.add(".DOCX");
		lstValidFIleExtensions.add(".XLS");
		lstValidFIleExtensions.add(".XLSX");
		lstValidFIleExtensions.add(".PPT");
		lstValidFIleExtensions.add(".PPTX");
		lstValidFIleExtensions.add(".CSV");	
		lstValidFIleExtensions.add(".PDF");	
		return lstValidFIleExtensions;
	}
	public static final int MAX_FILE_NAME_LENGTH=255;
}
