package com.openplatform.weasel.sdk.response;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
public class PlatformAccessTokenResponse {

	private String access_token;
	
	private Long expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public PlatformAccessTokenResponse setAccess_token(String access_token) {
		this.access_token = access_token;
		return this;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public PlatformAccessTokenResponse setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
		return this;
	}

}
