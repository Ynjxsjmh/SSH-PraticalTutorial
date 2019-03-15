package cn.atcast.c_anno;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service
 *
 */
@Service
public class DeptService {

	// 部门dao
	@Resource
	private DeptDao deptDao;

	// 日志dao
	@Resource
	private LogDao logDao;

	/*
	 * 事务控制
	 */
	@Transactional(readOnly = false, // 读写事务
			timeout = -1, // 事务的超时时间不限制(数据库有异常或没有连接上，等待的时间，但还是要看连接的数据库是如何设置的。)
			// noRollbackFor = ArithmeticException.class, //
			// noRollbackFor设置遇到指定的错误不用回滚。此处是遇到数学异常不回滚
			isolation = Isolation.DEFAULT, // 事务的隔离级别，数据库的默认
			propagation = Propagation.REQUIRED // 事务的传播行为,此处是指当前的方法要在事务中去执行。
	)
	/*
	 * DeptService执行save()时会调用insertLog(), 当前saveDept()方法有事务，
	 * 而insertLog()方法的事务传播行为为required, insertLog()会加入saveDept()方法的事务，
	 * 它们用的是同一个事务。如果在insertLog()方法后有一个异常，日志不会插入，因为用的是同一个事务。
	 * 如果此时要求日志一定要插入，此时用required就不可以。
	 */
	public void save(Dept dept) {
		logDao.insertLog(); // 保存日志 【自己开启一个事务】
		deptDao.save(dept); // 保存部门
		// int i = 1/0;
		deptDao.save(dept); // 保存部门
	}
}
