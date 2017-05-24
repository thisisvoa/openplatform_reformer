package com.openplatform.weasel.sdk.request;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
public abstract class PlatformRequest {

	private String appkey;

	private String secret;

	private String redirectUrl;

	private int pageSize = 20;

	private int pageNo = 0;

	public int getPageSize() {
		return pageSize;
	}

	public PlatformRequest setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public PlatformRequest setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
	
	public String getAppkey() {
		return appkey;
	}

	public PlatformRequest setAppkey(String appkey) {
		this.appkey = appkey;
		return this;
	}

	public String getSecret() {
		return secret;
	}

	public PlatformRequest setSecret(String secret) {
		this.secret = secret;
		return this;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public PlatformRequest setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		return this;
	}

	public abstract String getUrl();
}
