package com.dbs.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.bank.model.Branch;
import com.dbs.bank.repository.BranchRepository;



@Service
public class BranchServiceImpl implements BranchService{
	@Autowired
	private BranchRepository branchRepository;
	
    @Autowired
	public BranchServiceImpl(BranchRepository branchRepository) {
		super();
		this.branchRepository = branchRepository;
	}
    
	

	@Override
	public Optional<Branch> findByIfsc(long id) {
		return this.branchRepository.findById(id);
	}

	@Override
	public List<Branch> listAll() {
		return this.branchRepository.findAll();
	}


	@Override
	public Branch saveBranch(Branch branch) {
		return this.branchRepository.save(branch);
	}

}
