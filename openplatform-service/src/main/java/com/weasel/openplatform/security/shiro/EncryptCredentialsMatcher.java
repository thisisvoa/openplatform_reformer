package com.weasel.openplatform.security.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import com.weasel.openplatform.security.helper.EncryptHelper;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class EncryptCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		char[] notEncodePassword = (char [])getCredentials(token);
		Object encodePassword = getCredentials(info);
		ByteSource salt = getSalt(info);
		String _encodePassword = EncryptHelper.encryptPBKDF2(new String(notEncodePassword), salt.getBytes());
		return equals(encodePassword, _encodePassword);
	}
	
	private ByteSource getSalt(AuthenticationInfo info){
		ByteSource salt = null;
		if(info instanceof SaltedAuthenticationInfo){
			salt = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
			return salt;
		}
		throw new RuntimeException("AuthenticationInfo is not instanceof SaltedAuthenticationInfo");
	}

}
