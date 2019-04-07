package com.atcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.atcast.domain.Customer;
import com.atcast.domain.PageBean;

/**
 * 持久层
 * 
 * @author Administrator
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		// 把数据，保存到数据库中
		this.getHibernateTemplate().save(customer);
	}

	/**
	 * 分页的查询
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);

		// 先查询总记录数 select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			int totalCount = list.get(0).intValue();
			// 总的记录数
			page.setTotalCount(totalCount);
		}

		// 强调：把select count(*) 先清空，变成 select * ...
		// 这句的作用是将原来设置Projection(投影,投影图)的清空，否则只能查到满足条件的总记录数而criteria.list()将没有记录。
		criteria.setProjection(null);

		// 提供分页的查询
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,
				(pageCode - 1) * pageSize, pageSize);
		// 分页查询数据，每页显示的数据 使用limit
		page.setBeanList(beanList);

		return page;
	}

	/**
	 * 通过主键，查询客户
	 */
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}

	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	/**
	 * 更新客户
	 */
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

}
