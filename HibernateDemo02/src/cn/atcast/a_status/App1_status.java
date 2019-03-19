package cn.atcast.a_status;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App1_status {

	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().addClass(User.class).buildSessionFactory();
	}

	// 对象状态的转换
	@Test
	public void testSaveSet() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		User user = new User(); // 临时
		user.setUserName("tom");
		session.save(user);// 持久
		// user.setUserName("jerry");
		session.getTransaction().commit();
		session.close();
		user.setUserName("jerry"); // 游离
	}
}
