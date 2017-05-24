package com.openplatform.weasel.server.application;

import java.util.List;

import com.openplatform.weasel.server.domain.PlatformUser;
import com.weasel.openplatform.security.domain.SecurityService;


public interface PlatformUserService extends SecurityService<PlatformUser>{

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
