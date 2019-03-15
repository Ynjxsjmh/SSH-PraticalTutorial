package cn.atcast.b_dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 给所有的dao创建代理对象【动态代理】
 * 
 * 代理对象，不需要实现接口
 *
 */
public class ProxyFactory {

	 private Object target; //目标对象
	 
	 public ProxyFactory(Object target){
		 this.target=target;
	 }
	 
	 //返回一个代理对象
	 public Object getProxyInstance() {
		 return Proxy.newProxyInstance(
				 //指定当前目标对象是那个类
				 target.getClass().getClassLoader(), //userDao
				 //userDao接口是那个
				 target.getClass().getInterfaces(), //IUserDao
				 //找目标对象中的那个方法去调用
				 new InvocationHandler(){
					 //proxy目标对象
					 //method调用方法
					 //args参数
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("开启事务");
						Object returnValue=method.invoke(target, args);//执行目标对象中的方法
						System.out.println("提交事务");
						return returnValue;
					}
					 
				 });
	 }
}











