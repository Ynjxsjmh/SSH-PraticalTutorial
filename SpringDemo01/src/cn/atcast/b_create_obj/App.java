package cn.atcast.b_create_obj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	// 测试：对象创建

	// 2. 带参数构造器测试
	@Test
	public void testIOC2() throws Exception {
		// 创建IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/b_create_obj/bean.xml");
		// 获取容器中的对象
		User user = (User) ac.getBean("user2");

		System.out.println(user);
	}

	// 2. 带参数构造器测试 —— 通过 ref 标签传入字符串
	@Test
	public void testIOC3() throws Exception {
		// 创建IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/b_create_obj/bean.xml");
		// 获取容器中的对象
		User user = (User) ac.getBean("user3");

		System.out.println(user);
	}

	// 3. 工厂类创建对象
	// # 3.1 工厂类，实例方法
	@Test
	public void testIOC4() throws Exception {
		// 创建IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/b_create_obj/bean.xml");
		// 获取容器中的对象
		User user = (User) ac.getBean("user4");

		System.out.println(user);
	}

	// # 3.2 工厂类： 静态方法
	@Test
	public void testIOC5() throws Exception {
		// 创建IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/b_create_obj/bean.xml");
		// 获取容器中的对象
		User user = (User) ac.getBean("user5");

		System.out.println(user);
	}
}
