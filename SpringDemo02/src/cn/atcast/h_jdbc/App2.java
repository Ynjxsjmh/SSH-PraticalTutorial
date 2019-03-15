package cn.atcast.h_jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2 {

	// 容器对象
	ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/h_jdbc/bean.xml");
	
	@Test
	public void testApp() throws Exception {
		UserDao2 ud = (UserDao2) ac.getBean("userDao2");
		ud.save();
	}
}
