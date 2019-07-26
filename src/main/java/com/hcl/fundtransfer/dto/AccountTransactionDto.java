package com.hcl.fundtransfer.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountTransactionDto {
	
    @Min(value=1,message = "please enter customer id")	
	private long customerId;
	@NotBlank(message = "please  enter from date")
	private String fromDate;
	@NotBlank(message = "please  enter to date")
	private String toDate;

}
