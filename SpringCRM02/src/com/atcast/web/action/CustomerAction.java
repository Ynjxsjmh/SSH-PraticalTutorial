package com.atcast.web.action;

import java.util.List;

import com.atcast.domain.Customer;
import com.atcast.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户的控制层
 * 
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	// 这里实现的 ModelDriven 可以帮我们用表单中的数据封装 Customer
	// 如果是其他对象，换成其他对象的类型名就行

	private static final long serialVersionUID = 113695314694166436L;

	// 不要忘记手动new
	private Customer customer = new Customer();

	// 实现 ModelDriven 中的方法
	@Override
	public Customer getModel() {
		// 魔法时间到...
		// 手动 new 的时候 customer 是空值，自动调用这个方法后返回封装好的 customer
		return customer;
	}

	// 提供service的成员属性，提供set方法。
	// 因为在配置 CustomerAction 的时候依赖了 CustomerService（控制层要调用业务层的方法）
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 初始化到添加的页面
	 * 
	 * @return
	 */
	public String initAddUI() {
		return "initAddUI";
	}

	/**
	 * 保存客户的方法
	 * 
	 * @return
	 */
	public String save() {
		System.out.println("WEB层：保存客户...");
		/*
		 * WEB的工厂 
		 * WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		 * CustomerService cs = (CustomerService)
		 * context.getBean("customerService"); 
		 * // 调用方法 
		 * cs.save(customer);
		 */

		// 原来封装数据我们是使用 BeanUtils 这个工具类，但是这种封装方法在深克隆（对象嵌套对象又嵌套对象）的时候可能会丢失属性。
		// Struts2 给我们提供了更好的方案：ModelDriven，通过值栈（valueStack）帮我们获取到对象值
		customerService.save(customer);

		// 执行完 save 方法后再执行一遍 findAll 方法，重新查询数据库
		// 方法是在 struts2.xml 中配置
		return "findAll";  
	}

	/**
	 * 查询所有的客户
	 * 
	 * @return
	 */
	public String findAll() {
		List<Customer> list = customerService.findAll();
		// 查找完所有的 customer 后，我们原来是通过 request.setAttribute() 来存储查找后的值，以便跳转到另一个页面可以得到使用
		// 现在我们用 struts2 提供的值栈可以完成这一需求
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		return "list";
	}

	/**
	 * 演示的延迟加载的问题
	 * 
	 * @return
	 */
	public String loadById() {
		Customer c = customerService.loadById(2L);
		// 打印客户的名称
		System.out.println(c.getCust_name());
		return NONE;
	}
}
