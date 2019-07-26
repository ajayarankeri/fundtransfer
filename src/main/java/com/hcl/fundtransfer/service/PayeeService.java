package com.hcl.fundtransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.entity.Customer;

import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.exception.PayeeAndCustomerCannotBeSameException;

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
	
	public void addPayee(Long customerId, Long payeeId) throws ResourceNotFoundException, PayeeAndCustomerCannotBeSameException { 
		Payee newPayee = new Payee();
		Customer customer=customerRepository.findByCustomerId(customerId);
		if(null==customer) {
			throw new ResourceNotFoundException("customer not found");
		}
		Customer payee=customerRepository.findByCustomerId(payeeId);
		if (null==payee) {
			throw new ResourceNotFoundException("payee is not a valid customer. Please register payee as customer.");
		}else if(payee.getCustomerId().equals(customer.getCustomerId())) {
			throw new PayeeAndCustomerCannotBeSameException("Payee And Customer Cannot Be Same.");
		}
		else {
			newPayee.setCustomerId(customer);
			newPayee.setPayeeId(payee);
			newPayee.setOtp(null);
			newPayee.setStatus(0);
			
			payeeRepository.save(newPayee);
		}
	}

}
