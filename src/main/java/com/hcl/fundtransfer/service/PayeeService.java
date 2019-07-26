package com.hcl.fundtransfer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.ResponseDto;
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
	
	public ResponseDto addPayee(Long customerId, Long payeeId) throws ResourceNotFoundException, PayeeAndCustomerCannotBeSameException { 
		Payee newPayee = new Payee();
		
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer not found"));
		
		Customer payee=customerRepository.findById(payeeId).orElseThrow(()->new ResourceNotFoundException("payee is not a valid customer. Please register payee as customer."));
		
		
		 if(payee.getCustomerId().equals(customer.getCustomerId())) {
			throw new PayeeAndCustomerCannotBeSameException("Payee And Customer Cannot Be Same.");
		}
		else if(null!=payeeRepository.findByPayeeIdAndCustomerId(payee,customer)) {
			throw new PayeeAndCustomerCannotBeSameException("Payee Already added.");
		}
		else {
			newPayee.setCustomerId(customer);
			newPayee.setPayeeId(payee);
			newPayee.setOtp("");
			newPayee.setStatus(0);
			newPayee.setExpiryTime(LocalDateTime.now());
			
			payeeRepository.save(newPayee);
			
			return new ResponseDto("success",200,"null");
		}
	}

}
