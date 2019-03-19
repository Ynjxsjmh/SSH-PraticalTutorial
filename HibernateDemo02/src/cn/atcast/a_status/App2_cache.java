package cn.atcast.a_status;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App2_cache {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(User.class).buildSessionFactory();
	}

	// hibernate缓存： 一级缓存session和二级缓存sessionFactory
	@Test
	public void testCache() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		User user = null;
		user = (User) session.get(User.class, 4);
		// System.out.println(user);
		// get(),先检查缓存中是否有数据，如果有则不再查询数据库，而是直接从缓存中获取
		user = (User) session.get(User.class, 4);
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void flush() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		User user = null;
		user = (User) session.get(User.class, 4);
		// user.setUserName("jerry");
		// System.out.println(user.getUserName());
		// user.setUserName("mike");
		user.setUserName("jerry"); // 放到缓存
		session.flush();// 缓存数据与数据库同步
		user.setUserName("mike");
		session.getTransaction().commit(); // session.flush();
		session.close();
	}

	@Test
	public void clear() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		User user1 = (User) session.get(User.class, 4);
		User user2 = (User) session.get(User.class, 5);
		// session.clear();//清空所有缓存
		session.evict(user1);
		user1 = (User) session.get(User.class, 4);
		user2 = (User) session.get(User.class, 5);
		session.getTransaction().commit(); // session.flush();
		session.close();
	}

	// 不同的session是否会共享缓存中的数据
	@Test
	public void sessionTest() throws Exception {
		Session session1 = sf.openSession();
		session1.beginTransaction();
		Session session2 = sf.openSession();
		session2.beginTransaction();
		User user = (User) session1.get(User.class, 4);
		User user2 = (User) session2.get(User.class, 4);

		session1.getTransaction().commit(); // session2.flush();
		session1.close();
		session2.getTransaction().commit(); // session2.flush();
		session2.close();
	}
}
