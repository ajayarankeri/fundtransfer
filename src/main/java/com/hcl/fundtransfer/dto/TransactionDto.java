package com.hcl.fundtransfer.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransactionDto {
	
	@Min(value = 1,message = "Please enter customer id")
	private Long customerId;
	
	@NotNull(message = "Description should not be null")
	@NotEmpty(message = "Description should not be empty")
	private String description;
	
	@NotNull(message = "Transaction Type should not be null, Should be (CR/DR)")
	@NotEmpty(message = "Transaction Type should not be empty, Should be (CR/DR)")
	private String transactionType;
	
	@Min(value = 1,message = "Please enter valid transaction Amount")
	private Double transactionAount;
	
	
	
}
