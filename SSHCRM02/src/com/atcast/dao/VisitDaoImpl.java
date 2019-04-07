package com.atcast.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.atcast.domain.Visit;

/**
 * 客户拜访的持久层
 * 
 * @author Administrator
 */
@Repository(value = "visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

	@Resource(name = "sessionFactory")
	public void set2SessionFactory(SessionFactory sessionFactory) {
		// 关键，调用父类的方法
		super.setSessionFactory(sessionFactory);
	}

}
