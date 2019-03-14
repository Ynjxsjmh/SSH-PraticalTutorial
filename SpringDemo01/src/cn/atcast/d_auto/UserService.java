package cn.atcast.d_auto;

public class UserService {

	// 我们这里定义了一个 UserDao，然后 Spring 就会从配置文件中找哪个是 UserDao，找到了就自动注入
	private UserDao userDao1; // = new UserDao();
	// IOC：对象的创建交给spring的外部容器完成

	public void save() {
		userDao1.save();
	}

	public void setUserDao1(UserDao userDao1) {
		this.userDao1 = userDao1;
	}
}
