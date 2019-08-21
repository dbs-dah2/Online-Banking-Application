package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;
import com.dbs.bank.repository.AccountRepository;
import com.dbs.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository=transactionRepository;
	}
	
//	@Autowired
//	private AccountRepository accountRepository;
	
//	@Autowired
//	private AccountService accountService;
	
	
	@Override
	@Transactional
	public List<Transaction> listAll() {
		return this.transactionRepository.findAll();
	}

	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
		
		double fromAccountBalance = transaction.getFromAccount().getBalance();
		double toAccountBalance = transaction.getToAccount().getBalance();
		
		System.out.println("Current Transaction : " + transaction.getToAccount().getAccountType());
		System.out.println("From account Balance : " + transaction.getFromAccount().getBalance());
		System.out.println("To account Balance : " + transaction.getToAccount().getBalance());
		System.out.println("After transaction....");
		
		if((fromAccountBalance - transaction.getAmmount()) < 5000) {
			System.out.println("Transaction cannot be done... Your account balance will be short of $5,000 with this transaction");
			return null;
		}
		else {
			fromAccountBalance = fromAccountBalance - transaction.getAmmount();
			toAccountBalance = toAccountBalance + transaction.getAmmount();
			
			transaction.getFromAccount().setBalance(fromAccountBalance);
			transaction.getToAccount().setBalance(toAccountBalance);
		}
		System.out.println("From account Balance : " + transaction.getFromAccount().getBalance());
		System.out.println("To account Balance : " + transaction.getToAccount().getBalance());
		return transactionRepository.save(transaction);
	}



	@Override
	@Transactional
	public List<Transaction> findByFromAccount(Account id) {
		return this.transactionRepository.findByFromAccount(id);
	}

	@Override
	@Transactional
	public Transaction findById(long id) {
		return this.transactionRepository.findById(id).get();
	}

	@Override
	@Transactional
	public List<Transaction> findByToAccount(Account id) {
		return this.transactionRepository.findByToAccount(id);
		
	}

}