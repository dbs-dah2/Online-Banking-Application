package com.dbs.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dbs.bank.model.Branch;

@CrossOrigin
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{


}