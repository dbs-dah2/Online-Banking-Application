package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dbs.bank.model.Customer;
import com.dbs.bank.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    @Override
    @Transactional
    public List<Customer> listAll(){
    	return customerRepository.findAll();
    }
    
    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
    	if((customerRepository.findByPanId(customer.getPanId())==null)
    			&& (customerRepository.findByEmail(customer.getEmail())==null)
    					&& (customerRepository.findByPhoneNum(customer.getPhoneNum())==null))
    	{
    	return customerRepository.save(customer);
    	}
    	return null;
    }
    
    @Override
    @Transactional
    public Customer findById(long id) {
    	return this.customerRepository.findById(id).get();
    }
    
    @Override
    @Transactional
    public ResponseEntity<Customer> updateCustomer(long id,Customer customerDetails) {
    	Customer customer = customerRepository.findById(id).get();
    	
    	customer.setPassword(customerDetails.getPassword());
    	customer.setFirstname(customerDetails.getFirstname());
    	customer.setLastname(customerDetails.getLastname());
    	customer.setEmail(customerDetails.getEmail());
    	customer.setPhoneNum(customerDetails.getPhoneNum());
    	customer.setPanId(customerDetails.getPanId());
    	
    	return ResponseEntity.ok().body(customer);
    	
    }
    
    @Override
    @Transactional
	public Customer findByPanId(String panId) {
		// TODO Auto-generated method stub
		return customerRepository.findByPanId(panId);
	}

	@Override
	@Transactional
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public Customer findByPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		return customerRepository.findByPhoneNum(phoneNum);
	}
    
    @Override
    @Transactional
    public ResponseEntity<?> deleteCustomer(long id){
    	Customer customer = customerRepository.findById(id).get();
    	customerRepository.delete(customer);
    	return ResponseEntity.ok().build();
    }
    
    @Override
    @Transactional
	public Optional<Customer> findByEmailAndPassword(String email, String password) {
		return this.customerRepository.findByEmailAndPassword(email, password);
	}
}
