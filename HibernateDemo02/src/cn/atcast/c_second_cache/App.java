package cn.atcast.c_second_cache;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class).buildSessionFactory();
	}

	@Test
	public void testCache() {
		Session session1 = sf.openSession();
		session1.beginTransaction();
		// 第一个session
		// a.查询一次（放入一级缓存，同时如果做了二级缓存配置，不会放入二级缓存）
		Dept dept = (Dept) session1.get(Dept.class, 1);
		// 缓存策略是read-only只读
		// dept.setDeptName("开发部2");
		System.out.println(dept.getDeptName());
		System.out.println(dept.getEmps());
		session1.getTransaction().commit();
		session1.close();

		System.out.println("---------------------------");

		Session session2 = sf.openSession();
		session2.beginTransaction();
		// 第一个session
		// a.查询一次（放入一级缓存，同时如果做了二级缓存配置，不会放入二级缓存）
		Dept dept2 = (Dept) session2.get(Dept.class, 1);
		System.out.println(dept.getDeptName());
		System.out.println(dept2.getEmps());
		session2.getTransaction().commit();
		session2.close();
	}

	@Test
	public void listCache() {

		Session session1 = sf.openSession();
		session1.beginTransaction();
		// 指定list()将查询到的内容放入二级缓存，从二级缓存中去找。
		Query q = session1.createQuery("from Dept").setCacheable(true);
		// list()默认会放入一级缓存，便不会从一级缓存中去取。使用查询缓存，此时，list()就会去查询二级缓存。
		System.out.println(q.list());
		session1.getTransaction().commit();
		session1.close();

		System.out.println("---------------------------");
		Session session2 = sf.openSession();
		session2.beginTransaction();
		// 指定从二级缓存中去找。
		Query q2 = session2.createQuery("from Dept").setCacheable(true);
		System.out.println(q2.list());
		session2.getTransaction().commit();
		session2.close();

	}
}
