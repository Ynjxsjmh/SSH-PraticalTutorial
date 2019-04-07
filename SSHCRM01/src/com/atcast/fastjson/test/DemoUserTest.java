package com.atcast.fastjson.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atcast.dao.UserDao;
import com.atcast.domain.User;
import com.atcast.service.UserService;
import com.atcast.utils.MD5Utils;

public class DemoUserTest{

	@Test
	//测试是否有用户存在
	public void run1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userdao = (UserDao) ac.getBean("userDao");
		System.out.println(userdao.checkCode("cjc"));
	}

	@Test
	//测试保存用户
	/*在测试除查询以外的操作，如写，更新，删除，不能用UserDao userdao = (UserDao) ac.getBean("userDao"); 会报如下错误。
	 * org.springframework.dao.InvalidDataAccessApiUsageException: Write operations are not allowed in read-only mode (FlushMode.MANUAL): Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.
写操作是不允许在只读模式（FlushMode.NEVER /手动）：把Session了FlushMode.COMMIT / AUTO或删除'只读从交易的定义'标记。
	 错误是主要原因是DAO采用了Spring容器的事务管理策略，spring 会采取默认的事务管理策略(PROPAGATION_REQUIRED,read only).如果是插入和修改操作，就不被允许的，所以报这个异常
	 解决：在spring配置文件中，配置事务管理，指定对那些包下的那些方法名（save*、update*、del*)不采用只读策略，设置read-only="false"。并在测试时获取业务层对象，而不是获取dao层对象。
	 */
	public void run2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//UserDao userdao = (UserDao) ac.getBean("userDao");
		//强制转换为UserService接口形式，如果转为UserServiceImpl会报错。
		UserService userService = (UserService) ac.getBean("userService");
		User user=new User();
		user.setUser_name("美妈");
		user.setUser_password(MD5Utils.md5("666"));
		user.setUser_code("mei");
		user.setUser_state("1");
		//userdao.save(user);
		userService.save(user);
	}

	@Test
	//测试登录
	public void run3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userdao = (UserDao) ac.getBean("userDao");
		User user=new User();
		user.setUser_code("cjc");
		user.setUser_password(MD5Utils.md5("cjc"));
		user.setUser_state("1");
		System.out.println(userdao.login(user));
	}
}