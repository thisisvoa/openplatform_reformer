package com.openplatform.weasel.server.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplatform.weasel.sdk.resources.PlatformUserResource;
import com.openplatform.weasel.sdk.response.PlatformUserResourceResponse;
import com.openplatform.weasel.server.application.OauthValidateService;
import com.openplatform.weasel.server.application.PlatformUserService;
import com.openplatform.weasel.server.domain.PlatformUser;


@Controller
@RequestMapping(value = "/resource")
public class PlatformUserResourceController {

	@Autowired
	private PlatformUserService service;
	
	@Autowired
	private OauthValidateService oauthValidateService;

	@RequestMapping(value = "/platform-user")
	public @ResponseBody PlatformUserResourceResponse getPlatformUserResource(HttpServletRequest request,HttpServletResponse response) throws OAuthSystemException {

		// Get the access token
		PlatformUserResourceResponse resourceResponse = new PlatformUserResourceResponse();
		try {
			OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
			String accessToken = oauthRequest.getAccessToken();
			oauthValidateService.validateAccessToken(accessToken);
			
			String username = oauthValidateService.getUsernameByAccessToken(accessToken);
			int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 20);
			int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 0);
			List<PlatformUser> platformUsers = service.queryPage(username, pageSize, pageNo);
			resourceResponse.setPlatformUserResources(convert(platformUsers));
			return resourceResponse;
		} catch (OAuthProblemException e) {
			OAuthResponse oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
														 .setRealm("oauth2")
														 .buildHeaderMessage();
			response.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
			return resourceResponse.setErrorCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED))
					               .setBody(oauthResponse.getBody());
		}


	}

	private List<PlatformUserResource> convert(List<PlatformUser> platformUsers) {
		List<PlatformUserResource> platformUserResources = new ArrayList<PlatformUserResource>();
		for(PlatformUserResource platformUser:platformUserResources){
			PlatformUserResource resource = new PlatformUserResource();
			resource.setId(platformUser.getId());
			resource.setEmail(platformUser.getEmail());
			resource.setMobilePhone(platformUser.getMobilePhone());
			resource.setUsername(platformUser.getUsername());
			platformUserResources.add(resource);
		}

		return platformUserResources;
	}
}
