package com.atcast.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;

/**
 * 在many-to-one标签上 查询策略的优化
 * @author Administrator
 */
public class Demo8 {
	
	/**
	 * 默认值
	 * fetch:join
	 * lazy:值都一样的效果
	 */
	@Test
	public void run4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman l = session.get(Linkman.class,1L);
		// 看联系人的客户的名称
		System.out.println(l.getCustomer().getCust_name());
		tr.commit();
	}

	/**
	 * 默认值
	 * fetch:select
	 * lazy:proxy
	 */
	@Test
	public void run3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman l = session.get(Linkman.class,1L);
		// 看联系人的客户的名称
		System.out.println(l.getCustomer().getCust_name());
		tr.commit();
	}

	/**
	 * 默认值
	 * fetch:select
	 * lazy:false
	 */
	@Test
	public void run2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman l = session.get(Linkman.class,1L);
		// 看联系人的客户的名称
		System.out.println(l.getCustomer().getCust_name());
		tr.commit();
	}

	/**
	 * 默认值
	 * fetch:select
	 * lazy:是延迟加载
	 */
	@Test
	public void run1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman l = session.get(Linkman.class,1L);
		// 看联系人的客户的名称
		System.out.println(l.getCustomer().getCust_name());
		tr.commit();
	}

}
