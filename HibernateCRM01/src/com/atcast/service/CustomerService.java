package com.atcast.service;

import com.atcast.dao.CustomerDao;
import com.atcast.domain.Customer;

public class CustomerService {
	
	/**
	 * 保存客户
	 * @param c
	 */
	public void saveCustomer(Customer c){
		new CustomerDao().save(c);
	}
}
