package cn.atcast.form;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 1. 数据回显 2. 模型驱动
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	// 封装请求数据
	private User user = new User(); // 第一次创建的user对象

	// 实现模型驱动接口方法
	@Override
	public User getModel() {
		return user;
	}

	public String add() {
		// 测试： 使用了模型驱动，是否数据正常
		System.out.println(user);
		return "success";
	}
}
