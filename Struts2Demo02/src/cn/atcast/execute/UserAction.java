package cn.atcast.execute;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	// 如果使用的是对象，loin.jsp会自动将userName和pwd封装到user对象中。 params拦截器完成此功能。
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String login() {
		// 获取用户名密码
		System.out.println(user.getUserName());
		System.out.println(user.getPwd());
		return "login";
	}
}
