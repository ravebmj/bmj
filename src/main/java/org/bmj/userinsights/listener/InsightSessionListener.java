package org.bmj.userinsights.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class InsightSessionListener implements HttpSessionListener {
	private static final Logger log = Logger.getLogger(InsightSessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		Date date = new Date();
		log.info(">>>>>>>> Created Session : ["+session.getId() +"] at ["+ date.toString()+"] <<<");
		log.info(">>>>>>>> InsightSession SessionListener: Created Session : ["+session.getId() +"] at ["+ date.toString()+"] <<<");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		Date date = new Date();
		log.info("<<<<<<<<< Destroyed Session : ["+session.getId() +"] at ["+ date.toString()+"] <<<");
		log.info("<<<<<<<<< InsightSession Session Listener: Destroyed Session : ["+session.getId() +"] at ["+ date.toString()+"] <<<");


	}

}
