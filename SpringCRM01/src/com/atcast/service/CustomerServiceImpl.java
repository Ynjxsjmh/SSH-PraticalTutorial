package com.atcast.service;

import com.atcast.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public void save() {
		System.out.println("业务层：保存客户...");
		customerDao.save();
	}

}
