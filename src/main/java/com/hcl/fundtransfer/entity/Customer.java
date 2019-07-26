package com.hcl.fundtransfer.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;	
	
	@Column(name="full_name")
	@JsonIgnore
	private String fullName;
	
	@Column(name="birth_date")
	@JsonIgnore
	private LocalDate birthDate;
	
	@Column(name="gender")
	@JsonIgnore
	private String gender;
	
	@Column(name="mobile_no")
	@JsonIgnore
	private String mobileNo;
	
	@Column(name="email")
	@JsonIgnore
	private String email;
	
	@Column(name="address")
	@JsonIgnore
	private String address;
	
}
