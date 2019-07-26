package com.hcl.fundtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.OtpGenrateDto;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.service.TransactionService;

@RestController
@RequestMapping("/otp")
public class OtpServiceController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/genrate")
	public ResponseEntity<Object> genrateOtp(@RequestBody OtpGenrateDto otpGenrateDto) throws ResourceNotFoundException{
		
		return new  ResponseEntity<>(transactionService.sendOtp(otpGenrateDto),HttpStatus.OK);
	}

}
