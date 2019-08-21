package com.dbs.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dbs.bank.model.Customer;
import com.dbs.bank.service.CustomerService;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.listAll();
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
		
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) {
		return customerService.findById(id);
	}
	
	@PostMapping("/customer")
	public Optional<Customer> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
		return customerService.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
		
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id,
			@Valid @RequestBody Customer customerDetails) {
		return customerService.updateCustomer(id, customerDetails);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
		return customerService.deleteCustomer(id);
	}

}
