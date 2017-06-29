package com.openplatform.weasel.sdk.request;

import com.openplatform.weasel.sdk.OpenPlatformOauthConfig;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class PlatformUserResourceRequest extends PlatformRequest{

	@Override
	public String getUrl() {
		return OpenPlatformOauthConfig.userResourceUrl;
	}

}
