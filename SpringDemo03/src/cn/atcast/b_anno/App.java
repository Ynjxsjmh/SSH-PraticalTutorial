package cn.atcast.b_anno;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	@Test
	public void testApp() throws Exception {
		// 容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/atcast/b_anno/bean.xml");
		// 模拟数据
		Dept dept = new Dept();
		dept.setDeptName("测试： 开发部");

		DeptService deptService = (DeptService) ac.getBean("deptService");
		deptService.save(dept);
	}
}
