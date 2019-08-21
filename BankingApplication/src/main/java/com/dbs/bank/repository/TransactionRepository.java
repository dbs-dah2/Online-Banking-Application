package com.dbs.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Transaction;

@CrossOrigin
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByFromAccount(Account id);
	List<Transaction> findByToAccount(Account id);
}