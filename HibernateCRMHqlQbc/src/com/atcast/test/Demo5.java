package com.atcast.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.domain.Customer;
import com.atcast.utils.HibernateUtils;

/**
 * HQL的多表的查询
 * @author Administrator
 */
public class Demo5 {
	
	/**
	 * 数据的重复的问题
	 */
	@Test
	public void run3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 内连接的查询
		Query query = session.createQuery("from Customer c inner join fetch c.linkmans");
		// 默认的返回值是数组
		List<Customer> list = query.list();
		// 手动解决，编程中都使用这种方式解决重复的问题
		Set<Customer> set = new HashSet<Customer>(list);
		for (Customer customer : set) {
			System.out.println(customer);
		}
		tr.commit();
	}
	
	/**
	 * 数据默认返回的数组，把数据封装到对象中
	 * 提供关键字：fetch 迫切连接，使用fetch关键字，把数据封装到对象中
	 */
	@Test
	public void run2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 内连接的查询
		Query query = session.createQuery("from Customer c inner join fetch c.linkmans");
		// 默认的返回值是数组
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tr.commit();
	}
	
	/**
	 * 查询的客户，客户和联系人有关联
	 * select * from cst_customer c,cst_linkman l where c.id = l.id;
	 */
	@Test
	public void run1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 内连接的查询
		Query query = session.createQuery("from Customer c inner join c.linkmans");
		// 默认的返回值是数组
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tr.commit();
	}

}
