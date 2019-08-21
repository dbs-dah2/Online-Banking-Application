package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import com.dbs.bank.model.Banker;

public interface BankerService {
	
	List<Banker> listAll();

    Banker findById(long empId);

    Optional<Banker> findByAdminNameAndPassword(String name,String pwd);
}
