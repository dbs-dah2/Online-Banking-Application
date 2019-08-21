package com.dbs.bank.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dbs.bank.model.Account;
import com.dbs.bank.model.Customer;

@CrossOrigin
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	List<Account> findByCustomer(Customer id);
	Account findByAccountType(Account accountType);
}