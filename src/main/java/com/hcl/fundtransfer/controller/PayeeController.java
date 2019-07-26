package com.hcl.fundtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.service.PayeeService;

@RestController
@RequestMapping("")
public class PayeeController {
	
	@Autowired
	PayeeService payeeService;
	
	@PostMapping("/payee/{customer_id}")
	public void getInterestUsers(@PathVariable("customer_id") long customer_id) throws ResourceNotFoundException{ 		
	  // return new ResponseEntity<>(userOperationService.getConfirmedPayeeList(id,type),HttpStatus.OK);  
	}

}

