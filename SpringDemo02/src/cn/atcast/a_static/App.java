package cn.atcast.a_static;

public class App {

	public static void main(String[] args) {
		IUserDao target=new UserDao(); //多态 目标对象
		IUserDao proxy=new UserDaoProxy(target);//代理对象
		//target.save();
		proxy.save(); // 执行的是代理的方法

	}

}
