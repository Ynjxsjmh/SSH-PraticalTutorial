package com.atcast.action;

import java.util.List;

import com.atcast.domain.Customer;
import com.atcast.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户的控制器
 * 
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = -7111907817761614217L;

	private Customer customer = new Customer();

	public Customer getModel() {
		return customer;
	}

	/**
	 * 保存客户
	 * 
	 * @return
	 */
	public String save() {
		// 保存客户
		System.out.println("save");
		new CustomerService().saveCustomer(customer);
		return "relist";
	}

	/**
	 * 查询所有的客户
	 * 
	 * @return
	 */
	public String list() {
		List<Customer> clist = new CustomerService().findAll();
		// 把clist压入到值栈中
		ValueStack vs = ActionContext.getContext().getValueStack();
		// 压栈，默认的规范：压入的是集合，一般使用set方法，压入是对象，使用push对象
		vs.set("clist", clist);
		return "list";
	}

}
