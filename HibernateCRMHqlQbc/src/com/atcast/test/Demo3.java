package com.atcast.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.atcast.domain.Customer;
import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;

/**
 * QBC的查询
 * @author Administrator
 */
public class Demo3 {
	
	/**
	 * 演示离线条件对象
	 */
	@Test
	public void run10(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建离线条件查询的对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		// 添加查询的条件了
		criteria.add(Restrictions.eq("lkm_gender", "女"));
		
		// 查询了
		List<Linkman> list = criteria.getExecutableCriteria(session).list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		
		tr.commit();
	}
	
	/**
	 * 强调问题： select count(*) from 表，又想查select * from 表单，存在问题
	 */
	@Test
	public void run9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		// 设置聚合函数的方式  select count(lkm_id) from 表;  5
		criteria.setProjection(Projections.count("lkm_id"));
		List<Number> list = criteria.list();
		Long count = list.get(0).longValue();
		System.out.println(count);
		
		criteria.setProjection(null);
		
		// 继续查询所有的联系人  select * from 表
		List<Linkman> mans = criteria.list();
		for (Linkman linkman : mans) {
			System.out.println(linkman);
		}
		
		tr.commit();
	}
	
	/**
	 * 聚合函数的查询
	 */
	@Test
	public void run8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		// 设置聚合函数的方式
		List<Number> list = criteria.setProjection(Projections.count("lkm_id")).list();
		Long count = list.get(0).longValue();
		System.out.println(count);
		tr.commit();
	}
	
	/**
	 * 判断值是否为空
	 */
	@Test
	public void run7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		
		// 找所有的lkm_email是空的值
		criteria.add(Restrictions.isNull("lkm_email"));
		
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * 演示QBC的or方法
	 */
	@Test
	public void run6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		
		// SQL：select * from cst_linkman where lkm_gender = '女' or lkm_id > 3L;
		criteria.add(Restrictions.or(Restrictions.eq("lkm_gender", "女"), Restrictions.gt("lkm_id", 3L)));
		
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * in查询
	 */
	@Test
	public void run5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		
		// SQL：select * from cst_linkman where lkm_id in (1,2,7);
		List<Long> params = new ArrayList<Long>();
		params.add(1L);
		params.add(2L);
		params.add(7L);
		
		// 使用in 方法查询
		criteria.add(Restrictions.in("lkm_id", params));
		
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * QBC的条件查询
	 */
	@Test
	public void run4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		
		// 使用方法添加条件  and
		// criteria.add(Restrictions.eq("lkm_gender", "男"));
		// criteria.add(Restrictions.ge("lkm_id", 3L));
		// criteria.add(Restrictions.between("lkm_id", 2L, 5L));
		
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * QBC分页的方法和HQL分页的方式一样的
	 */
	@Test
	public void run3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		// 调用排序的方法，addOrder()
		criteria.addOrder(Order.desc("lkm_id"));
		
		// 设置分页的方法
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * QBC的基本入门查询
	 * 排序查询，调用的方法
	 */
	@Test
	public void run2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		// 调用排序的方法，addOrder()
		criteria.addOrder(Order.desc("lkm_id"));
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * QBC的基本入门查询
	 */
	@Test
	public void run1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tr.commit();
	}

}
