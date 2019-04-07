package cn.atcast.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.atcast.dao.SysUserDao;
import cn.atcast.domain.SysUser;
import cn.atcast.pojo.SysUserVo;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, Long> implements SysUserDao {

	@Override
	public List<SysUser> findSysUserList(SysUserVo sysUserVo) {

		StringBuffer queryString = new StringBuffer();
		queryString.append(" from SysUser u where 1=1 ");
		// 参数
		List<Object> params = new ArrayList<Object>();

		if (sysUserVo != null) {
			if (StringUtils.isNotEmpty(sysUserVo.getUserName())) {
				// 使用hql
				queryString.append(" and u.userName like ? ");
				params.add("%" + sysUserVo.getUserName() + "%");
			}
		}

		return (List<SysUser>) this.getHibernateTemplate().find(queryString.toString(), params.toArray());
	}

}
