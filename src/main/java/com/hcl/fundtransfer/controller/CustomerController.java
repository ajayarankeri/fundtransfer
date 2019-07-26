package com.hcl.fundtransfer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.CustomerDto;
import com.hcl.fundtransfer.service.CustomerService;


@RestController
@RequestMapping("/user")
public class CustomerController {
	
	
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("")
	public ResponseEntity<String> addUser(@Valid @RequestBody CustomerDto customerDto) {
		
		return new ResponseEntity<>("Hello , You are added successfully. Your Customer Id is  "+customerService.registerUser(customerDto)+"",HttpStatus.OK);
		
	}
	

}
