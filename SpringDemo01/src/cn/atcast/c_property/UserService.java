package cn.atcast.c_property;

public class UserService {

	// private UserDao userDao = new UserDao(); 以前的方式
	private UserDao userDao; // null

	// Spring 通过 set() 方法注入对象。同时我们得在配置文件中配置与 UserDao 的依赖关系
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save() {
		userDao.save();
	}

}
