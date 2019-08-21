package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import com.dbs.bank.model.Branch;



public interface BranchService {
	Optional<Branch> findByIfsc(long id);
	Branch saveBranch(Branch branch);
	List<Branch> listAll();
}
