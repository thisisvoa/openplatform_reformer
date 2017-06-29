package com.openplatform.weasel.server.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.openplatform.weasel.server.application.PlatformUserService;
import com.openplatform.weasel.server.domain.PlatformUser;
import com.openplatform.weasel.server.infrastructure.PlatformUserRepository;

public class PlatformUserServiceImpl implements PlatformUserService {

	@Autowired
	private PlatformUserRepository repository;
	
	public PlatformUser save(PlatformUser user) {
		return repository.save(user);
	}
	
	public PlatformUser getUserByNameOrEmialOrPhone(String nameOrEmailOrPhone) {
		
		return repository.getUserByNameOrEmialOrPhone(nameOrEmailOrPhone);
	}

	@Override
	public List<PlatformUser> queryPage(String username, int pageSize, int pageNo) {
		return repository.queryPage(username, pageSize, pageNo);
	}




}
