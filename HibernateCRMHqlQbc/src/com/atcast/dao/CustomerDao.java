package com.atcast.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.atcast.domain.Customer;
import com.atcast.utils.HibernateUtils;

public class CustomerDao {

	/**
	 * 保存客户
	 * 
	 * @param c
	 */
	public void save(Customer c) {
		// 先获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction tr = session.beginTransaction();
		// 保存用户
		session.save(c);
		// 提交事务
		tr.commit();
		// 关闭资源
		session.close();
	}

	/**
	 * 查询所有的客户
	 * 
	 * @return
	 */
	public List<Customer> findAll() {
		// QBC查询
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 查询
		Criteria criteria = session.createCriteria(Customer.class);
		// 查询
		List<Customer> list = criteria.list();
		tr.commit();
		session.close();
		return list;
	}

	/**
	 * 带查询条件的查询所有的客户
	 * 
	 * @return
	 */
	public List<Customer> findAll(String custName) {
		// QBC查询
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 查询
		Criteria criteria = session.createCriteria(Customer.class);

		// 添加查询的条件
		if (custName != null && !custName.trim().isEmpty()) {
			// 添加查询的条件
			criteria.add(Restrictions.like("cust_name", "%" + custName + "%"));
		}

		// 查询
		List<Customer> list = criteria.list();
		tr.commit();
		session.close();
		return list;
	}

	/**
	 * 通过主键，查询客户
	 * 
	 * @param cust_id
	 * @return
	 */
	public Customer findById(Long cust_id) {
		// 使用session
		Session session = HibernateUtils.getCurrentSession();
		// 查询
		return session.get(Customer.class, cust_id);
	}
}
