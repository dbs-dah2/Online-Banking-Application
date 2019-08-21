package com.dbs.bank.model;


import javax.persistence.*;



import lombok.Data;


@Entity
@Table
public class Banker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String adminName;
	private String password;
	
	public Banker() {}
	
	public long getId() {
		return id;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "Banker [id=" + id + ", adminName=" + adminName + ", password=" + password + "]";
	}
	
	public Banker(String adminName,String password) {
		super();
		this.adminName=adminName;
		this.password = password;
	}
}
