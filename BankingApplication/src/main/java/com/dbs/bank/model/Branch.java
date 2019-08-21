package com.dbs.bank.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"branchName"})})
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long branchIFSC;
	
	private String branchName;
	private String City;
	private String State;
	private String pincode;
	
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Account> accounts = new HashSet<>();
	
	public Branch() {
		super();
	}
	public long getBranchIFSC() {
		return branchIFSC;
	}
	public void setBranchIFSC(long branchIFSC) {
		this.branchIFSC = branchIFSC;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPincode() {
		return pincode;
	}
	@Override
	public String toString() {
		return "Branch [branchIFSC=" + branchIFSC + ", branchName=" + branchName + ", City=" + City + ", State=" + State
				+ ", pincode=" + pincode + "]";
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Branch(long branchIFSC, String branchName, String city, String state, String pincode) {
		super();
		this.branchIFSC = branchIFSC;
		this.branchName = branchName;
		City = city;
		State = state;
		this.pincode = pincode;
	}

	
}
