package com.openplatform.weasel.sdk;

import com.openplatform.weasel.sdk.helper.PropertyHelper;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月15日
 */
public class OpenPlatformOauthConfig {

	public static PropertyHelper properties = PropertyHelper.read("/_oauth.properties");
	public static String tokenUrl = properties.getProperty("tokenUrl");
	public static String authorizeUrl = properties.getProperty("authorizeUrl");
	public static String userResourceUrl = properties.getProperty("userResourceUrl");

}
