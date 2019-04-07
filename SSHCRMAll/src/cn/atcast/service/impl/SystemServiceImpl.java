package cn.atcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.atcast.dao.BaseDictDao;
import cn.atcast.dao.SysUserDao;
import cn.atcast.domain.BaseDict;
import cn.atcast.domain.SysUser;
import cn.atcast.pojo.BaseDictVo;
import cn.atcast.pojo.SysUserVo;
import cn.atcast.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService {

	// 注入dao
	@Autowired
	private BaseDictDao baseDictDao;

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typecode) {
		// 根据业务接口构造查询
		BaseDictVo baseDictVo = new BaseDictVo();
		baseDictVo.setDictTypeCode(typecode);

		List<BaseDict> list = baseDictDao.findBaseDictList(baseDictVo);

		return list;
	}

	@Override
	public List<SysUser> findSysUserByUserName(String userName) {
		SysUserVo sysUserVo = new SysUserVo();
		// 用户名称
		sysUserVo.setUserName(userName);
		return sysUserDao.findSysUserList(sysUserVo);
	}

}
