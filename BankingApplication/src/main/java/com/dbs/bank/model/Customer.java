package com.dbs.bank.model;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Entity
@Table()
public class Customer implements Serializable{
	
	public long getCust_id() {
		return cust_id;
	}


	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cust_id;
	
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNum;
	
	@Column(unique = true)
	private String panId;
	
	private String gender;
	private LocalDate dateOfBirth;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> accounts = new HashSet<>();


	
	Customer(){}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public Customer(long cust_id, String password, String firstname, String lastname, String email,
			String phoneNum, String panId) {
		this.cust_id = cust_id;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNum = phoneNum;
		this.panId = panId;
		
	}


	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", phoneNum=" + phoneNum + ", panId=" + panId + ", accounts="
				+ accounts + "]";
	}

}
