package com.hcl.fundtransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.repository.CustomerRepository;
import com.hcl.fundtransfer.repository.PayeeRepository;

@Service
public class PayeeService {
	
	@Autowired
	PayeeRepository payeeRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getConfirmedPayeeList(long customerId) throws ResourceNotFoundException {		
		Customer customer=customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Please check customer id, provided customer id does not exist!!"));
		 return payeeRepository.findByCustomerId(customer);
	}

}
