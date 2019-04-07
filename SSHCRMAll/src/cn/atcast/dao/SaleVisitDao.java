package cn.atcast.dao;

import java.util.List;

import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.SaleVisitVo;

public interface SaleVisitDao extends BaseDao<SaleVisit, String> {

	// 查询客户拜访列表
	public List<SaleVisit> findSaleVisitList(SaleVisitVo saleVisitVo, int firstResult, int maxResults);

	// 查询客户拜访列表记录总数
	public Long findSaleVisitCount(SaleVisitVo saleVisitVo);

}
