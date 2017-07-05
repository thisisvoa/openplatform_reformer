package com.weasel.openplatform.security.helper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.weasel.openplatform.security.domain.BaseUser;

/**
 * @author Dylan
 * @time 2013-8-12
 */
public class ShiroSecurityHelper {
	
	/**
	 * 从cache拿当前user
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends BaseUser> T getCurrentUser() {
		if (!hasAuthenticated()) {
			return null;
		}
		Subject subject = getSubject();
		PrincipalCollection collection = subject.getPrincipals();
		if (null != collection && !collection.isEmpty()) {
			Object principal = collection.iterator().next();
			if(principal instanceof BaseUser){
				return (T)principal;
			}
		}
		return null;
	}

	/**
	 * 获得当前用户名
	 * 
	 * @return
	 */
	public static String getCurrentUsername() {
		BaseUser user = getCurrentUser();
		return null != user ? user.getUsername() : "";
	}

	/**
	 * 
	 * @return
	 */
	public static Session getSession() {
		return getSubject().getSession();
	}

	/**
	 * 
	 * @return
	 */
	public static String getSessionId() {
		Session session = getSession();
		if (null == session) {
			return null;
		}
		return getSession().getId().toString();
	}
	
	/**
	 * 判断当前用户是否已通过认证
	 * @return
	 */
	public static boolean hasAuthenticated() {
		return getSubject().isAuthenticated();
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}


}
