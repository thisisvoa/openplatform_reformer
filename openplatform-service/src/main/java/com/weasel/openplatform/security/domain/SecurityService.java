package com.weasel.openplatform.security.domain;



/**
 * @author Dylan
 * @time 2013-8-5
 */
public interface SecurityService<T extends BaseUser> {

	/**
	 * @param nameOrEmailOrPhone
	 * @return
	 */
	T getUserByNameOrEmialOrPhone(String nameOrEmailOrPhone);
	
}
