package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Customer;


public interface AccountService {
	List<Account> listAll();

	Account saveAccount(Customer id,Account account);

	Account findById(long id);

	ResponseEntity<Account> updateAccount(long id, Account accountDetails);

	ResponseEntity<?> deleteAccount(long id);
	
	List<Account> findByCustomer(Customer id);
	
	Account findByAccountType(Account accountType);

}