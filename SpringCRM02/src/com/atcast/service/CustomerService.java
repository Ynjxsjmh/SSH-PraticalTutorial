package com.atcast.service;

import java.util.List;

import com.atcast.domain.Customer;

public interface CustomerService {
	
	public void save(Customer customer);
	
	public void update(Customer customer);
	
	public Customer getById(Long id);
	
	public List<Customer> findAll();
	
	public List<Customer> findAllByQBC();

	public Customer loadById(long id);
	
}
