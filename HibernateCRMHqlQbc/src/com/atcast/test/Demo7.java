package com.atcast.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.domain.Customer;
import com.atcast.utils.HibernateUtils;

/**
 * 查询的策略
 * @author Administrator
 */
public class Demo7 {
	
	/**
	 * fetch:subselect  采用子查询的方式
	 * lazy:false  不延迟加载
	 */
	@SuppressWarnings("all")
	@Test
	public void run6(){
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
	 * fetch:subselect  采用子查询的方式
	 * lazy:true  默认延迟加载
	 */
	@SuppressWarnings("all")
	@Test
	public void run5(){
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
	 * fetch是：join	采用的迫切左连接进行的查询
	 * lazy：什么是值，都是一样的效果
	 */
	@Test
	public void run4(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 1L);
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}
	
	/**
	 * fetch是：select 默认的SQL语句格式
	 * lazy:extra 及其的 延迟加载
	 */
	@Test
	public void run3(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 1L);
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}
	
	/**
	 * fetch是：select 默认的SQL语句格式
	 * lazy:false 不延迟加载
	 */
	@Test
	public void run2(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 1L);
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}
	
	/**
	 * 默认值：fetch是：select lazy:true
	 */
	@Test
	public void run1(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 1L);
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}
}
