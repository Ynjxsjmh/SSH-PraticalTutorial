package com.atcast.web.action;

import com.atcast.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 客户的Action
 * 
 * @author Administrator
 */
public class CustomerAction extends ActionSupport {

	private static final long serialVersionUID = 113695314694166436L;
	// 提供service的成员属性，提供set方法
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 保存客户
	 * 
	 * @return
	 */
	public String save() {
		System.out.println("WEB层：保存客户...");
		customerService.save();
		return NONE;
	}
}
