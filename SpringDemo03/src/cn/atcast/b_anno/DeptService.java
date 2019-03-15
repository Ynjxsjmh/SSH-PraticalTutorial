package cn.atcast.b_anno;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {

	// 部门dao
	@Resource
	private DeptDao deptDao;

	// 事务控制
	@Transactional
	public void save(Dept dept) {
		deptDao.save(dept);
		// int i=1/0;
		deptDao.save(dept);
	}
}
