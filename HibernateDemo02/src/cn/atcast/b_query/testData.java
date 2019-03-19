package cn.atcast.b_query;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class testData {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class).buildSessionFactory();
	}

	@Test
	public void save() {

		Session session = sf.openSession();
		session.beginTransaction();

		Dept dept = new Dept();
		dept.setDeptName("物流部");

		Employee emp_zs = new Employee();
		emp_zs.setEmpName("小猫");
		Employee emp_ls = new Employee();
		emp_ls.setEmpName("小狗");

		emp_zs.setDept(dept);
		emp_ls.setDept(dept);

		session.save(dept);
		session.save(emp_zs);
		session.save(emp_ls);

		session.getTransaction().commit();
		session.close();
	}

}
