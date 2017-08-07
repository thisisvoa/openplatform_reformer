package com.openplatform.weasel.server.infrastructure.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public abstract class MybatisDaoSupport extends SqlSessionDaoSupport{
	
	protected abstract String getNamespace();

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
