package com.atcast.dao;

import java.util.List;

import com.atcast.domain.Customer;

public interface CustomerDao {
	
	public void save(Customer customer);

	public void update(Customer customer);

	public Customer getById(Long id);

	public List<Customer> findAll();

	/* Hibernate 官方有几种查询语言
	 * HQL 是一种
	 * QBC 也是一种，更适合多条件查询
	 */
	public List<Customer> findAllByQBC();

	// 通过延迟加载的方式来做
	public Customer loadById(long id);
	// 本例不考虑后两个
}
