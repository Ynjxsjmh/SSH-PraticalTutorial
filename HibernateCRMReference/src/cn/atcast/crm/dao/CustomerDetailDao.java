package cn.atcast.crm.dao;

import cn.atcast.crm.domain.CstCustomerDetail;

public interface CustomerDetailDao {

	// 添加客户详细信息
	public void insert(CstCustomerDetail customerDetail);
	// 删除客户
	public void delete(Long custId);

	// 更新客户
	public void update(CstCustomerDetail cstCustomerDetail);
	//查询客户详细信息
	public CstCustomerDetail findCustomerDetailById(Long custId);

	
}
