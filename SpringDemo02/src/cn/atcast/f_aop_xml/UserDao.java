package cn.atcast.f_aop_xml;

/**
 * 目标对象
 *
 */
public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.out.println("-----核心业务：保存！！！------"); 
	}
}
