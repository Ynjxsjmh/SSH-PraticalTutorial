package cn.atcast.b_query;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App_page {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(Dept.class).addClass(Employee.class).buildSessionFactory();
	}

	@Test
	public void all() {

		Session session = sf.openSession();
		session.beginTransaction();
		// mysql limit 0,2
		Query q = session.createQuery("from Employee");
		// q.setFirstResult(1);
		// q.setMaxResults(2);
		// System.out.println(q.list());

		// Query q = session.createQuery("select count(*) from Employee");
		// Long num = (Long) q.uniqueResult();
		// System.out.println(num);
		// 创建一个可以滚动的结果集
		ScrollableResults scroll = q.scroll(); // 指向第0行
		scroll.last(); // 将指針移到最后一行
		int totalnum = scroll.getRowNumber() + 1;
		System.out.println(totalnum);
		session.getTransaction().commit();
		session.close();
	}
}
