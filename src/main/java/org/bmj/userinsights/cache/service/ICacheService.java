package org.bmj.userinsights.cache.service;



public interface ICacheService {
	
	public void clearAllCache() throws Exception ;
	public void clearCacheByName(String cacheName) throws Exception ;

}
