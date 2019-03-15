package cn.atcast.d_myaop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	ApplicationContext ac = 
		new ClassPathXmlApplicationContext("cn/atcast/d_myaop/bean.xml");

	@Test
	public void testApp() {
		IUserDao userDao = (IUserDao) ac.getBean("userDao");
		userDao.save();
	}
}
