package cn.atcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.atcast.domain.CstCustomer;
import cn.atcast.pojo.CustomerVo;

public interface CustomerDao extends BaseDao<CstCustomer, Long> {

	// 添加客户
	// public void insert(CstCustomer customer);
	// 修改
	// public void update(CstCustomer customer);

	// 根据id查找客户
	// public CstCustomer findCustomerById(Long custId);

	// 根据id删除客户基本信息
	// public void delete(Long custId);

	// 查询客户信息列表
	public Long findCustomerCount(CustomerVo customerVo);

	public List<CstCustomer> findCustomerList(CustomerVo customerVo,
			int firstResult, int maxResults);

	public List<CstCustomer> findCustomerList(CustomerVo customerVo);

	// 客户统计
	public List findCustomerStatList(CustomerVo customerVo, int firstResult,
			int maxResults);

	public Long findCustomerStatCount(CustomerVo customerVo);

	// 创建一个DetachedCriteria
	DetachedCriteria createDetachedCriteria();

}
