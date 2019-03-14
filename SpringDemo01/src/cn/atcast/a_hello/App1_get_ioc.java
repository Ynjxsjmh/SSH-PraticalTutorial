package cn.atcast.a_hello;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App1_get_ioc {

	// 1. 通过工厂类得到IOC容器创建的对象
	@Test
	public void testIOC() throws Exception {
		// 创建对象
		// 原来创建对象是 User user = new User();
		// 现在，把对象的创建交给spring的IOC容器
		// 读配置文件的方式有很多种，这里只是其中一种，这种过时了。
		Resource resource = new ClassPathResource("cn/atcast/a_hello/applicationContext.xml");
		// 创建容器对象(Bean的工厂), IOC容器 = 工厂类 + applicationContext.xml
		BeanFactory factory = new XmlBeanFactory(resource);
		// 得到容器创建的对象，getBean 的参数就是刚才写的配置文件里的 id
		User user = (User) factory.getBean("user");
		Map map = new HashMap();
		System.out.println(user.getId());
	}

	// 2. （方便）直接得到IOC容器对象
	@Test
	public void testAc() throws Exception {
		// 得到IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/a_hello/applicationContext.xml");
		// 从容器中获取bean
		User user1 = (User) ac.getBean("user");
		User user2 = (User) ac.getBean("user");

		System.out.println(user1);
		System.out.println(user2);
		// 这种方法只是在测试的时候这样用，整合的时候是另一种方法拿到 bean——自动注入。
	}
}
