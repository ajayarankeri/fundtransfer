package com.hcl.fundtransfer.entity;

import java.time.LocalDate;

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
@Table(name="transaction")
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trans_id")
	@JsonIgnore
	private Long transactionId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_amount")
	private Double transactionAount;

	
	@Column(name="transaction_date")
	private LocalDate transactionDate;
	
	@Column(name="balance")
	private Double balance;
	
	

}
