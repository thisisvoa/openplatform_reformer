package com.openplatform.weasel.server.application;

import java.util.List;

import com.openplatform.weasel.server.domain.Application;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public interface ApplicationService {

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
	 * 验证appkey是否有效
	 * @param appkey
	 * @return
	 */
	boolean appkeyAvailable(String appkey);

	/**
	 * @param clientId
	 * @param redirectURI
	 * @return
	 */
	boolean redirectUrlAvailable(String clientId, String redirectURI);

	/**
	 * @param secret
	 * @return
	 */
	boolean secretAvailable(String secret);

}
