package cn.atcast.b_dynamic;



public class App {

	public static void main(String[] args) {
		IUserDao2 target=new UserDao2();//目标对象
		System.out.println(target.getClass());
		//代理对象
		IUserDao2 proxy=(IUserDao2)new ProxyFactory(target).getProxyInstance();
		proxy.save();
		
	}
}
