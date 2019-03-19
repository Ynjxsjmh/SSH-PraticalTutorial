package cn.atcast.framework;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.atcast.framework.bean.ActionMapping;
import cn.atcast.framework.bean.ActionMappingManager;
import cn.atcast.framework.bean.Result;

/**
 * 核心控制器,此项目只有这一个servlet
 * 1. 拦截所有的*.action为后缀的请求，简化web.xml中关于Servlet的配置。
 * 2. 请求:http://localhost:8080/mystruts/login.action
 * 		  http://localhost:8080/mystruts/register.action
 *
 */
/*
 * 要解决两个问题：通过外部配置文件mystruts.xml描述。
 * 1.如何知道创建了不同的Action
 * 请求路径与处理Action的关系
 *  /login=LoginAction    /register=registerAction
 * 2.跳转页面写死了，不便于维护
 *  registerSuccess=/login.jsp 注册成功
 *  loginFaild=/login.jsp 登陆失败
 *  loginSuccess=/index.jsp 登陆成功（使用重定向）
 */
public class ActionServlet extends HttpServlet{
	
	private ActionMappingManager actionMappingManager;
	
	// 读取配置文件，只执行一次  (希望启动时候执行)
	@Override
	public void init() throws ServletException {
		System.out.println("ActionServlet.init()");
		actionMappingManager = new ActionMappingManager();
	}

	// http://localhost:8080/mystruts/login.action
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. 获取请求uri, 得到请求路径名称   【login】
			String uri = request.getRequestURI();
			// 得到 login
			String actionName=uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".action"));
			
			// 2. 根据路径名称，读取配置文件，得到类的全名   【cn.atcast.framework.action.LoginAction】
			ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
			String className = actionMapping.getClassName();
			
			// 当前请求的处理方法   【method="login"】
			String method = actionMapping.getMethod();
			
			// 3. 反射： 创建对象，调用方法； 获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();  //LoginAction loginAction = new LoginAction();
			//调用action中的方法，以及需要传递的参数类型(即调用LoginAction.java中的login()方法)
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class );
			// 调用方法返回的标记"login"
			String returnFlag =  (String) m.invoke(obj, request, response);
			
			// 4. 拿到标记"login"，读取配置文件得到标记对应的页面 （即result name="loginSuccess"中的loginSuccess）、 跳转类型
			Result result = actionMapping.getResults().get(returnFlag);
			// 类型
			String type = result.getType();
			// 页面
			String page = result.getPage();
			
			// 5.跳转
			if ("redirect".equals(type)) {
				response.sendRedirect(request.getContextPath() + page);
			} else {
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
