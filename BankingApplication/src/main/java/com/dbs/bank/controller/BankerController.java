package com.dbs.bank.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dbs.bank.model.Banker;
import com.dbs.bank.service.BankerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BankerController {

	@Autowired
    private BankerService bankerService;

    @GetMapping("/banker")
    public List<Banker> listAll(){
        System.out.println("Inside the list all method....");
        return this.bankerService.listAll();
    }

    @GetMapping("/banker/{id}")
    public Banker findEmployeeById(@PathVariable("id") long id){
        return this.bankerService.findById(id);
    }

    @GetMapping("/banker/{name}/{pwd}")
    public Optional<Banker> findEmployeeByNameAndPassword(@PathVariable("name") String name,@PathVariable("pwd") String pwd){
        return this.bankerService.findByAdminNameAndPassword(name,pwd);
    }
}