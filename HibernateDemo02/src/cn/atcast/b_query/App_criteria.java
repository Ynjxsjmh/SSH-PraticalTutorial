package cn.atcast.b_query;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class App_criteria {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class) // 测试时候使用
				.buildSessionFactory();
	}

	@Test
	public void criteria() {

		Session session = sf.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Employee.class);
		// 构建条件
		criteria.add(Restrictions.eq("empId", 1));
		// criteria.add(Restrictions.idEq(1)); // 主键查询

		System.out.println(criteria.list());

		session.getTransaction().commit();
		session.close();
	}

	// 5) SQLQuery， 本地SQL查询
	// 不能跨数据库平台： 如果改了数据库，sql语句有肯能要改。
	@Test
	public void sql() {

		Session session = sf.openSession();
		session.beginTransaction();

		SQLQuery q = session.createSQLQuery("SELECT * FROM t_Dept limit 3;").addEntity(Dept.class); // 也可以自动封装
		Dept dept = (Dept) q.list().get(0);
		System.out.println(dept.getDeptName());

		session.getTransaction().commit();
		session.close();
	}

}
