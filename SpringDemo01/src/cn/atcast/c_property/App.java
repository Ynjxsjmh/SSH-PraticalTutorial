package cn.atcast.c_property;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	// 创建容器对象
	private ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/c_property/bean.xml");

	@Test
	public void testSet() {
		// 从容器中获取
		User user = (User) ac.getBean("user2");

		System.out.println(user);
	}

	@Test
	public void testExecuteAction() {
		// 从容器中获取Action
		UserAction userAction = (UserAction) ac.getBean("userAction2");
		userAction.execute();

	}
}
