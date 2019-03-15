package cn.atcast.c_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Cglib子类代理工厂
 * (对UserDao 在内存中动态构建一个子类对象)
 *
 */
public class ProxyFactory implements MethodInterceptor{
	
	// 维护目标对象
	private Object target;
	public ProxyFactory(Object target){
		this.target = target;
	}
	
	// 给目标对象创建代理对象
	public Object getProxyInstance(){
		//1. 工具类
		Enhancer en = new Enhancer();
		//2. 设置父类（以子类方式在内存中动态创建代理对象，需要知道子类的父类，此处为target，即是UserDao的实例对象）
		en.setSuperclass(target.getClass());
		//3. 设置回调函数（执行target类里的方法时，会触发拦截器中的方法）
		en.setCallback(this);
		//4. 创建子类(代理对象)
		return en.create();
	}
	
	/*
	 * CGLib采用非常底层的字节码技术，可以为一个类创建一个子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，并顺势植入横切逻辑。
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		System.out.println("开始事务.....");
		
		// 执行目标对象的方法
		Object returnValue = method.invoke(target, args);
		
		System.out.println("提交事务.....");
		
		return returnValue;
	}
}
