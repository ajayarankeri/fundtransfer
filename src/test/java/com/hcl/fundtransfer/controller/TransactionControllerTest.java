package com.hcl.fundtransfer.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.fundtransfer.dto.AccountTransactionDto;
import com.hcl.fundtransfer.dto.OtpGenrateDto;
import com.hcl.fundtransfer.dto.TranjactionResponseDto;
import com.hcl.fundtransfer.dto.TransactionDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.service.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

	@InjectMocks
	AccountController accountController;
	
	@InjectMocks
	OtpServiceController otpServiceController;
	
	@Mock
	TransactionService transactionService;
	
	AccountTransactionDto accAccountTransactionDto;
	Customer customer;
	Customer payeeObj;
	Account account;
	
	TransactionDto transactionDto;
	Transaction transaction1,transaction2;
	List<Transaction> transactionList;
	
    TranjactionResponseDto tranjactionResponseDto;
    
    OtpGenrateDto otpGenrateDto;
	
    @Before
	public void setDto() {
		
		customer=new Customer();
		customer.setAddress("Pune");
		customer.setBirthDate(LocalDate.parse("1990-08-09"));
		customer.setCustomerId(Long.valueOf(1));
		customer.setEmail("vinyaak@gmail.com");
		customer.setFullName("vinayak");
		customer.setGender("M");
		customer.setMobileNo("123456789");
		
		payeeObj=new Customer();
		payeeObj.setAddress("Pune");
		payeeObj.setBirthDate(LocalDate.parse("1990-08-09"));
		payeeObj.setCustomerId(Long.valueOf(2));
		payeeObj.setEmail("vinyaakdesaimca@gmail.com");
		payeeObj.setFullName("vinayak");
		payeeObj.setGender("M");
		payeeObj.setMobileNo("123456789");
		
		
		
		account=new Account();
		account.setAccountNumber(Long.valueOf(123456));
		account.setAccountType("saving");
		account.setBalance(Double.valueOf(2000));
		account.setCustomerId(customer);
		
		accAccountTransactionDto=new AccountTransactionDto();
		accAccountTransactionDto.setCustomerId(1);
		accAccountTransactionDto.setFromDate("2019-07-20");
		accAccountTransactionDto.setToDate("2019-07-30");
		
		transactionDto=new TransactionDto();
		transactionDto.setCustomerId(1l);
		transactionDto.setDescription("desc");
		transactionDto.setTransactionAount(Double.valueOf(2000));
		transactionDto.setTransactionType("Cr");
		
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
		
		tranjactionResponseDto=new TranjactionResponseDto();
		tranjactionResponseDto.setTotalBalance(1200);
		tranjactionResponseDto.setTotalCredit(2000);
		tranjactionResponseDto.setTotalDebit(300);
		tranjactionResponseDto.setTransactionResult(transactionList);
		
		otpGenrateDto=new OtpGenrateDto();
		otpGenrateDto.setCustomerId(1);
		otpGenrateDto.setPayeeId(1);

	}
	
	
	@Test
	public void  completeTranjactionTest() throws ResourceNotFoundException {
		Mockito.when(transactionService.getTransactionHistoryByCustomerId(1l)).thenReturn(transactionList);
		assertNotNull(accountController.getTransactionHistory(1l));
		
	}
	
	@Test
	public void sendOtpTest() throws ResourceNotFoundException {
		
		assertNotNull(otpServiceController.genrateOtp(otpGenrateDto));
	}
	
	@Test
	public void  completeTranjactionAllTest() throws ResourceNotFoundException {
		Mockito.when(transactionService.getAllTransaction(1,"2019-05-09","2019-05-10")).thenReturn(tranjactionResponseDto);
		assertNotNull(accountController.getAllTransaction(accAccountTransactionDto));
	}
}
