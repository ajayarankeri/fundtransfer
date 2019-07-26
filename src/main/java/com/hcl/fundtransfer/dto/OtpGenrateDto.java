package com.hcl.fundtransfer.dto;

import lombok.Data;

@Data
public class OtpGenrateDto {
	private long customerId;
	private long payeeId;
}
