package cn.atcast.h_jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App3 {

	// 容器对象
	ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/h_jdbc/bean.xml");
	
	@Test
	public void testApp() throws Exception {
		UserDao3 ud = (UserDao3) ac.getBean("userDao3");
//		ud.save();
		System.out.println(ud.findById(1));
		System.out.println(ud.getAll());
	}
}
