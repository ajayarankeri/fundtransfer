package com.hcl.fundtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.exception.PayeeAndCustomerCannotBeSameException;
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
	public ResponseEntity<Object> confirmedPayee(@PathVariable("refrenceId") long refrenceId) throws ResourceNotFoundException{ 		
		   return new ResponseEntity<>(transactionService.confirmPayee(refrenceId),HttpStatus.OK);  
		}
	

	
	@PostMapping("/payee/add")
	public ResponseEntity<?> addPayee(@RequestParam("customer_id") Long customerId ,@RequestParam("payee_id") Long payeeId) throws ResourceNotFoundException, PayeeAndCustomerCannotBeSameException{
		if(null==customerId) {
			throw new ResourceNotFoundException("please provide customer id");
		}
		else if (null==payeeId) {
			throw new ResourceNotFoundException("please provide customer id");
		}
		payeeService.addPayee(customerId, payeeId);
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}


}

