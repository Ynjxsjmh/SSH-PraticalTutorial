package cn.atcast.dao;

import java.util.List;

import cn.atcast.domain.SysUser;
import cn.atcast.pojo.SysUserVo;

public interface SysUserDao extends BaseDao<SysUser, Long> {

	// 查询sysuser记录，不分页
	public List<SysUser> findSysUserList(SysUserVo sysUserVo);
}
