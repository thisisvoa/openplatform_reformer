package com.openplatform.weasel.server.infrastructure.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class OauthCache {

	public final static int expiresIn = 86400; //24小时
	private final static String TOKEN = "token_";
	private final static Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(expiresIn, TimeUnit.SECONDS).maximumSize(1000000).build();
	
	public static void cacheAccessToken(String accessToken){
		cache.put(TOKEN+accessToken, accessToken);
	}
	
	public static boolean accessTokenAvailable(String accessToken){
		return null != cache.getIfPresent(TOKEN+accessToken);
	}
}
