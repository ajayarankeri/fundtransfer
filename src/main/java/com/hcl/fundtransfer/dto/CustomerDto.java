package com.hcl.fundtransfer.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDto {
	
	
	@NotNull
	private String fullName;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String mobNo;
	
	@NotNull
	private String email;
	
	@NotNull
	private String address;
	
	


}
