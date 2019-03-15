package cn.atcast.a_tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	@Test
	public void testApp() throws Exception {
		// 容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/a_tx/bean.xml");

		// 模拟数据
		Dept dept = new Dept();
		dept.setDeptName("测试： 开发部");

		DeptService deptService = (DeptService) ac.getBean("deptService");
		deptService.save(dept);

	}
}
