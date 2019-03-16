package com.atcast.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.atcast.domain.Customer;
import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;

/**
 * 测试一对多
 * 
 * @author Administrator
 */
public class Demo1 {

	/**
	 * cascade和inverse的区别
	 */
	@Test
	public void run1() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();

		// 级联保存
		Customer c1 = new Customer();
		c1.setCust_name("美美");
		Customer c2 = new Customer();
		c2.setCust_name("小风");
		// 创建2个联系人
		Linkman l1 = new Linkman();
		l1.setLkm_name("熊大");
		Linkman l2 = new Linkman();
		l2.setLkm_name("熊二");
		l1.setCustomer(c1);
		l2.setCustomer(c1);
		session.save(c1);
		session.save(c2);
		session.save(l1);
		session.save(l2);

		// 不用修改
		tr.commit();
	}

	/**
	 * 放弃外键的维护 需求：让熊大联系人属于小风客户 linkman.hbm.xml中设置 lazy="false"
	 */
	@Test
	public void run2() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 先获取到客户小风
		Customer c2 = session.get(Customer.class, 36L);
		// 获取到熊大
		Linkman l1 = session.get(Linkman.class, 32L);
		// 做双向的关联
		c2.getLinkmans().add(l1);
		l1.setCustomer(c2);
		// 不用修改
		tr.commit();
	}

	/**
	 * 解除关系：从集合中删除联系人
	 */
	@Test
	public void run3() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 先获取到客户
		Customer c1 = session.get(Customer.class, 36L);
		Linkman l1 = session.get(Linkman.class, 32L);
		// 解除
		c1.getLinkmans().remove(l1);
		tr.commit();
	}

	/**
	 * 删除联系人，级联删除客户
	 */
	@Test
	public void run4() {
		Session session = HibernateUtils.getSession();
		// Session session=HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		Linkman man = session.get(Linkman.class, 32L);
		session.delete(man);
		tr.commit();
	}

	/**
	 * 测试级联删除，删除客户，级联删除客户下的联系人
	 */
	@Test
	public void run5() {
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		Customer c1 = session.get(Customer.class, 36L);
		session.delete(c1);
		tr.commit();
	}

}
