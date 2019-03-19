package com.atcast.demo2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 演示值栈对象的目录结构
 * @author Administrator
 */
public class ValueStackAction extends ActionSupport{
	/*
	 * 序列化通常是用来传输对象的。传输就有发送方和接收方。为保证正确性，这两方必须使用同一个class来序列化和反序列化一个对象。
	 * 可是，如果两方使用同一个class的不同版本（名字相同，成员和方法不同）怎么办？
	 * 所以java要求每个Serializable的类都有一个serialVersionUID。
	 * 原则上，每次修改类的时候都应该增加或改变这个serialVersionUID。
	 * 这样，如果接收方用的是旧的版本，java就会报错:InvalidClassException。
	 */
	private static final long serialVersionUID = 1079080388172927594L;
	
	/*private User user = new User("小泽","456");
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/

	/**
	 * 演示从值栈中获取值
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		/*// 使用获取值栈对象
		HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
		System.out.println(vs);*/
		
		// 获取到值栈对象，先获取到ActionContext对象
		ValueStack vs = ActionContext.getContext().getValueStack();
		System.out.println(vs);
//		//向栈顶压字符，此时栈顶为"刘备"
//		vs.push("刘备");
//		//向栈顶压继续压入字符，此时栈顶为"张飞"，"刘备"在"张飞"的下面
//		vs.push("张飞");
//       // set(key,obj)也是向栈顶压入对象，只是以map集合的方式，将key和obj存入map集合中。
//     	vs.set("msg", "美美");
//	    vs.set("info", "小苍");;
		
		// 创建User对象
//		  User user = new User("小苍","123");
//		// 压栈
//		 //vs.push(user);
//		 vs.set("user", user);
		
		List<User> ulist = new ArrayList<User>();
		ulist.add(new User("熊大","123"));
		ulist.add(new User("熊二","456"));
		ulist.add(new User("熊三","789"));
 	
		// 把ulist集合压栈
		 // vs.push(ulist);
		
		// set方法进行压栈
 		//vs.set("ulist", ulist);
	 
		// 从context栈中获取值，底层已经封装到request session对象，操作就是map集合
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("msg", "美美");
		request.getSession().setAttribute("msg", "小风");
		
		return SUCCESS;
	}
}
