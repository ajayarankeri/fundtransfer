package com.hcl.fundtransfer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="payee")
public class Payee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reference_id")
	private Long referenceId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customerId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="payee_id")
	private Customer payeeId;
	
	@Column(name="status")
	private int status;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="expiry_time")
	private LocalDateTime expiryTime;
}
	