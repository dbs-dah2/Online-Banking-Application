package com.dbs.bank.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tid;
	private long ammount;
	
	private LocalDateTime time; 


    @ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="from_account", nullable = false)
    private Account fromAccount;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="to_account", nullable = false)
    private Account toAccount;
    
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public long getAmmount() {
		return ammount;
	}
	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}
 
	public Transaction() {
		super();
	}

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public Transaction(long tid, long ammount, Account fromAccount, Account toAccount) {
		super();
		this.tid = tid;
		this.ammount = ammount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
}
