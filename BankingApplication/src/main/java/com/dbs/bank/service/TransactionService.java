package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;

public interface TransactionService {
	
	List<Transaction> listAll();

	Transaction saveTransaction(Transaction transaction);

	Transaction findById(long id);

	List<Transaction> findByFromAccount(Account id);
	
	List<Transaction> findByToAccount(Account id);

}