package cn.atcast.service;

import cn.atcast.dao.DeptDao;
import cn.atcast.entity.Dept;

// 业务逻辑层
public class DeptService {

	private DeptDao deptDao;

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public void save(Dept dept) {
		deptDao.save(dept);
	}
}
