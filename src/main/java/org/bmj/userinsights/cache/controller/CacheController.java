package org.bmj.userinsights.cache.controller;

import java.util.ResourceBundle;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.bmj.userinsights.common.CommonUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * This class is handling cache functionality 
 * @author anurag.mishra
 *
 */
@Controller
public class CacheController {
	private static final Logger log = Logger.getLogger(CacheController.class);
	private EhCacheCacheManager ehcache;
    ResourceBundle messagesProperties= ResourceBundle.getBundle("userinsights_messages");
	/**
	 * To clear cache and show cache result page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clear/cache", method = RequestMethod.GET)
	public ModelAndView clearEachCache(HttpServletRequest request) {
		try {
			this.clearCache(request);
			ModelAndView mv = new ModelAndView("CacheSuccessPage");
			mv.addObject("cache", false);
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("CacheSuccessPage");
			mv.addObject("cache", true);
			CommonUtils.errorLoggging(log, e, messagesProperties.getString("cache_error"));
			return mv;
		}

	}
	
	/**
	 * To clear cache in the application
	 * @param request
	 * @return
	 */
	private void clearCache(HttpServletRequest request) throws Exception {
		log.info("-------------- Clearing application Cache---------------");
		CacheManager cacheManager = ehcache.getCacheManager();
		Cache insightcache = cacheManager.getCache("insightCache");
		Cache logincache = cacheManager.getCache("loginCache");
		Cache searchcache = cacheManager.getCache("searchCache");
		Cache insightConfigCache = cacheManager.getCache("insightConfig");
		Cache codeListCache = cacheManager.getCache("codeList");
		Cache mainUserTypeCache = cacheManager.getCache("mainUserType");
		Cache foundViaCache = cacheManager.getCache("foundVia");
		Cache geographiesCache = cacheManager.getCache("geographies ");
		Cache productLibCache = cacheManager.getCache("productLib");
		Cache projectLibCache = cacheManager.getCache("projectLib ");
		Cache tagLibCache = cacheManager.getCache("tagLib");
		
		
		insightcache.removeAll();
		logincache.removeAll();
		searchcache.removeAll();
		insightConfigCache.removeAll();
		codeListCache.removeAll();
		mainUserTypeCache.removeAll();
		foundViaCache.removeAll();
		geographiesCache.removeAll();
		productLibCache.removeAll();
		projectLibCache.removeAll();
		tagLibCache.removeAll();
		log.info("Cleared cache - Server : " + request.getServerName());
		log.info("-------------- application Cache cleared---------------");
	}

	/* Setting the Ehcache manager by autowiring */
	@Autowired
	public void setEhCacheManager(EhCacheCacheManager ehcache) {
		this.ehcache = ehcache;
	}
}
