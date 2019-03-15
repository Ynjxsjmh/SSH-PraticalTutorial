package cn.atcast.d_myaop1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 */
public class ProxyFactory {

	// 目标对象
	private static Object target;
	private static Aop aop;

	// 生成代理对象方法
	public static Object getProxyInstance(Object target_, Aop aop_) {
		target = target_;
		aop = aop_;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						aop.begin();
						Object returnValue = method.invoke(target, args);
						aop.commite();
						return returnValue;
					}

				});
	}
}
