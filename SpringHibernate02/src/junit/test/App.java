package junit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.atcast.entity.Dept;
import cn.atcast.service.DeptService;


public class App {
	
	// 容器
	private ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

	@Test
	public void testApp() throws Exception {
		DeptService deptServie = (DeptService) ac.getBean("deptService");
		System.out.println(deptServie.getClass());
		Dept dept=new Dept();
		dept.setName("销售部2");
		deptServie.save(dept); 
	}
}
