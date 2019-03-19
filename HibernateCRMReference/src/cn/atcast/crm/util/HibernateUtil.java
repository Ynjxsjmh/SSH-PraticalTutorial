package cn.atcast.crm.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * <p>
 * Title: HibernateUtil
 * </p>
 * <p>
 * Description:session工具类
 * </p>
 */
public class HibernateUtil {

	// 会话工厂，以单例方式管理
	private static SessionFactory sessionFactory;

	// ThreadLocal存储session
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();


	// 以单例方式管理sessionFactory
	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException("初始化会话工厂失败！");
		}

	}
	//得到一个单例的会话工厂
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	//获取一个新session
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	//获取当前与线程绑定的session，如果获取不到则创建一个新session并与当前线程绑定
//	public static Session getCurrentSession() throws HibernateException {
//		//获取当前线程绑定的session
//		Session s = (Session) session.get();
//		if (s == null) {
//			//创建一个新session
//			s = sessionFactory.openSession();
//			//新session并与当前线程绑定
//			session.set(s);
//		}
//		return s;
//	}
 
	public static Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession(); 
	}
	//关闭当前线程绑定的session
//	public static void closeSession() throws HibernateException {
//		//获取当前线程绑定的session
//		Session s = (Session) session.get();
//		if (s != null){
//			//关闭session
//			s.close(); 
//		}
//		session.set(null);
//	}
	
	public static void closeSession() throws HibernateException {
		sessionFactory.getCurrentSession().close();
	}


}
