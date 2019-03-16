package com.atcast.dao;

import org.hibernate.Session;

import com.atcast.domain.Linkman;
import com.atcast.utils.HibernateUtils;


public class LinkmanDao {

	/**
	 * 保存联系人
	 * @param man
	 */
	public void save(Linkman man){
		Session session = HibernateUtils.getSession();
		session.save(man);
		/*
		// 先获取session
				Session session = HibernateUtils.getCurrentSession();
				// 开启事务
				Transaction tr = session.beginTransaction();
				try {
					// 编写代码
					// 先查客户
					Customer c = new CustomerDao().findById(cust_id);
					// 设置
					man.setCustomer(c);
					// 保存联系人
					new LinkmanDao().save(man);
					
					tr.commit();
				} catch (Exception e) {
					tr.rollback();
					e.printStackTrace();
				}
			}
			*/
	}
}
