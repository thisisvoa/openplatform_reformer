package com.openplatform.weasel.server.infrastructure;

import java.util.List;

import com.openplatform.weasel.server.domain.Application;

public interface ApplicationRepository {

	/**
	 * @param username
	 * @return
	 */
	List<Application> query(String username);

	/**
	 * @param application
	 * @return
	 */
	Application save(Application application);

	/**
	 * @param id
	 * @param username
	 */
	void delete(Long id, String username);

	/**
	 * @param appkey
	 * @return
	 */
	Application getByAppkey(String appkey);

	/**
	 * @param secret
	 * @return
	 */
	Application getBySecret(String secret);


}
