package com.hcl.fundtransfer.dto;

import lombok.Data;

@Data
public class TranjactionResponseDto {
	
	private Object transactionResult;
	private double totalCredit;
	private double totalDebit;
	private double totalBalance;

}
