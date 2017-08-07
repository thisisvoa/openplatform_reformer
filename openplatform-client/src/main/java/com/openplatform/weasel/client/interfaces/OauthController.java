package com.openplatform.weasel.client.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.openplatform.weasel.client.infrastructure.helper.ClientOauthConfig;
import com.openplatform.weasel.sdk.DefaultPlatformClient;
import com.openplatform.weasel.sdk.OpenPlatformOauthConfig;
import com.openplatform.weasel.sdk.request.PlatformAccesssTokenRequest;
import com.openplatform.weasel.sdk.request.PlatformUserResourceRequest;
import com.openplatform.weasel.sdk.response.PlatformAccessTokenResponse;
import com.openplatform.weasel.sdk.response.PlatformUserResourceResponse;
import com.weasel.openplatform.security.helper.ShiroSecurityHelper;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {
	
	private static Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000000).build();
	
	/**重定向到服务端的授权地址进行授权
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authorize",method = GET)
	public String authorize(HttpServletRequest request){
		
		
		//重定向到服务端的授权地址
		return "redirect:"+OpenPlatformOauthConfig.authorizeUrl+"?response_type=code&client_id=" + ClientOauthConfig.appkey
				+ "&redirect_uri=" + ClientOauthConfig.redirectUrl;
	}

	/**
	 * 服务端授权成功重，重定向回access-token，并带有授权码(auth_code)
	 * @param request
	 * @param model
	 * @return
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 */
	@RequestMapping(value = "/access-token",method = GET)
	public String token(HttpServletRequest request,Model model) throws OAuthSystemException, OAuthProblemException{
		
		String code = ServletRequestUtils.getStringParameter(request, "code","错误code");//获得授权码
		
		PlatformAccesssTokenRequest accessTokenRequest = new PlatformAccesssTokenRequest();
		accessTokenRequest.setAppkey(ClientOauthConfig.appkey) //设置appkey
				    .setSecret(ClientOauthConfig.secret)   //设置secret
				    .setCode(code)    //设置授权码
				    .setGrantType(GrantType.AUTHORIZATION_CODE)  //设置类型为authorization_code
				    .setRedirectUrl(ClientOauthConfig.redirectUrl);  //设置回调地址
		DefaultPlatformClient client = new DefaultPlatformClient();
		
		//用授权码到服务端换取access_token(令牌)
		PlatformAccessTokenResponse  accessTokenResponse = client.getAccessToken(accessTokenRequest);
		//将令牌保存起来，获取资源的时候使用
		cache.put(ShiroSecurityHelper.getSessionId(), accessTokenResponse.getAccess_token());
		model.addAttribute("accessToken", accessTokenResponse.getAccess_token());
		model.addAttribute("expiresIn", accessTokenResponse.getExpires_in());
		return "index";
	}
	
	/**
	 * 刷新令牌有效期
	 * @param request
	 * @param model
	 * @return
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 */
	@RequestMapping(value = "/refresh-access-token",method = GET)
	public String refreshToken(HttpServletRequest request,Model model) throws OAuthSystemException, OAuthProblemException{
		
		PlatformAccesssTokenRequest accessTokenRequest = new PlatformAccesssTokenRequest();
		accessTokenRequest.setAppkey(ClientOauthConfig.appkey) //设置appkey
				    .setSecret(ClientOauthConfig.secret)   //设置secret
				    .setRefreshAccessToken(cache.getIfPresent(ShiroSecurityHelper.getSessionId()))  //设置access_token
				    .setGrantType(GrantType.REFRESH_TOKEN); //设置类型为refresh_token
		DefaultPlatformClient client = new DefaultPlatformClient();
		
		//刷新令牌有效期
		PlatformAccessTokenResponse  accessTokenResponse = client.getRefreshAccessToken(accessTokenRequest);
		cache.put(ShiroSecurityHelper.getSessionId(), accessTokenResponse.getAccess_token());
		model.addAttribute("accessToken", accessTokenResponse.getAccess_token());
		model.addAttribute("expiresIn", accessTokenResponse.getExpires_in());
		return "index";
	}
	
	/**
	 * 获取用户资源
	 * @param request
	 * @return
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 */
	@RequestMapping(value = "/user-resource.json",method = GET)
	public @ResponseBody PlatformUserResourceResponse userResource(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException{
		
		DefaultPlatformClient client = new DefaultPlatformClient();
		PlatformUserResourceRequest userResourceRequest = new PlatformUserResourceRequest();
		userResourceRequest.setAppkey(ClientOauthConfig.appkey)//设置appkey
		 				   .setSecret(ClientOauthConfig.secret)//设置secret
						   .setPageNo(1)
						   .setPageSize(20)
						   .setRedirectUrl(ClientOauthConfig.redirectUrl); //设置回调地址
		PlatformUserResourceResponse userResourceResponse = client.getResources(userResourceRequest, cache.getIfPresent(ShiroSecurityHelper.getSessionId()), PlatformUserResourceResponse.class);
		return userResourceResponse;
	}
}
