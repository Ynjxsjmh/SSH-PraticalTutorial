package cn.atcast.b_one2Many;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App3_inverse {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class) // 测试时候使用
				.buildSessionFactory();
	}

	// 1. 保存数据
	@Test
	public void save() {

		Session session = sf.openSession();
		session.beginTransaction();

		// 部门对象
		Dept dept = new Dept();
		dept.setDeptName("应用开发部");
		// 员工对象
		Employee emp_zs = new Employee();
		emp_zs.setEmpName("张三");
		Employee emp_ls = new Employee();
		emp_ls.setEmpName("李四");

		// 关系
		// 如果此时设置inverse=true, 则员工方不会维护关联关系，通过部门方维护。
		dept.getEmps().add(emp_zs);
		dept.getEmps().add(emp_ls);

		// 保存
		session.save(emp_zs);
		session.save(emp_ls);
		session.save(dept); // 保存部门，部门下所有的员工

		session.getTransaction().commit();
		session.close();
	}

	// 2. 是否设置inverse，对获取数据的影响？ 无.
	@Test
	public void get() {
		Session session = sf.openSession();
		session.beginTransaction();

		Dept dept = (Dept) session.get(Dept.class, 1);
		System.out.println(dept.getDeptName());
		System.out.println(dept.getEmps());

		session.getTransaction().commit();
		session.close();
	}

	// 3. 是否设置inverse属性，在删除数据中对关联关系的影响?
	// inverse=false, 有控制权， 可以删除。先清空外键引用，再删除数据。
	// inverse=true, 没有控制权: 如果删除的记录有被外键引用，会报错，违反主外键引用约束！
	// 如果删除的记录没有被引用，可以直接删除。
	@Test
	public void deleteData() {
		Session session = sf.openSession();
		session.beginTransaction();

		// 查询部门
		// inverse=true, 没有控制权: 如果删除的记录有被外键引用，会报错，违反主外键引用约束！ 但可以设置cascade="delete"
		Dept dept = (Dept) session.get(Dept.class, 5);
		session.delete(dept);

		session.getTransaction().commit();
		session.close();
	}

}
