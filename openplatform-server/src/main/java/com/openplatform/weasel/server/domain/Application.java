package com.openplatform.weasel.server.domain;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class Application {

	private Long id;
	
	private String appname;

	private String appkey;
	
	private String secret;
	
	private String redirectUrl;
	
	private PlatformUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public PlatformUser getUser() {
		return user;
	}

	public void setUser(PlatformUser user) {
		this.user = user;
	}
	
}
