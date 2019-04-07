package cn.atcast.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.atcast.dao.SaleVisitDao;
import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.SaleVisitVo;
import cn.atcast.service.SaleVisitService;

@Service("saleVisitService")
public class SaleVisitServiceImpl implements SaleVisitService {

	@Autowired
	private SaleVisitDao saleVisitDao;

	@Override
	public void insertSaleVisit(SaleVisitVo saleVisitVo) {
		if (saleVisitVo == null) {
			// 抛出异常
			throw new RuntimeException("非法参数");
		}
		SaleVisit saleVisit = new SaleVisit();
		// 将saleVisitVo的属性拷贝到saleVisit
		BeanUtils.copyProperties(saleVisitVo, saleVisit);
		saleVisitDao.insert(saleVisit);

	}

}
