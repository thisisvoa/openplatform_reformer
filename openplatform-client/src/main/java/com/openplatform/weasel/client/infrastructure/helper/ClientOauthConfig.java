package com.openplatform.weasel.client.infrastructure.helper;

import com.openplatform.weasel.sdk.helper.PropertyHelper;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月15日
 */
public class ClientOauthConfig {

	public static PropertyHelper properties = PropertyHelper.read("/oauth.properties");
	public static String appkey = properties.getProperty("appkey");
	public static String secret = properties.getProperty("secret");
	public static String redirectUrl = properties.getProperty("redirectUrl");

}
