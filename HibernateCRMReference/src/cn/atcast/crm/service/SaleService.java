package cn.atcast.crm.service;

import cn.atcast.crm.domain.SaleVisit;

public interface SaleService {

	//添加客户拜访
	public void insertSaleVisit(Long userId,Long custId,SaleVisit saleVisit);
	
}
