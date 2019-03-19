package cn.atcast.interceptor;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action开发测试
 */
public class HelloAction extends ActionSupport {

	public HelloAction() {
		System.out.println("3. Action实例创建了");
	}

	@Override
	public String execute() throws Exception {
		System.out.println("5. 执行了请求处理的方法: execute");
		return super.execute();
	}
}
