package com.hcl.fundtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.ConfirmPayeeDto;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.service.PayeeService;
import com.hcl.fundtransfer.service.TransactionService;

@RestController
@RequestMapping("")
public class PayeeController {
	
	@Autowired
	PayeeService payeeService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/payee/{customerId}") 
	public ResponseEntity<Object> getInterestUsers(@PathVariable("customerIid") long customer_id) throws ResourceNotFoundException{ 		
	   return new ResponseEntity<>(payeeService.getConfirmedPayeeList(customer_id),HttpStatus.OK);  
	}
	
	@PostMapping("payee/confirm/{refrenceId}")
	public ResponseEntity<Object> confirmedPayee(@RequestBody ConfirmPayeeDto confirmPayeeDto) throws ResourceNotFoundException{ 		
		   return new ResponseEntity<>(transactionService.confirmPayee(confirmPayeeDto),HttpStatus.OK);  
		}
	

}

