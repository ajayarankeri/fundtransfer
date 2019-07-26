package com.hcl.fundtransfer.service;


import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	TransactionService transactionService;
	
	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	Customer customer;
	Account account;
	Transaction transaction1,transaction2;
	List<Transaction> transactionList;
	
	@Before
	public void setMockdata() {
		customer=new Customer();
		customer.setAddress("Pune");
		customer.setBirthDate(LocalDate.parse("1990-08-09"));
		customer.setCustomerId(Long.valueOf(1));
		customer.setEmail("vinyaak@gmail.com");
		customer.setFullName("vinayak");
		customer.setGender("M");
		customer.setMobileNo("123456789");
		
		account=new Account();
		account.setAccountNumber(Long.valueOf(123456));
		account.setAccountType("saving");
		account.setBalance(Double.valueOf(2000));
		account.setCustomerId(customer);
		
		transaction1=new Transaction();
		transaction1.setTransactionId(Long.valueOf(1));
		transaction1.setCustomerId(customer);
		transaction1.setDescription("test");
		transaction1.setTransactionAount(Double.valueOf(2000));
		transaction1.setTransactionDate(LocalDate.parse("2019-07-29"));
		transaction1.setBalance(Double.valueOf(1000));
		
		transaction2=new Transaction();
		transaction2.setTransactionId(Long.valueOf(2));
		transaction2.setCustomerId(customer);
		transaction2.setDescription("test");
		transaction2.setTransactionAount(Double.valueOf(2000));
		transaction2.setTransactionDate(LocalDate.parse("2019-07-29"));
		transaction2.setBalance(Double.valueOf(1000));
		
		transactionList=new ArrayList<Transaction>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);	
	}
	
	@Test
	public void TransactionlistTest() throws ResourceNotFoundException {
	   Mockito.when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
	   Mockito.when(transactionRepository.findByCustomerIdAndTransactionDateGreaterThanAndTransactionDateLessThan(customer, LocalDate.parse("2019-07-23"), LocalDate.parse("2019-07-30"))).thenReturn(transactionList);
	   Mockito.when(accountRepository.findByCustomerId(customer)).thenReturn(account);
		assertNotNull(transactionService.getAllTransaction(Long.valueOf(1), "2019-07-29", "2019-07-29"));
	}
	
	
}
