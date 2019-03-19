package com.atcast.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.atcast.domain.User;
import com.atcast.utils.HibernateUtils;

/**
 * 持久层
 * 
 * @author Administrator
 */
public class UserDao {

	/**
	 * 通过用户名和密码查询数据库
	 * 
	 * @param user
	 * @return
	 */
	public User findByNameAndPwd(User user) {
		// 先获取
		Session session = HibernateUtils.getCurrentSession();
		// 使用用户名和密码进行查询
		Query query = session.createQuery("from User where username = ? and password = ?");
		// 设置参数
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());
		// 查询
		List<User> list = query.list();
		// 有数据
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
