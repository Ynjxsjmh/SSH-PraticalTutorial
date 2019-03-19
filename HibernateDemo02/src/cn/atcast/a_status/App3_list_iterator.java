package cn.atcast.a_status;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App3_list_iterator {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(User.class).buildSessionFactory();
	}

	@Test
	public void list() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from User");
		List<User> list = q.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("--------------");
		List<User> list2 = q.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void iterator() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		Query q = session.createQuery("from User");
		Iterator<User> it = q.iterate();
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user);
		}
		System.out.println("---------------");
		Query q2 = session.createQuery("from User");
		Iterator<User> it2 = q2.iterate();
		while (it2.hasNext()) {
			User user = it2.next();
			System.out.println(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void cache() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from User");
		List<User> list = q.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("--------------");
		Query q2 = session.createQuery("from User");
		Iterator<User> it2 = q2.iterate();
		while (it2.hasNext()) {
			User user = it2.next();
			System.out.println(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void list_iterator() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		session.close();
	}
}
