package com.openplatform.weasel.server.infrastructure.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.openplatform.weasel.server.domain.Application;
import com.openplatform.weasel.server.infrastructure.ApplicationRepository;
import com.openplatform.weasel.server.infrastructure.mybatis.MybatisDaoSupport;

@Repository
public class ApplicationRepositoryImpl extends MybatisDaoSupport implements ApplicationRepository {
	
	
	public List<Application> query(String username) {
		
		return getSqlSession().selectList(getNamespace().concat(".query"), username);
	}
	
	public void delete(Long id, String username) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("id", id);
		parameters.put("username", username);
		getSqlSession().delete(getNamespace().concat(".delete"), parameters);
	}

	public Application save(Application application) {
		getSqlSession().insert(getNamespace().concat(".save"), application);
		return application;
	}
	
	@Override
	public Application getByAppkey(String appkey) {
		return getSqlSession().selectOne(getNamespace().concat(".getByAppkey"), appkey);
	}
	@Override
	public Application getBySecret(String secret) {
		return getSqlSession().selectOne(getNamespace().concat(".getBySecret"), secret);
	}

	@Override
	protected String getNamespace() {
		return Application.class.getName();
	}



}
