package com.openplatform.weasel.server.interfaces;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplatform.weasel.sdk.helper.JsonHelper;
import com.openplatform.weasel.sdk.response.PlatformAccessTokenResponse;
import com.openplatform.weasel.server.application.ApplicationService;
import com.openplatform.weasel.server.application.OauthValidateService;
import com.openplatform.weasel.server.infrastructure.cache.OauthCache;
import com.weasel.openplatform.security.helper.ShiroSecurityHelper;


@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private OauthValidateService oauthValidateService;

	/**
	 * 获取授权码
	 * @param request
	 * @param model
	 * @return
	 * @throws org.apache.oltu.oauth2.common.exception.OAuthSystemException
	 */
	@RequestMapping(value = "/authorize",method = GET)
	public String auth(HttpServletRequest request,Model model) throws OAuthSystemException{
		
		String appkey = "";
		String redirectUri = "";
		String responseType = "";
		try {
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			appkey = oauthRequest.getClientId();   //获得应用的appkey
			redirectUri = oauthRequest.getRedirectURI();  //获取客户端的回调函数
			responseType = oauthRequest.getResponseType();  //获得客户端的返回类型
			oauthValidateService.validateAppkey(appkey);  //验证appkey是否有效
			oauthValidateService.validateRedirectUrl(appkey, redirectUri);  //验证回调函数是否有效
			oauthValidateService.validateAuthenticated();   //验证用户是否已登录授权，如果还没授权，抛出异常，跳转到登录页面
			
			OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request,HttpServletResponse.SC_FOUND);
			OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
			if(ResponseType.CODE.toString().equals(responseType)){  //如果返回类型是授权码类型
				String authCode = oauthIssuerImpl.authorizationCode();  
				builder.setCode(authCode);   //发布一个授权码
				//缓存授权码
				oauthValidateService.cacheAuthCode(authCode,ShiroSecurityHelper.getCurrentUsername());
			}
			/*if(ResponseType.TOKEN.toString().equals(responseType)){   //直接返回acccess_token,不需要通过authCode换取acccess_token
				String accessToken = oauthIssuerImpl.accessToken();
				builder.setAccessToken(accessToken);
                builder.setExpiresIn(String.valueOf(OauthCache.expiresIn));
                oauthValidateService.cacheAccessToken(accessToken);
			}*/
			
			String redirectURI = oauthRequest.getRedirectURI();
			final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
			return "redirect:"+response.getLocationUri();   //重写向回客户端的回调地址，并带有授权码做为参数
		} catch (OAuthProblemException e) {
			final OAuthResponse resp = OAuthASResponse
		             .errorResponse(HttpServletResponse.SC_FOUND)
		             .error(e)
		             .location(e.getRedirectUri())
		             .buildQueryMessage();
			model.addAttribute("appkey", appkey);
			model.addAttribute("redirectUri", redirectUri);
			model.addAttribute("responseType", responseType);
			model.addAttribute("message", e.get("message"));
			return e.getRedirectUri();
		} 
	}
	
	/**
	 * 通过授权码换取令牌
	 * @param request
	 * @return
	 * @throws org.apache.oltu.oauth2.common.exception.OAuthSystemException
	 */
	@RequestMapping(value = "/access-token",method = POST)
	public @ResponseBody PlatformAccessTokenResponse accessToken(HttpServletRequest request) throws OAuthSystemException{
		
		try {
			OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
			oauthValidateService.validateAppkey(oauthRequest.getClientId());  //验证appkey是否有效
			oauthValidateService.validateSecret(oauthRequest.getClientSecret());//验证secret是否有效
			
			String authCode = oauthRequest.getCode();  //获取授权码
			if (GrantType.AUTHORIZATION_CODE.toString().equals(oauthRequest.getGrantType())) { //如果类型为authorization_code
				oauthValidateService.validateAuthCode(authCode);  //验证授权码是否有效
			}
			
            if(GrantType.REFRESH_TOKEN.toString().equals(oauthRequest.getGrantType())){ //如果类型为refresh_token
            	String refreshToken = oauthRequest.getRefreshToken();  //获得token(token为客户端传过来，即需要刷新有效期的token)
            	//重新缓存token
            	oauthValidateService.cacheAccessToken(refreshToken,oauthValidateService.getUsernameByAccessToken(refreshToken));
            	OAuthResponse response = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken(refreshToken)
                        .setExpiresIn(String.valueOf(OauthCache.expiresIn))  //重新设置缓存时间
                        .buildJSONMessage();
            	//如果是refresh_token类型，刷新缓存时间后，直接返回
            	return JsonHelper.fromJsonString(response.getBody(), PlatformAccessTokenResponse.class);
			}
            
            OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            String accessToken = oauthIssuerImpl.accessToken();  //发布accessToken
            //缓存accessToken
            oauthValidateService.cacheAccessToken(accessToken,oauthValidateService.getUsernameByAuthCode(authCode));
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken)
                    .setExpiresIn(String.valueOf(OauthCache.expiresIn))  //设置缓存时间
                    .buildJSONMessage();
            
            return JsonHelper.fromJsonString(response.getBody(), PlatformAccessTokenResponse.class);
		} catch (OAuthProblemException e) {
			final OAuthResponse response = OAuthASResponse
		             .errorResponse(HttpServletResponse.SC_FOUND)
		             .error(e)
		             .location(e.getRedirectUri())
		             .buildQueryMessage();
			return JsonHelper.fromJsonString(response.getBody(), PlatformAccessTokenResponse.class);
		}
	}
	
	@RequestMapping(value = "/auth-error",method = GET)
	public String authError(@RequestParam("message") String message,Model model){
		
		model.addAttribute("message", message);
		return "/open/auth_error";
	}
}
