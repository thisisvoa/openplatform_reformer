package com.openplatform.weasel.server.application;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;


public interface OauthValidateService {

	/**
	 * 验证appkey是否有效
	 * @param appkey
	 */
	void validateAppkey(String appkey) throws OAuthProblemException ;
	/**
	 * 验证secret是否有效
	 * @param secret
	 */
	void validateSecret(String secret) throws OAuthProblemException ;
	/**
	 * 验证redirectUrl是否有效
	 * @param appkey
	 * @param redirectUrl
	 */
	void validateRedirectUrl(String appkey, String redirectUrl) throws OAuthProblemException ;
	
	/**
	 * 验证是否已授权
	 * @param secret
	 */
	void validateAuthenticated() throws OAuthProblemException ;
	
	/**验证authCode
	 * @param authCode
	 */
	void validateAuthCode(String authCode)  throws OAuthProblemException ;
	
	/**验证accessToken
	 * @param acccessToken
	 */
	void validateAccessToken(String accessToken)  throws OAuthProblemException ;
	
	/**
	 * 缓存authCode
	 * @param authCode
	 */
	void cacheAuthCode(String authCode, String username);
	
	/**缓存accessToken
	 * @param accessToken
	 */
	void cacheAccessToken(String accessToken, String username);
	
	
	/**根据accessToken获取用户名
	 * @param accessToken
	 * @return
	 */
	String getUsernameByAccessToken(String accessToken);
	
	/**根据authCode获取用户名
	 * @param authCode
	 * @return
	 */
	String getUsernameByAuthCode(String authCode);
}
