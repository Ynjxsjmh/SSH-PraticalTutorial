package cn.atcast.dao.impl;

import org.springframework.stereotype.Repository;

import cn.atcast.dao.CustomerDetailDao;
import cn.atcast.domain.CstCustomerDetail;

@Repository("customerDetailDao")
public class CustomerDetailDaoImpl extends BaseDaoImpl<CstCustomerDetail, Long> implements CustomerDetailDao {

}
