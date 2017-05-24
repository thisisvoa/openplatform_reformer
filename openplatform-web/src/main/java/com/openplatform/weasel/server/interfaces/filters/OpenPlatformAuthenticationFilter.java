package com.openplatform.weasel.server.interfaces.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.weasel.openplatform.security.helper.ShiroSecurityHelper;

public class OpenPlatformAuthenticationFilter extends AccessControlFilter {
	
	
	private static final String DEFAULT_LOGIN_URL = "/open/login";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		return ShiroSecurityHelper.hasAuthenticated();
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		redirectToLogin(request, response);
		return false;
	}

	@Override
	public String getLoginUrl() {
		
		return DEFAULT_LOGIN_URL;
	}

}
