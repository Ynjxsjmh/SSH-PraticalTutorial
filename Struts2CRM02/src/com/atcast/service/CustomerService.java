package com.atcast.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.dao.CustomerDao;
import com.atcast.domain.Customer;
import com.atcast.utils.HibernateUtils;

/**
 * 客户的业务层
 * 
 * @author Administrator
 */
public class CustomerService {

	/**
	 * 保存客户
	 * 
	 * @param customer
	 */
	public void saveCustomer(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 调用业务层
		new CustomerDao().save(customer);
		tr.commit();
	}

	/**
	 * 查询所有的客户
	 */
	public List<Customer> findAll() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 调用业务层
		List<Customer> list = new CustomerDao().findAll();
		tr.commit();
		return list;
	}

	@Test
	public void run() {
		Customer customer = new Customer();
		customer.setCust_name("测试");
		this.saveCustomer(customer);
	}

}
