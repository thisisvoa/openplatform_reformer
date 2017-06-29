package com.openplatform.weasel.sdk.response;

import java.util.List;

import com.openplatform.weasel.sdk.resources.PlatformUserResource;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class PlatformUserResourceResponse {
	
	private String errorCode;
	
	private String body;
	
	private List<PlatformUserResource> platformUserResources;
	
	public String getErrorCode() {
		return errorCode;
	}

	public PlatformUserResourceResponse setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getBody() {
		return body;
	}

	public PlatformUserResourceResponse setBody(String body) {
		this.body = body;
		return this;
	}

	public List<PlatformUserResource> getPlatformUserResources() {
		return platformUserResources;
	}

	public void setPlatformUserResources(List<PlatformUserResource> platformUserResources) {
		this.platformUserResources = platformUserResources;
	}
	
}
