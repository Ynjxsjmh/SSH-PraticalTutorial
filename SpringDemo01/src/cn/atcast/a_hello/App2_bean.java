package cn.atcast.a_hello;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App2_bean {
	@Test
	public void testIOC() throws Exception {
		// 得到IOC容器对象 【用实现类，因为要调用销毁的方法】
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"cn/atcast/a_hello/applicationContext.xml");
		System.out.println("-----容器创建-----");

		// 从容器中获取bean
		User user1 = (User) ac.getBean("user");
		User user2 = (User) ac.getBean("user");

		System.out.println(user1);
		System.out.println(user2);

		// 销毁容器对象
		ac.destroy();
	}
}
