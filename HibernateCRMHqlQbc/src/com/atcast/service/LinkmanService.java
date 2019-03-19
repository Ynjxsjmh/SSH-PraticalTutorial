package com.atcast.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.atcast.dao.CustomerDao;
import com.atcast.dao.LinkmanDao;
import com.atcast.domain.Customer;
import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;

public class LinkmanService {

	/**
	 * 编写业务，保存联系人
	 * 先把客户查询出来，设置到联系人中，再保存联系人
	 * @param man
	 * @param cust_id
	 */
	public void save(Linkman man, Long cust_id) {
		// 先获取session
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction tr = session.beginTransaction();
		try {
			// 编写代码
			// 先查客户
			Customer c = new CustomerDao().findById(cust_id);
			// 设置
			man.setCustomer(c);
			// 保存联系人
			new LinkmanDao().save(man);
			
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
	}
	
	public List<Linkman> findAll() {
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction tr = session.beginTransaction();
		List<Linkman> mans = null;
		try {
			mans = new LinkmanDao().findAll();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return mans;
	}
	
	/**
	 * 根据条件查询所有的联系人
	 * @param criteria
	 * @return
	 */
	public List<Linkman> findAll(DetachedCriteria criteria) {
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction tr = session.beginTransaction();
		List<Linkman> mans = null;
		try {
			mans = new LinkmanDao().findAll(criteria);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return mans;
	}

}
