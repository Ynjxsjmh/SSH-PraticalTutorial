package cn.atcast.execute;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	// 属性名与login.jsp中表单中传递的参数名一致。
	// struts2自动赋值参数，通过params拦截器
	private String userName;
	private String pwd;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String login() {
		// 获取用户名密码
		System.out.println(userName);
		System.out.println(pwd);

		// 把数据保存到域
		ActionContext ac = ActionContext.getContext();

		// 得到代表request的map
		Map<String, Object> request = ac.getContextMap();
		// 得到代表session的map
		Map<String, Object> session = ac.getSession();
		// 得到代表servletContext的map
		Map<String, Object> application = ac.getApplication();

		// 保存
		request.put("request_data", "request_data");
		session.put("session_data", "session_data");
		application.put("application_data", "application_data");
		return "login";
	}
}
