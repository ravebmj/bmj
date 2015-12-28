package org.bmj.userinsights.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.log4j.Logger;

// this is a test commit for Atif
// CommonUtil class to access common functionality
public class CommonUtils {
	static ResourceBundle messagesProperties= MessageBundle.getResourceBundle();
	private static final  Logger log = Logger.getLogger(CommonUtils.class);
	// This is an array for creating hex chars
	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	private static final DateFormat dailyFeedDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	private static final DateFormat monthlyFeedDateFormat = new SimpleDateFormat("MMM-yyyy");

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

		// return buf.toString();
		return hex(ba);

	} // end hashAllFields()

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
        bub.copyProperties(dest, orig);
    }
    
    
    public static String getDDMMMYYYY(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		//Date date = null;
		String dateStr = null;
		//try {
			//date = sdf.parse(dateString);
			dateStr = sdf.format(date);
		/*} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return dateStr;
	}
	

}
