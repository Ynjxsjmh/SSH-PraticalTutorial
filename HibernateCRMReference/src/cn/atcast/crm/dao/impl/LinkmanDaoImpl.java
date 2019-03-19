package cn.atcast.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import cn.atcast.crm.dao.LinkmanDao;
import cn.atcast.crm.domain.CstLinkman;
import cn.atcast.crm.util.HibernateUtil;

public class LinkmanDaoImpl implements LinkmanDao {
	
	
	// 更新联系人
	public void update(CstLinkman cstLinkman){
		Session session = HibernateUtil.getCurrentSession();
		session.update(cstLinkman);
	}
	
	//删除联系人
	public void delete(Long lkmId){
		Session session = HibernateUtil.getCurrentSession();
		CstLinkman cstLinkman = new CstLinkman();
		cstLinkman.setLkmId(lkmId);
		session.delete(cstLinkman);
	}
	
	//根据id查询联系人
	public CstLinkman findLinkmanById(Long lkmId){
		Session session = HibernateUtil.openSession();
		return session.get(CstLinkman.class, lkmId);
	}

//	//添加联系人
//	@Override
//	public void insert(CstLinkman cstLinkman) {
//		Session session = HibernateUtil.openSession();
//		try {
//			//开启事务
//			session.beginTransaction();
//			//保存
//			session.save(cstLinkman);
//			//提交事务
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			//回滚事务
//			session.getTransaction().rollback();
//		}finally{
//			session.close();
//		}
//		
//	}
	
	//添加联系人
	@Override
	public void insert(CstLinkman cstLinkman) {
		Session session = HibernateUtil.getCurrentSession();
		// 保存
		session.save(cstLinkman);
	}

	@Override
	public long findLinkmanCount(CstLinkman cstLinkman) {
		Session session = HibernateUtil.getCurrentSession();
		StringBuffer hql = new StringBuffer("select count(*) from CstLinkman c where 1=1 ");
		
		//拼接查询条件
		if(cstLinkman!=null){
			if(cstLinkman.getLkmName()!=null && !cstLinkman.getLkmName().equals("")){
				hql.append(" and c.lkmName = ?");
				
			}
		}
		Query query = session.createQuery(hql.toString());
		//参数绑定
		if(cstLinkman!=null){
			if(cstLinkman.getLkmName()!=null && !cstLinkman.getLkmName().equals("")){
				query.setString(0, cstLinkman.getLkmName());
				
			}
		}
		List list = query.list();
		Long total = (Long) list.get(0);
		return total;
	}

	@Override
	public List<CstLinkman> findLinkmanList(CstLinkman cstLinkman, int firstResult, int maxResults) {
		Session session = HibernateUtil.getCurrentSession();
		StringBuffer hql = new StringBuffer("from CstLinkman c where 1=1  ");
		//拼接查询条件
		if(cstLinkman!=null){
			if(cstLinkman.getLkmName()!=null && !cstLinkman.getLkmName().equals("")){
				hql.append(" and c.lkmName = ?");
				
			}
		}
		Query query = session.createQuery(hql.toString());
		//参数绑定
		if(cstLinkman!=null){
			if(cstLinkman.getLkmName()!=null && !cstLinkman.getLkmName().equals("")){
				query.setString(0, cstLinkman.getLkmName());
				
			}
		}
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List list = query.list();
		return list;
	}

}
