package cn.atcast.dao;

import cn.atcast.entity.User;


public class UserDao {

	// 模拟登陆
	public User login(User user){
		if ("tom".equals(user.getName()) && "tom".equals(user.getPwd()) ){
			// 登陆成功
			return user;
		}
		// 登陆失败
		return null;
	}
	
	// 模拟注册
	public void register(User user) {
		System.out.println("注册成功：用户，" + user.getName());
	}
}
