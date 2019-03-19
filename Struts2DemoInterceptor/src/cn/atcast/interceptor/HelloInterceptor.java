package cn.atcast.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 自定义拦截器
 */
public class HelloInterceptor implements Interceptor {
	// 启动时候执行
	public HelloInterceptor() {
		System.out.println("1.创建了拦截器对象");
	}

	// 启动时候执行
	@Override
	public void init() {
		System.out.println("2.执行了拦截器的初始化方法");
	}

	// 拦截器业务处理方法 （在访问action时候执行？ 在execute之前执行？）
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("4. 执行Action之前");
		// 调用下一个拦截器或执行Action (相当于chain.doFilter(..)
		// 获取的是： execute方法的返回值
		String resultFlag = invocation.invoke();
		System.out.println("6. 拦截器，业务处理-结束" + resultFlag);
		return resultFlag;
	}

	@Override
	public void destroy() {
		System.out.println("7. 销毁....");
	}
}
