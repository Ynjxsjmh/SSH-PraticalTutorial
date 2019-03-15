package com.atcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atcast.dao.CustomerDao;
import com.atcast.domain.Customer;

/**
 * 客户的业务层
 * @author Administrator
 */
@Transactional // 表示下面类的所有方法都纳入事务管理。也可以放到方法前面，表示只针对方法进行事务管理
// 单单写一个注解是不能的，还要在 Spring 配置文件中进行配置
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;   // null
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		System.out.println("业务层：保存客户...");
		customerDao.save(customer);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public Customer getById(Long id) {
		return customerDao.getById(id);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
	public List<Customer> findAllByQBC() {
		return customerDao.findAllByQBC();
	}

	public Customer loadById(long id) {
		return customerDao.loadById(id);
	}

}
