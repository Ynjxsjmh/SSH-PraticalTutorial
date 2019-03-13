package com.atcast.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.atcast.domain.User;
import com.atcast.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户的模块的控制器
 * 
 * @author Administrator
 */
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1305643617977647333L;

	/**
	 * 处理登录功能
	 * 
	 * @return
	 */
	public String login() {
		System.out.println("login");
		// 这边没有学习功能，封装数据，现在还需要使用request对象
		// Struts2 怎么获取request方式
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取请求参数
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			// login.jsp页面中的属性一定要和User中的属性名一致
			// 现在这种数据封装方式还可以进行优化，可以使用 Struts2 提供的 ModelDriven
			BeanUtils.populate(user, map);
			// 调用业务层
			User existUser = new UserService().login(user);
			// 判断
			if (existUser == null) {
				// 说明，用户名或者密码错误了。返回 login 字符串让其重新登录
				return LOGIN;
			} else {
				// 存入到session中
				request.getSession().setAttribute("existUser", existUser);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String regist() {
		System.out.println("regist");
		return "register";
	}

}