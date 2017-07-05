package com.openplatform.weasel.server.infrastructure.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.openplatform.weasel.server.domain.PlatformUser;
import com.openplatform.weasel.server.infrastructure.PlatformUserRepository;
import com.openplatform.weasel.server.infrastructure.mybatis.MybatisDaoSupport;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
@Repository
public class PlatformUserRepositoryImpl extends MybatisDaoSupport implements PlatformUserRepository {

	
	public PlatformUser getUserByNameOrEmialOrPhone(String nameOrEmailOrPhone) {
		return getSqlSession().selectOne(getNamespace().concat(".getUserByNameOrEmialOrPhone"), nameOrEmailOrPhone);
	}
	

	public PlatformUser save(PlatformUser user) {
		getSqlSession().insert(getNamespace().concat(".save"), user);
		return user;
	}
	
	@Override
	public List<PlatformUser> queryPage(String username, int pageSize, int pageNo) {

		Map<String,Object> params = new HashMap<>();
		params.put("username", username);
		params.put("pageSize", pageSize);
		params.put("pageNo", pageNo);
		return getSqlSession().selectList(getNamespace().concat(".queryPage"),params);
	}
	@Override
	public String getNamespace() {
		return PlatformUser.class.getName();
	}
	
}
