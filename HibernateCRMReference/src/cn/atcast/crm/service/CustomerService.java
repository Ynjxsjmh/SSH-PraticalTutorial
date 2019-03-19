package cn.atcast.crm.service;

import java.util.List;

import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.CstCustomerDetail;

public interface CustomerService {

	// 添加客户
	public void insertCustomer(CstCustomer cstCustomer);

	// 添加客户同时添加客户详细信息
	public void insertCustomer(CstCustomer cstCustomer,CstCustomerDetail cstCustomerDetail) ;
	
	/**
	 * 
	 * <p>Title: updateCustomer</p>
	 * <p>Description:更新客户 </p>
	 * @param custId 客户id
	 * @param cstCustomer 客户基本信息
	 * @param cstCustomerDetail 客户详细信息
	 */
	public void updateCustomer(Long custId,CstCustomer cstCustomer,CstCustomerDetail cstCustomerDetail);
	
	/**
	 * 
	 * <p>Title: deleteCustomer</p>
	 * <p>Description:删除客户 </p>
	 * @param custId 客户id
	 */
	public void deleteCustomer(Long custId);
	
	//根据主键查询客户
	public CstCustomer findCustomerById(Long custId);
	
	//根据主键查询客户详细信息
	public CstCustomerDetail findCstCustomerDetailById(Long custId);
	
	// 查询客户列表总记录数
	public long findCustomerCount(CstCustomer cstCustomer);

	// 查询客户列表
	public List<CstCustomer> findCustomerList(CstCustomer cstCustomer, int firstResult, int maxResults);
}
