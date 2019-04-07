package cn.atcast.service;

import java.util.List;

import cn.atcast.domain.BaseDict;
import cn.atcast.domain.CstCustomer;
import cn.atcast.domain.CstCustomerDetail;
import cn.atcast.domain.SysUser;
import cn.atcast.pojo.CustomerVo;

public interface SystemService {

	// 根据typecode查询数据字典记录
	public List<BaseDict> findBaseDictByTypeCode(String typecode);

	// 根据用户名称查询用户信息
	public List<SysUser> findSysUserByUserName(String userName);

}
