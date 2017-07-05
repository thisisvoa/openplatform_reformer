package com.openplatform.weasel.sdk;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.openplatform.weasel.sdk.helper.JsonHelper;
import com.openplatform.weasel.sdk.request.PlatformAccesssTokenRequest;
import com.openplatform.weasel.sdk.request.PlatformRequest;
import com.openplatform.weasel.sdk.response.PlatformAccessTokenResponse;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月10日
 */
public class DefaultPlatformClient {
	
	private OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
	
	public <T> T getResources(PlatformRequest request,String accessToken,Class<T> clazz) throws OAuthSystemException, OAuthProblemException{
		OAuthClientRequest resourceRequest = new OAuthBearerClientRequest(request.getUrl()).setAccessToken(accessToken)
				                                                                           .buildQueryMessage();
		OAuthResourceResponse resourceResponse = oAuthClient.resource(resourceRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
		return JsonHelper.fromJsonString(resourceResponse.getBody(), clazz);
	}
	
	/**
	 * @param code
	 * @return
	 * @throws OAuthSystemException
	 * @throws OAuthProblemException
	 */
	public PlatformAccessTokenResponse getAccessToken(PlatformAccesssTokenRequest request) throws OAuthSystemException, OAuthProblemException{
		
        OAuthClientRequest accessTokenRequest = OAuthClientRequest
                .tokenLocation(OpenPlatformOauthConfig.tokenUrl)
                .setGrantType(request.getGrantType())
                .setClientId(request.getAppkey())
                .setClientSecret(request.getSecret())
                .setCode(request.getCode())
                .setRedirectURI(request.getRedirectUrl())
                .buildQueryMessage();

        OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
        return new PlatformAccessTokenResponse().setAccess_token(oAuthResponse.getAccessToken())
        										.setExpires_in(oAuthResponse.getExpiresIn());
	}
	
	public PlatformAccessTokenResponse getRefreshAccessToken(PlatformAccesssTokenRequest request) throws OAuthSystemException, OAuthProblemException{
		
        OAuthClientRequest accessTokenRequest = OAuthClientRequest
                .tokenLocation(OpenPlatformOauthConfig.tokenUrl)
                .setGrantType(request.getGrantType())
                .setClientId(request.getAppkey())
                .setClientSecret(request.getSecret())
                .setRefreshToken(request.getRefreshAccessToken())
                .buildQueryMessage();

        OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
        return new PlatformAccessTokenResponse().setAccess_token(oAuthResponse.getAccessToken())
        										.setExpires_in(oAuthResponse.getExpiresIn());
	}

}
