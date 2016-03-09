package org.bmj.userinsights.cache.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class CacheService implements ICacheService {
	
	@Autowired
	private EhCacheCacheManager ehcache;

	@Override
	public void clearAllCache() throws Exception {
		CacheManager cacheManager = ehcache.getCacheManager();
		
		Cache insightcache = cacheManager.getCache("insightCache");
		Cache logincache = cacheManager.getCache("loginCache");
		Cache searchcache = cacheManager.getCache("searchCache");
		Cache insightConfigCache = cacheManager.getCache("insightConfig");
		Cache codeListCache = cacheManager.getCache("codeList");
		Cache mainUserTypeCache = cacheManager.getCache("mainUserType");
		Cache foundViaCache = cacheManager.getCache("foundVia");
		Cache geographiesCache = cacheManager.getCache("geographies");
		Cache productLibCache = cacheManager.getCache("productLib");
		Cache projectLibCache = cacheManager.getCache("projectLib");
		Cache tagLibCache = cacheManager.getCache("tagLib");
		Cache insightTitleCache = cacheManager.getCache("insightTitleCache");
		
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
		insightTitleCache.removeAll();
	}

	@Override
	public void clearCacheByName(String cacheName) throws Exception {
		CacheManager cacheManager = ehcache.getCacheManager();
		Cache cacheType = cacheManager.getCache(cacheName);
		cacheType.removeAll();
	}

}
