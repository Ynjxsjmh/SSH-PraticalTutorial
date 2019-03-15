package cn.atcast.dao;

import org.hibernate.SessionFactory;

import cn.atcast.entity.Dept;

// 数据访问层（将sessionFactory的创建给spring管理）
public class DeptDao {

	// Spring与Hibernate整合： IOC容器注入
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 保存一个记录
	// Spring与Hibernate整合：事务管理交给Spring
	// 要使用这个类来管理事务org.springframework.orm.hibernate3.HibernateTransactionManager
	public void save(Dept dept) {
		sessionFactory.getCurrentSession().save(dept);
	}
}
