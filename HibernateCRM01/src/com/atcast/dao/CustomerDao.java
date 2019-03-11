package com.atcast.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
