package cn.atcast.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.atcast.crm.domain.SaleVisit;

public interface SaleVisitDao {

	// 添加客户拜访
	public void insertSaleVisit(SaleVisit saleVisit);

	// 客户拜访列表总数
	public long findSaleVisitCount(DetachedCriteria detachedCriteria);
	// 客户拜访列表
	public List<SaleVisit> findSaleVisitList(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

}
