package com.openplatform.weasel.server.infrastructure;

import java.util.List;

import com.openplatform.weasel.server.domain.PlatformUser;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public interface PlatformUserRepository {

	/**
	 * @param nameOrEmailOrPhone
	 * @return
	 */
	PlatformUser getUserByNameOrEmialOrPhone(String nameOrEmailOrPhone);

	/**
	 * @param user
	 * @return
	 */
	PlatformUser save(PlatformUser user);

	/**
	 * @param username
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	List<PlatformUser> queryPage(String username, int pageSize, int pageNo);

}
