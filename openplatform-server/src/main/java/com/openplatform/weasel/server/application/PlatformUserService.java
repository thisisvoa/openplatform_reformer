package com.openplatform.weasel.server.application;

import java.util.List;

import com.openplatform.weasel.server.domain.PlatformUser;
import com.weasel.openplatform.security.domain.SecurityService;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
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
