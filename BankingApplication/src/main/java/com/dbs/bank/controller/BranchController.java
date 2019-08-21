package com.dbs.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.bank.model.Branch;
import com.dbs.bank.service.BranchService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BranchController {

	@Autowired
	BranchService branchService;
	
	@GetMapping("/branch/{id}")
	public Optional<Branch> getBranchById(@PathVariable("id") Long id) {
		return branchService.findByIfsc(id);
	}
	
	@GetMapping("/branch")
	public List<Branch> getBranches() {
		return branchService.listAll();
	}
	
	@PostMapping("/branch")
	public Branch createCustomer(@Valid @RequestBody Branch branch) {
		return branchService.saveBranch(branch);
		
	}
	
	
}
