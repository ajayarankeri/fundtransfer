package com.hcl.fundtransfer.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository  customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public long registerUser(CustomerDto customerDto) {
		Customer customer=new Customer();
		
		BeanUtils.copyProperties(customerDto, customer);
		
		Customer customerObj =	customerRepository.save(customer);
		
		Account account =new Account();
		int number=(int)((Math.random()*9000000)+1000000);
		
		account.setCustomerId(customerObj);
		account.setAccountNumber(Long.valueOf(number));
		account.setAccountType("Saving");
		account.setBalance(Double.valueOf(20000));
		accountRepository.save(account);
		return  customerObj.getCustomerId();
	}
	
	

}
