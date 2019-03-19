package cn.atcast.crm.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.atcast.crm.dao.CustomerDao;
import cn.atcast.crm.dao.CustomerDetailDao;
import cn.atcast.crm.dao.impl.CustomerDaoImpl;
import cn.atcast.crm.dao.impl.CustomerDetailDaoImpl;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.CstCustomerDetail;
import cn.atcast.crm.service.CustomerService;
import cn.atcast.crm.util.HibernateUtil;

public class CustomerServiceImpl implements CustomerService {

	//添加客户
	@Override
	public void insertCustomer(CstCustomer cstCustomer) {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.insert(cstCustomer);
		
	}
	
	//添加客户，同时添加联系人
//	@Override
//	public void insertCustomer(CstCustomer cstCustomer,Linkman linkman) {
//		CustomerDao customerDao = new CustomerDaoImpl();
//		LinkmanDao linkmanDao = new LinkmanDaoImpl();
//		customerDao.insert(customer);
//		linkmanDao.insert(linkman);
//	}
//	@Override
//	public void insertCustomer(CstCustomer cstCustomer,CstLinkman cstLinkman)  {
//		//获取与线程绑定的session
//		Session session = HibernateUtil.getCurrentSession();
//		try {
//			//开启事务
//			session.beginTransaction();
//			CustomerDao customerDao = new CustomerDaoImpl();
//			LinkmanDao linkmanDao = new LinkmanDaoImpl();
//			customerDao.insert(cstCustomer);
//			//这里cstCustomer新增后得到新客户id
//			//新客户id
////			Long custId = cstCustomer.getCustId();
//			//将新客户id存储到联系人表外键中
////			cstLinkman.setLkmCustId(custId);
//			cstLinkman.setCstCustomer(cstCustomer);
//
//			linkmanDao.insert(cstLinkman);
//			//提交事务
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			//回滚事务
//			session.getTransaction().rollback();
//			throw new RuntimeException("添加客户信息时报错！");
//		} finally {
//			//关闭session
//			HibernateUtil.closeSession();
//		}
//		
//	}
	// 添加客户同时添加联系人、客户详细信息
	public void insertCustomer(CstCustomer cstCustomer,CstCustomerDetail cstCustomerDetail) {

		//获取与线程绑定的session
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			CustomerDao customerDao = new CustomerDaoImpl();
			CustomerDetailDao customerDetailDao = new CustomerDetailDaoImpl();
			
			//保存客户信息
			customerDao.insert(cstCustomer);
			//此时cstCustomer对象为持久对象，由于为自增主键，执行insert后向数据库发出sql，hibernate将新主键id填充到cstCustomer
			cstCustomerDetail.setCustId(cstCustomer.getCustId());
			//保存客户详细信息
			customerDetailDao.insert(cstCustomerDetail);
			
			//提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			session.getTransaction().rollback();
			throw new RuntimeException("添加客户信息时报错！");
		} finally {
			//关闭session
			HibernateUtil.closeSession();
		}
		
	}
	
	
	//查询客户列表总记录数
	@Override
	public long findCustomerCount(CstCustomer cstCustomer){
		CustomerDao customerDao = new CustomerDaoImpl();
		
		//拼接查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CstCustomer.class);
		if(cstCustomer!=null){
			if(cstCustomer.getCustName()!=null && !cstCustomer.getCustName().equals("")){
				//拼接查询条件客户名称
				detachedCriteria.add(Restrictions.eq("custName", cstCustomer.getCustName()));
			}
		}
		return customerDao.findCustomerCount(detachedCriteria);
	}

	@Override
	public List<CstCustomer> findCustomerList(CstCustomer cstCustomer, int firstResult, int maxResults) {
		CustomerDao customerDao = new CustomerDaoImpl();
		//拼接查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CstCustomer.class);
		if(cstCustomer!=null){
			if(cstCustomer.getCustName()!=null && !cstCustomer.getCustName().equals("")){
				//拼接查询条件客户名称
				detachedCriteria.add(Restrictions.eq("custName", cstCustomer.getCustName()));
			}
		}
		return customerDao.findCustomerList(detachedCriteria, firstResult, maxResults);
		
	}

	@Override
	public void updateCustomer(Long custId,CstCustomer cstCustomer,CstCustomerDetail cstCustomerDetail) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			CustomerDao customerDao = new CustomerDaoImpl();
			CustomerDetailDao customerDetailDao = new CustomerDetailDaoImpl();
			
			//根据id查询客户
			CstCustomer cstCustomer_update = customerDao.findCustomerById(custId);
			//更新客户名称
			cstCustomer_update.setCustName(cstCustomer.getCustName());
			//更新客户的其它属性..
			//...

			customerDao.update(cstCustomer_update);
			//根据id查询客户详细信息
			CstCustomerDetail cstCustomerDetail_update = customerDetailDao.findCustomerDetailById(custId);
			//更新客户地址
			cstCustomerDetail_update.setCustAddress(cstCustomerDetail.getCustAddress());//联系地址
			//更新客户详细信息的其它属性..
			//....
			customerDetailDao.update(cstCustomerDetail);
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

	@Override
	public void deleteCustomer(Long custId) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			CustomerDao customerDao = new CustomerDaoImpl();
			CustomerDetailDao customerDetailDao = new CustomerDetailDaoImpl();
			//删除客户基本信息
			customerDao.delete(custId);
			//删除客户详细信息
			customerDetailDao.delete(custId);
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

	@Override
	public CstCustomer findCustomerById(Long custId) {
		CustomerDao customerDao = new CustomerDaoImpl();
		return customerDao.findCustomerById(custId);
	}
	
	//根据主键查询客户详细信息
	public CstCustomerDetail findCstCustomerDetailById(Long custId){
		CustomerDetailDao customerDetailDao = new CustomerDetailDaoImpl();
		return customerDetailDao.findCustomerDetailById(custId);
	}

}
