package com.openplatform.weasel.sdk.request;

import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
public class PlatformAccesssTokenRequest {

	private GrantType grantType = GrantType.AUTHORIZATION_CODE;

	private String code;

	private String appkey;

	private String secret;

	private String redirectUrl;
	
	private String refreshAccessToken;

	public GrantType getGrantType() {
		return grantType;
	}

	public PlatformAccesssTokenRequest setGrantType(GrantType grantType) {
		this.grantType = grantType;
		return this;
	}

	public String getAppkey() {
		return appkey;
	}

	public PlatformAccesssTokenRequest setAppkey(String appkey) {
		this.appkey = appkey;
		return this;
	}

	public String getSecret() {
		return secret;
	}

	public PlatformAccesssTokenRequest setSecret(String secret) {
		this.secret = secret;
		return this;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public PlatformAccesssTokenRequest setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return this;
	}

	public String getCode() {
		return code;
	}

	public PlatformAccesssTokenRequest setCode(String code) {
		this.code = code;
		return this;
	}

	public String getRefreshAccessToken() {
		return refreshAccessToken;
	}

	public PlatformAccesssTokenRequest setRefreshAccessToken(String refreshAccessToken) {
		this.refreshAccessToken = refreshAccessToken;
		return this;
	}

}
