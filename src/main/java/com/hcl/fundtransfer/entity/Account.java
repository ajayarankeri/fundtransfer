package com.hcl.fundtransfer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="account")
@Data
public class Account {

	
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private long id;
	
	@Column(name="account_number")
	@JsonIgnore
	private Long accountNumber;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;
	
	@JoinColumn(name="balance")
	private Double balance;
	
	@Column(name="account_type")
	private String accountType;
	

}
