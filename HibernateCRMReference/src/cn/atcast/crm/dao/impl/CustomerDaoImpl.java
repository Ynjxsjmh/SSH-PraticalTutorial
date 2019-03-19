package cn.atcast.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import cn.atcast.crm.dao.CustomerDao;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void insert(CstCustomer customer) {
		Session session = HibernateUtil.openSession();
		try {
			//开启事务
			session.beginTransaction();
			//保存
			session.save(customer);
			//提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
	}
	
//	@Override
//	public void insert(CstCustomer cstCustomer) {
//		Session session = HibernateUtil.getCurrentSession();
//		// 保存
//		session.save(cstCustomer);
//	}

	@Override
	public void delete(Long custId) {
		Session session = HibernateUtil.getCurrentSession();
		CstCustomer cstCustomer = new CstCustomer();
		cstCustomer.setCustId(custId);
		session.delete(cstCustomer);
		
	}

	@Override
	public void update(CstCustomer cstCustomer) {
		Session session = HibernateUtil.getCurrentSession();
		session.update(cstCustomer);
		
	}

	@Override
	public CstCustomer findCustomerById(Long custId) {
		Session session = HibernateUtil.openSession();
		return session.get(CstCustomer.class, custId);
	}

	@Override
	public List<CstCustomer> findCustomerList(CstCustomer cstCustomer, int firstResult, int maxResults) {
		Session session = HibernateUtil.openSession();
		Criteria criteria = session.createCriteria(CstCustomer.class);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		List list = criteria.list();
		return list;
	}

	@Override
	public Long findCustomerCount(CstCustomer cstCustomer) {
		Session session = HibernateUtil.openSession();
		Criteria criteria = session.createCriteria(CstCustomer.class);
		criteria.setProjection(Projections.rowCount());
		//查询总数
		Long total = (Long) criteria.uniqueResult();
		return total;
	}
	public Long findCustomerCount(DetachedCriteria detachedCriteria){
		Session session = HibernateUtil.openSession();
		//离线criteria与session绑定生成可执行criteria
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setProjection(Projections.rowCount());
		//查询总数
		Long total = (Long) criteria.uniqueResult();
		return total;
	}
	public List<CstCustomer> findCustomerList(DetachedCriteria detachedCriteria, int firstResult, int maxResults){
		Session session = HibernateUtil.openSession();
		//离线criteria与session绑定生成可执行criteria
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		List list = criteria.list();
		return list;
	}


}
