package cn.atcast.crm.service.impl;

import java.util.List;

import org.hibernate.Session;

import cn.atcast.crm.dao.LinkmanDao;
import cn.atcast.crm.dao.impl.LinkmanDaoImpl;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.CstLinkman;
import cn.atcast.crm.service.LinkmanService;
import cn.atcast.crm.util.HibernateUtil;

public class LinkmanServiceImpl implements LinkmanService {

	
	//添加联系人 
	@Override
	public void insertLinkman(Long custId,CstLinkman cstLinkman) {
	  //获取与线程绑定的session
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			//根据客户id查询客户信息
			CstCustomer cstCustomer = session.get(CstCustomer.class, custId);
			cstLinkman.setCstCustomer(cstCustomer);
			
			 LinkmanDao linkmanDao = new LinkmanDaoImpl();
			 linkmanDao.insert(cstLinkman);
			//提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			session.getTransaction().rollback();
		} finally {
			//关闭session
			HibernateUtil.closeSession();
		}
		
	}
	//更新联系人
	public void updateLinkman(Long custId,Long lkmId,CstLinkman cstLinkman){
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			LinkmanDao linkmanDao = new LinkmanDaoImpl();
			
			//根据id查询联系人
			CstLinkman cstLinkman_update = linkmanDao.findLinkmanById(lkmId);
			cstLinkman_update.setLkmName(cstLinkman.getLkmName());//更新联系人名称
			//根据客户id查询客户信息
			CstCustomer cstCustomer = session.get(CstCustomer.class, custId);
			cstLinkman.setCstCustomer(cstCustomer);
			cstLinkman_update.setCstCustomer(cstCustomer);
			//更新联系人
			linkmanDao.update(cstLinkman_update);
			
			//提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			session.getTransaction().rollback();
		} finally {
			//关闭session
			HibernateUtil.closeSession();
		}
	}
	
	//删除联系人
	public void deleteLinkman(Long lkmId){
		
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			LinkmanDao linkmanDao = new LinkmanDaoImpl();
			//删除联系人
			linkmanDao.delete(lkmId);
			//提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			session.getTransaction().rollback();
		} finally {
			//关闭session
			HibernateUtil.closeSession();
		}
		
	}
	//根据联系人id查询联系人
	public CstLinkman findLinkmanById(Long lkmId){
		LinkmanDao linkmanDao = new LinkmanDaoImpl();
		return linkmanDao.findLinkmanById(lkmId);
	}
	
	//查询联系人表总记录数
	@Override
	public long findLinkmanCount(CstLinkman cstLinkman){
		LinkmanDao linkmanDao = new LinkmanDaoImpl();
		return linkmanDao.findLinkmanCount(cstLinkman);
	}

	@Override
	public List<CstLinkman> findLinkmanList(CstLinkman cstLinkman, int firstResult, int maxResults) {
		LinkmanDao linkmanDao = new LinkmanDaoImpl();
		return linkmanDao.findLinkmanList(cstLinkman, firstResult, maxResults);
		
	}

}
