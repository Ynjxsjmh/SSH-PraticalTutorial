package com.atcast.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.domain.Customer;
import com.atcast.utils.HibernateUtils;

/**
 * 演示的延迟加载，提升程序的性能
 * @author Administrator
 */
public class Demo6 {

	/**
	 * fetch属性能解决的问题
	 */
	@Test
	public void run4(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = session.createQuery("from Customer").list();
		for (Customer customer : list) {
			System.out.println(customer.getLinkmans().size());
		}
		tr.commit();
	}

	/**
	 * 关联级别的延迟加载
	 * 说的是客户下的联系人的集合
	 */
	@Test
	public void run3(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 1L);
		System.out.println("=============================");
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}

	/**
	 * 类级别的延迟加载
	 * 需要使用session.load() 默认情况使用的延迟加载
	 */
	@Test
	public void run2(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// load方法，默认是延迟加载
		Customer c1 = session.load(Customer.class, 1L);
		// 把c1对象初始化
		// Hibernate.initialize(c1);
		System.out.println("=============================");
		System.out.println(c1.getCust_name());
		tr.commit();
	}

	/**
	 * 类级别的延迟加载
	 * 需要使用session.load() 默认情况使用的延迟加载
	 */
	@Test
	public void run1(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 使用get方法
		Customer c1 = session.get(Customer.class,1L);
		System.out.println("=============================");
		System.out.println(c1.getCust_name());
		tr.commit();
	}

}
