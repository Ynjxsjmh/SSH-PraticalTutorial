package com.atcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.atcast.domain.User;

/**
 * 持久层：都可以继承HibernateDaoSupport类
 * 
 * @author Administrator
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 通过登录名进行验证
	 */
	public User checkCode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存用户
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 登录功能
	 * 通过用户名和密码和用户的状态( 1正常 0暂停)
	 */
	public User login(User user) {
		// QBC的查询，按条件进行查询
		/*
		 * Criteria 和 DetachedCriteria 的主要区别在于创建的形式不一样， Criteria 是在线的，所
			以它是由 Hibernate Session 进行创建的；而 DetachedCriteria 是离线的，创建时无需
			Session，DetachedCriteria 提供了 2 个静态方法 forClass(Class) 或 forEntityName(Name)
			进行DetachedCriteria 实例的创建。Spring 的框架提供了getHibernateTemplate 
			().findByCriteria(detachedCriteria) 方法可以很方便地根据DetachedCriteria 来返回查询结果。 
		 */
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		// 拼接查询的条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		// 查询
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}