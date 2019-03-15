package cn.atcast.d_myaop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 目标对象
 *
 */
@Component 
public class UserDao implements IUserDao{
	@Resource 
	private Aop aop;
	public  void save(){
		aop.begin();
		System.out.println("核心业务：保存");
		aop.commite();
	}
}
