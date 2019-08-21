package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dbs.bank.model.Customer;

public interface CustomerService {

	List<Customer> listAll();

	Customer saveCustomer(Customer customer);

	Customer findById(long id);

	ResponseEntity<Customer> updateCustomer(long id, Customer customerDetails);

	ResponseEntity<?> deleteCustomer(long id);
	
	Customer findByPanId(String panId);
	
	Optional<Customer> findByEmailAndPassword(String email,String password);
	
	public Customer findByEmail(String email);
	
	public Customer findByPhoneNum(String phoneNum);

}
