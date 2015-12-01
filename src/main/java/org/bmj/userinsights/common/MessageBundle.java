package org.bmj.userinsights.common;

import java.util.ResourceBundle;


import org.apache.log4j.Logger;

public class MessageBundle {

	private static ResourceBundle configProperties = null;
	
	private static final Logger log = Logger.getLogger(MessageBundle.class);
	public static ResourceBundle getResourceBundle() {
		if(configProperties==null){
			String locale = "en";
			if (null != locale && !"".equals(locale)) {
				configProperties = ResourceBundle.getBundle("userinsights_messages");
				log.debug("Environment variable is configured:"+locale);
			}else{
				configProperties = ResourceBundle.getBundle("userinsights_messages");
				log.error("Locale is not identified");
			}
		}
		return configProperties;
	}



}
