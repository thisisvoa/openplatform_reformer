package com.weasel.openplatform.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weasel.openplatform.security.domain.BaseUser;
import com.weasel.openplatform.security.domain.SecurityService;
import com.weasel.openplatform.security.exception.UnAllowLoginException;
import com.weasel.openplatform.security.helper.ShiroSecurityHelper;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2014年11月24日
 */
@SuppressWarnings("rawtypes")
public class SecurityServer {

	private final static Logger LOG = LoggerFactory.getLogger(SecurityServer.class);

	private SecurityService securityService;
	
	public SecurityServer(SecurityService _securityService){
		this.securityService = _securityService;
	}
	
	/**
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	public void login(BaseUser user, HttpServletRequest request, HttpServletResponse response) {
	    
		String _rememberMe = request.getParameter("rememberMe");
		boolean rememberMe = StringUtils.hasText(_rememberMe) ? new Boolean(_rememberMe) : false;
		baseLogin(user,rememberMe, request, response);
	}

	/**
	 * 自动登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, Object> autoLogin(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> responseMsg = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isRemembered()){
			String username = ShiroSecurityHelper.getCurrentUsername();
			BaseUser user = securityService.getUserByNameOrEmialOrPhone(username);
			baseLogin(user,true, request, response);
			responseMsg.put("username", username);
		}
		return responseMsg;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			String username = ShiroSecurityHelper.getCurrentUsername();
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			if (LOG.isDebugEnabled()) {
				LOG.debug("用户" + username + "退出登录");
			}
		}
	}
	
	/**
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	public void baseLogin(BaseUser user, boolean rememberMe,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			//如果用户已登录，先踢出
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), rememberMe);
				currentUser.login(token); // 登录
			}

			request.setAttribute("username", user.getUsername());
		} catch (Exception e) {
			throw new UnAllowLoginException(e);
		}
	}

}
