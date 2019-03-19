package cn.atcast.crm.service.impl;

import org.hibernate.Session;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.SaleVisit;
import cn.atcast.crm.domain.SysUser;
import cn.atcast.crm.service.SaleService;
import cn.atcast.crm.util.HibernateUtil;

public class SaleServiceImpl implements SaleService {

	@Override
	public void insertSaleVisit(Long userId, Long custId, SaleVisit saleVisit) {
		//获取与线程绑定的session
		Session session = HibernateUtil.getCurrentSession();
		try {
			//开启事务
			session.beginTransaction();
			//客户
			CstCustomer cstCustomer = new CstCustomer();
			cstCustomer.setCustId(custId);
			saleVisit.setCstCustomer(cstCustomer);
			//用户
			SysUser sysUser = new SysUser();
			sysUser.setUserId(userId);
			saleVisit.setSysUser(sysUser);
			//添加客户拜访
			session.save(saleVisit);
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
	

}
