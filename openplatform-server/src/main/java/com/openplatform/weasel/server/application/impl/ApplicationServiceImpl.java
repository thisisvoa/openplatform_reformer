package com.openplatform.weasel.server.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplatform.weasel.server.application.ApplicationService;
import com.openplatform.weasel.server.domain.Application;
import com.openplatform.weasel.server.infrastructure.ApplicationRepository;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository repository;
	
	public List<Application> query(String username) {
		return repository.query(username);
	}

	public Application save(Application application) {
		return repository.save(application);
	}

	public void delete(Long id, String username) {
		repository.delete(id,username);
	}

	@Override
	public boolean appkeyAvailable(String appkey) {
		Application application = repository.getByAppkey(appkey);
		return null != application;
	}

	@Override
	public boolean redirectUrlAvailable(String appkey, String redirectURI) {
		Application application = repository.getByAppkey(appkey);
		return null != application && application.getRedirectUrl().equals(redirectURI);
	}

	@Override
	public boolean secretAvailable(String secret) {
		Application application = repository.getBySecret(secret);
		return null != application;
	}

}
