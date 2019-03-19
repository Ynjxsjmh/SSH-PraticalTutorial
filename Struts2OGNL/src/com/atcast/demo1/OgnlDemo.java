package com.atcast.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ognl.Ognl;
import ognl.OgnlException;

import org.junit.Test;

public class OgnlDemo {
	
	//1.对Javabean进行操作
	@Test
	/*
	 * 将user对象作为ognl的根，编写ognl表达式来获取user对象中的各种属性。
	 	直接编写属性名，即获取user对象中的某个属性值。
	 */
	public void run1() throws OgnlException{
		User user=new User();
		user.setName("tom");
		String name=(String)Ognl.getValue("name",new HashMap(),user);
		System.out.println(name);
	}
	
	@Test
	/*
	 * 获取javabean中的对象的属性值
	 */
	public void run2() throws OgnlException{
		User user=new User();
		Address address=new Address();
		address.setCity("北京");
		user.setAddress(address);  //在页面上通过ognl表达式取值，context或root
		String city=(String)Ognl.getValue("address.city", new HashMap(),user);
		System.out.println(city);
	}
	
	@Test
	/*
	 * 对javabean中的属性进行赋值操作
	 * 三种方式：
	 * 1.属性=value
	 * Ognl.getValue("name='tom'",new HashMap(),user);
	 * 2.setName(value)
	 * Ognl.getValue("setName('tom')",new HashMap(),user);
	 * 3.Ongl.getValue("name",new HashMap(),user,'tom');
	 */
	public void run3() throws OgnlException{
		User user=new User();
		Ognl.getValue("name='tom'", new HashMap(),user);
		System.out.println(user.getName());
	}
	
	//2.调用对象方法
	@Test
	/*
	 * setName("xxx");对root中的方法进行调用，直接写方法名加参数
	 * getName() 可以通过调用getName()获取user的属性值。
	 */
	public void run4() throws OgnlException{
		User user=new User();
		Ognl.getValue("setName('tom')", new HashMap(),user);
		String name=(String)Ognl.getValue("getName()",new HashMap(),user); //获取name属性值
		System.out.println(name);
	}
	
	//3.调用类的静态方法或属性
	/*
	 * 获取静态的方法和静态变量，不受方法或属性是否在root或context上下文中的限制
	 * 访问静态方法格式： @包名+类名@静态方法名
	 * @java.util.UUID@randomUUID()
	 * 访问静态变量格式:@包名+类名@静态变量名
	 * @java.lang.Math@PI
	 */
	@Test
	public void run5() throws OgnlException{
		User user=new User();
		UUID randomUUID=(UUID)Ognl.getValue("@java.util.UUID@randomUUID()",new HashMap(),user);
		System.out.println(randomUUID.toString());
		double pi=(Double)Ognl.getValue("@java.lang.Math@PI", new HashMap(),user);
		System.out.println(pi);
	}
	//4.索引数组元素
	@Test
	public void run6() throws OgnlException{
		int[] array={1,2,3,4,5};
		//获取数组中第一个元素
		int first=(Integer)Ognl.getValue("[0]", new HashMap(),array);
		System.out.println(first);
	}
	//5.操作集合
	//操作list集合，list集合本质就是一个数组，所以从list中读取数据跟对数组的操作一样
	@Test
	public void run7() throws OgnlException{
		List<User> uList=new ArrayList<User>();
		User user1=new User();
		user1.setName("tom");
		uList.add(user1);
		User user2=new User();
		user2.setName("jerry");
		uList.add(user2);
		//获取集合中的第一个对象的name属性值 root [0].name  username
		String name1=(String)Ognl.getValue("[0].name", new HashMap(),uList);
		System.out.println(name1);
	}
	//操作map集合
	@Test
	public void run8() throws OgnlException{
		 Map<String,String> map=new HashMap<String,String>();
		 map.put("name", "tom");
		 map.put("age", "18");
		 /*
		  * 1.取root下的map集合中的value值
		  * 格式：key  直接写上key，就能找到对应的value值。如果value值是一个对象，通过"."来获取对象中的属性。
		  * 2.取context上下文中的属性，因为上下文就是一个map,为区分是从root还是上下文中取，使用"#"区分。
		  * 格式:#key
		  */
		 String name=(String)Ognl.getValue("name", new HashMap(),map);
		 System.out.println(name);
		 
	 
	}
	//创建list集合
	@Test
	public void run9() throws OgnlException{
		User user=new User();
		List<String> list=(List<String>)Ognl.getValue("{'tom','jerry','mike'}", new HashMap(),user);
		System.out.println(list.get(0));
	}
	//创建map集合
	@Test
	public void run10() throws OgnlException{
		User user=new User();
		Map map=(Map)Ognl.getValue("#{'name':'tom','age':'18'}", new HashMap(),user);
		System.out.println(map.get("name"));
	}
	
}
