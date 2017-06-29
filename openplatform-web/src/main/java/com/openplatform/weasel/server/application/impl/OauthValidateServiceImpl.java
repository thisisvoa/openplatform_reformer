package com.openplatform.weasel.server.application.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.openplatform.weasel.server.application.ApplicationService;
import com.openplatform.weasel.server.application.OauthValidateService;
import com.weasel.openplatform.security.helper.ShiroSecurityHelper;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OauthValidateServiceImpl implements OauthValidateService {
	
	private static Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).maximumSize(1000000).build();

	@Autowired
	private ApplicationService applicationService;
	
	@Override
	public void validateAppkey(String appkey) throws OAuthProblemException {
		if(!applicationService.appkeyAvailable(appkey)){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.TokenResponse.INVALID_CLIENT, "appkey无效");
			exception.setRedirectUri("redirect:/oauth/auth-error");
			exception.setParameter("message", "appkey无效");
			throw exception;
		}
	}

	@Override
	public void validateSecret(String secret)  throws OAuthProblemException {
		if(!applicationService.secretAvailable(secret)){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.TokenResponse.INVALID_CLIENT, "secret无效");
			exception.setRedirectUri("redirect:/oauth/auth-error");
			exception.setParameter("message", "secret无效");
			throw exception;
		}
	}

	@Override
	public void validateRedirectUrl(String appkey, String redirectUrl)  throws OAuthProblemException {
		if(!applicationService.redirectUrlAvailable(appkey, redirectUrl)){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.OAUTH_ERROR_URI, "redirectUrl无效");
			exception.setRedirectUri("redirect:/oauth/auth-error");
			exception.setParameter("message", "redirectUrl无效");
			throw exception;
		}
	}

	@Override
	public void validateAuthenticated() throws OAuthProblemException {
		if(!ShiroSecurityHelper.hasAuthenticated()){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT, "用户未授权");
			exception.setRedirectUri("open/login");
			exception.setParameter("message", "用户未授权");
			throw exception;
		}
	}

	@Override
	public void validateAuthCode(String authCode)  throws OAuthProblemException {
		String ifPresent = cache.getIfPresent(authCode);
		boolean b = !StringUtils.hasText(ifPresent);
		if(b){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT, "授权码错误");
			exception.setRedirectUri("redirect:/oauth/auth-error");
			exception.setParameter("message", "授权码错误");
			throw exception;
		}
	}

	@Override
	public void validateAccessToken(String acccessToken)  throws OAuthProblemException {
		if(!StringUtils.hasText(cache.getIfPresent(acccessToken))){
			OAuthProblemException exception = OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT, "令牌错误");
			exception.setRedirectUri("redirect:/oauth/auth-error");
			exception.setParameter("message", "令牌错误");
			throw exception;
		}
	}

	@Override
	public void cacheAuthCode(String authCode,String username) {
		cache.put(authCode, username);
	}

	@Override
	public void cacheAccessToken(String accessToken,String username) {
		cache.put(accessToken, username);
	}

	@Override
	public String getUsernameByAccessToken(String accessToken) {
		return cache.getIfPresent(accessToken);
	}

	@Override
	public String getUsernameByAuthCode(String authCode) {
		return cache.getIfPresent(authCode);
	}
	
}
