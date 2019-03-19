package cn.atcast.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import cn.atcast.crm.dao.SaleVisitDao;
import cn.atcast.crm.domain.SaleVisit;
import cn.atcast.crm.util.HibernateUtil;

public class SaleVisitDaoImpl implements SaleVisitDao {

	@Override
	public void insertSaleVisit(SaleVisit saleVisit) {
		Session session = HibernateUtil.getCurrentSession();
		session.save(saleVisit);
		
	}

	@Override
	public long findSaleVisitCount(DetachedCriteria detachedCriteria) {
		Session session = HibernateUtil.openSession();
		//离线criteria与session绑定生成可执行criteria
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setProjection(Projections.rowCount());
		//查询总数
		Long total = (Long) criteria.uniqueResult();
		//关闭session
		session.close();
		return total;
	}

	@Override
	public List<SaleVisit> findSaleVisitList(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
		Session session = HibernateUtil.openSession();
		//离线criteria与session绑定生成可执行criteria
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		List<SaleVisit> list = criteria.list();
//		for(SaleVisit saleVisit:list){
//			//通过对象导航加载属性
//			saleVisit.getSysUser().getUserName();
//		}
		//关闭session
		session.close();
		return list;
	}
	
	

}
