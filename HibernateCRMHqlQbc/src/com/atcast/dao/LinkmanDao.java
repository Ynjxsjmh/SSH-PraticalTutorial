package com.atcast.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;

public class LinkmanDao {

	/**
	 * 保存联系人
	 * @param man
	 */
	public void save(Linkman man){
		Session session = HibernateUtils.getCurrentSession();
		session.save(man);
	}

	public List<Linkman> findAll() {
		Session session = HibernateUtils.getCurrentSession();
		return session.createQuery("from Linkman").list();
	}

	/**
	 * 根据条件查询所有的联系人
	 * @param criteria
	 * @return
	 */
	public List<Linkman> findAll(DetachedCriteria criteria) {
		// 先获取到session
		Session session = HibernateUtils.getCurrentSession();
		// 执行
		return criteria.getExecutableCriteria(session).list();
	}

}
