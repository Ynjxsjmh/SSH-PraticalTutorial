package com.atcast.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atcast.domain.Customer;
import com.atcast.service.CustomerService;

/**
 * 测试Hibernate模板类的简单方法
 * @author Administrator
 */
/**
 * 测试共公类 
 * 在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环境
 */
// locations 可以拿多个 XML 文件，这里只拿了一个
// 因为我们做测试的时候并没有启动 tomcat，因此读不到 web.xml 文件，也就无法知道 配置文件在哪。所以我们得指出配置文件的位置。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Demo1 {
	// 对业务层进行测试，首先要有实体对象
	// @Autowired
	@Resource(name = "customerService")
	private CustomerService customerService;  // 如果不引入配置文件，这里是会 null

	/**
	 * 测试 需要Junit4.9以上
	 */
	@Test
	public void testAdd() {
		Customer customer = new Customer();
		customer.setCust_id(1L);
		customer.setCust_name("test");
		customerService.save(customer);
	}

	/**
	 * 查询某个客户
	 */
	@Test
	public void run2() {
		Customer customer = customerService.getById(2L);
		System.out.println(customer);
	}

	/**
	 * 查询所有客户
	 */
	@Test
	public void run3() {
		List<Customer> list = customerService.findAll();
		System.out.println(list);
	}

	/**
	 * QBC查询所有的数据
	 */
	@Test
	public void run4() {
		List<Customer> list = customerService.findAllByQBC();
		System.out.println(list);
	}
}
