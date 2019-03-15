package cn.atcast.b_dynamic;

/**
 * 目标对象
 *
 */
public class UserDao2 implements IUserDao2{

	@Override
	public void save() {
		System.out.println("-----已经保存数据111！！------");
	}

}
