package cn.atcast.g_pointcut;

/**
 * 目标对象
 *
 */
public class UserDao implements IUserDao{
	@Override
	public void save() {
		System.out.println("-----核心业务：保存！！！userdao------"); 
	}
}
