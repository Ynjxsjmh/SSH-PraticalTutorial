package cn.atcast.web.interceptor;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.atcast.util.FastJsonUtil;

public class ExceptionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = null;
		try {
			result = invocation.invoke();

		} catch (Exception e) {
			e.printStackTrace();
			// 获取action的实例
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			// 获取到异常信息
			Collection<String> actionErrors = actionSupport.getActionErrors();
			for (String error : actionErrors) {
				if (error.indexOf("Request exceeded allowed size limit") >= 0) {
					// 向客户端输出错误信息“上传文件过大”
					String ajaxResult = FastJsonUtil.ajaxResult(false, "上传文件过大");
					// 输出json
					FastJsonUtil.write_json(response, ajaxResult);
				}
			}
		}

		return result;
	}

}
