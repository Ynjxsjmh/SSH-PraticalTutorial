package com.atcast.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {

	@Override
	// 一般不会用 execute 方法，而是直接写增删改查
	public String execute() throws Exception {
		System.out.println("ok");
		return null;
	}

	/*
	 * 如果调用完该方法，我们想跳转到某个页面上，以前使用 Servlet 的时候是使用 redirect 和 forward 实现的
	 * 这样有一点不好的是如果我们不想往那个页面跳转了，我们得修改源代码，这样叫做硬编码
	 * 我们要尽量避免硬编码，因为一旦修改我们就要重新编译，重新上传到服务器上，十分麻烦
	 */

	/* 现在我们可以返回一个字符串，并在 struts.xml 中匹配这个字符串，定义其跳转页面
	 * 因为返回字符串的话，struts2 会在其配置文件中对应的 action 节点查找
	 * 以后我们想要改变跳转页面，就只需要在配置文件对应的地方改了，实现最大程度的解耦
	 */
	public String addUser() throws Exception {
		System.out.println("增加用户");
		return "success";
	}

	public String delUser() throws Exception {
		System.out.println("删除用户");
		return null;
	}
}
