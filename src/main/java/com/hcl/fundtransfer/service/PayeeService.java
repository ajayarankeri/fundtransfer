package com.hcl.fundtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.repository.PayeeRepository;

@Service
public class PayeeService {
	
	@Autowired
	PayeeRepository payeeRepository;

	public Object getConfirmedPayeeList(long customer_id) {
		return payeeRepository.findByCustomerId(customer_id);		
	}

}
