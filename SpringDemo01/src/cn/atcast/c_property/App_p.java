package cn.atcast.c_property;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App_p {

	// 创建容器对象
	private ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/c_property/bean_p.xml");

	@Test
	public void testExecuteAction() {
		// 从容器中获取Action
		UserAction userAction = (UserAction) ac.getBean("userAction");
		userAction.execute();

		System.out.println(ac.getBean("user"));
	}
}
