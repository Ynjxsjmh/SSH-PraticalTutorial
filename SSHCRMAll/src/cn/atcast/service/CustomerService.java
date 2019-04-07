package cn.atcast.service;

import java.util.List;

import cn.atcast.domain.CstCustomer;
import cn.atcast.domain.CstCustomerDetail;
import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.CstCustomerDetailVo;
import cn.atcast.pojo.CustomerVo;
import cn.atcast.pojo.SaleVisitVo;

public interface CustomerService {

	// 根据主键查询客户
	public CstCustomer findCustomerById(Long custId);

	// 根据主键查询客户详细信息
	public CstCustomerDetail findCustomerDetailById(Long custId);

	// 新增客户
	public void insertCustomer(CustomerVo customerVo, CstCustomerDetailVo customerDetailVo);

	// 修改客户
	// service方法参数尽量用基本类，参数细化
	public void updateCustomer(Long custId, CustomerVo customerVo, CstCustomerDetailVo customerDetailVo);

	// 查询客户信息列表
	public Long findCustomerCount(CustomerVo customerVo);

	public List<CstCustomer> findCustomerList(CustomerVo customerVo, int firstResult, int maxResults);

	// 删除客户信息
	public void deleteCustomer(Long custId);

	// 查询客户拜访
	// 查询客户拜访列表
	public List<SaleVisit> findSaleVisitList(SaleVisitVo saleVisitVo, int firstResult, int maxResults);

	// 查询客户拜访列表记录总数
	public Long findSaleVisitCount(SaleVisitVo saleVisitVo);

	// 客户统计
	public List findCustomerStatList(CustomerVo customerVo, int firstResult, int maxResults);

	public Long findCustomerStatCount(CustomerVo customerVo);

	// 根据客户名称搜索客户信息
	public List<CstCustomer> findCustomerByName(String custName);
}
