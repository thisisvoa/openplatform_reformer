package com.weasel.openplatform.security.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weasel.openplatform.security.domain.BaseUser;
import com.weasel.openplatform.security.domain.SecurityService;

/**
 * @author Dylan
 * @time 2013-8-2
 */
@SuppressWarnings("rawtypes")
public class ShiroRealm extends AuthorizingRealm{

	private final static Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);
	
	public final static String REALM_NAME = "ShiroCasRealm";

	private SecurityService securityService;
	
	public ShiroRealm(SecurityService _securityService) {
		this.securityService = _securityService;
		setName(REALM_NAME); // This name must match the name in the User
								// class's getPrincipals() method
		setCredentialsMatcher(new EncryptCredentialsMatcher());
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		if(LOG.isTraceEnabled()){
			LOG.trace("开始认证 "+ username);
		}
		try {
			if(!StringUtils.hasText(username)){
				throw new AccountException("can not handle this login");
			}
			BaseUser user = securityService.getUserByNameOrEmialOrPhone(username);
			checkUser(user, username);
			return new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
		} catch (Exception e) {
			throw translateAuthenticationException(e);
		}
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return new SimpleAuthorizationInfo();
	}

	/**
	 * 异常转换
	 * @param e
	 * @return
	 */
	private AuthenticationException translateAuthenticationException(Exception e) {
		if (e instanceof AuthenticationException) {
			return (AuthenticationException) e;
		}
		if(e instanceof DisabledAccountException){
			return (DisabledAccountException)e;
		}
		if(e instanceof UnknownAccountException){
			return (UnknownAccountException)e;
		}
		return new AuthenticationException(e);
	}
	/**
	 * 检查用户
	 * @param user
	 * @param username
	 */
	private void checkUser(BaseUser user,String username){
		if(null == user){
			throw new UnknownAccountException(username + " can not find "+username+" from system");
		}
	}
	
}
