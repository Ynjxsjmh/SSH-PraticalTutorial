package cn.atcast.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.atcast.crm.domain.CstCustomer;

public interface CustomerDao {

	// 添加客户
	public void insert(CstCustomer customer);

	// 删除客户
	public void delete(Long custId);

	// 更新客户
	public void update(CstCustomer customer);

	// 根据id查找客户
	public CstCustomer findCustomerById(Long custId);

	// 自定义条件查询客户列表
	public List<CstCustomer> findCustomerList(CstCustomer customer, int firstResult, int maxResults);
	public List<CstCustomer> findCustomerList(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

	// 自定义查询客户列表总记录数
	public Long findCustomerCount(CstCustomer cstCustomer);
	public Long findCustomerCount(DetachedCriteria detachedCriteria);
}
