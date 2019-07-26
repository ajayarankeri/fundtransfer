package com.hcl.fundtransfer.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.ConfirmPayeeDto;
import com.hcl.fundtransfer.dto.OtpGenrateDto;
import com.hcl.fundtransfer.dto.ResponseDto;
import com.hcl.fundtransfer.dto.TranjactionResponseDto;
import com.hcl.fundtransfer.dto.TransactionDto;
import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Payee;
import com.hcl.fundtransfer.entity.Transaction;
import com.hcl.fundtransfer.exception.ResourceNotFoundException;
import com.hcl.fundtransfer.repository.AccountRepository;
import com.hcl.fundtransfer.repository.CustomerRepository;
import com.hcl.fundtransfer.repository.PayeeRepository;
import com.hcl.fundtransfer.repository.TransactionRepository;


@Service
public class TransactionService {
	
	static Logger log = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	PayeeRepository payeeRepository;

	public Transaction makeTransaction(TransactionDto transactionDto,Account accountDetails) throws ResourceNotFoundException {
		Transaction transaction = null;
		
		// deposit
		if(transactionDto.getTransactionType().equalsIgnoreCase("CR")) {
			transaction=new Transaction();
			BeanUtils.copyProperties(transactionDto, transaction);
			Double updatedBalance=accountDetails.getBalance()+transactionDto.getTransactionAount();
			transaction.setTransactionDate(LocalDate.now());
			transaction.setCustomerId(accountDetails.getCustomerId());
			transaction.setBalance(updatedBalance);
			transaction.setTransactionType("CR");
			transactionRepository.save(transaction);
			
			accountDetails.setBalance(updatedBalance);
			accountRepository.save(accountDetails);	
		}
		// withdraw
		if(transactionDto.getTransactionType().equalsIgnoreCase("DR")) {			
		
			if(accountDetails.getBalance()<transactionDto.getTransactionAount()) {
					log.debug("Sorry, Your dont have sufficient balance for transaction ");
					throw new ResourceNotFoundException("Sorry, Your dont have sufficient balance for transaction!");
				}else {
					transaction=new Transaction();
					BeanUtils.copyProperties(transactionDto, transaction);
					Double updatedBalance=accountDetails.getBalance()-transactionDto.getTransactionAount();
					transaction.setTransactionDate(LocalDate.now());
					transaction.setCustomerId(accountDetails.getCustomerId());
					transaction.setBalance(updatedBalance);
					transaction.setTransactionType("DR");
					transactionRepository.save(transaction);
					
					accountDetails.setBalance(updatedBalance);
					accountRepository.save(accountDetails);
				}			
		}
		
		return transaction;		
	}

	public Account validateCustomerDetails(Long custId) throws ResourceNotFoundException {
		 Customer customer=customerRepository.findById(custId).orElseThrow(()-> new ResourceNotFoundException("Please check customer id, provided customer id does not exist!!"));
		 return accountRepository.findByCustomerId(customer);
	}
	

	public TranjactionResponseDto getAllTransaction(long customerId,String fromDate,String toDate) throws ResourceNotFoundException{
		Customer customerObject=customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer not found"));	
		List<Transaction> objTransactionList=transactionRepository.findByCustomerIdAndTransactionDateGreaterThanAndTransactionDateLessThan(customerObject, LocalDate.parse(fromDate), LocalDate.parse(toDate));
		double credit=objTransactionList.stream().filter(e->e.getTransactionType().equalsIgnoreCase("Cr")).mapToDouble(e->e.getTransactionAount()).sum();
		double debit=objTransactionList.stream().filter(e->e.getTransactionType().equalsIgnoreCase("Dr")).mapToDouble(e->e.getTransactionAount()).sum();
		double totalBalance=validateCustomerDetails(customerId).getBalance();	
		TranjactionResponseDto tranjactionResponseDto=new TranjactionResponseDto();
		tranjactionResponseDto.setTransactionResult(objTransactionList);
		tranjactionResponseDto.setTotalCredit(credit);
		tranjactionResponseDto.setTotalDebit(debit);
		tranjactionResponseDto.setTotalBalance(totalBalance);
		
		return tranjactionResponseDto;
	}
	
	
	
	public ResponseDto sendOtp(OtpGenrateDto otpGenrateDto) throws ResourceNotFoundException {
		Payee payeeObject=payeeRepository.findById(otpGenrateDto.getPayeeId()).orElseThrow(()->new ResourceNotFoundException("refrence id not found"));
		sendEmail(payeeObject.getReferenceId(),payeeObject.getPayeeId().getEmail());
		payeeObject.setOtp(random(6));
		payeeObject.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		payeeRepository.save(payeeObject);
		return new ResponseDto("sucess",200,"Otp sent sucessfully");
		
	}
	
	public ResponseDto confirmPayee(ConfirmPayeeDto confirmPayeeDto) throws ResourceNotFoundException {



		Payee payeeObject=payeeRepository.findById(confirmPayeeDto.getRefrenceId()).orElseThrow(()->new ResourceNotFoundException("refrence id not found"));



		ResponseDto responseDto;
		if(checkExpiredOtp(payeeObject.getReferenceId())) {
			responseDto=new ResponseDto("fail",402,"Otp expired");
			return responseDto;
		}
		else
		{ 
			if(confirmPayeeDto.getOtp().equals(payeeObject.getOtp()))
			{
				throw new ResourceNotFoundException("wrong otp entered");
			}
			payeeObject.setStatus(1);
			payeeRepository.save(payeeObject);
			return new ResponseDto("sucess",200,"payee confirmed sucessfully");
		}
		
	}
	
	public List<Transaction> getTransactionHistoryByCustomerId(Long customerId) throws ResourceNotFoundException
	{	
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("resource not found"));
		return transactionRepository.findTop10ByCustomerIdOrderByTransactionDateDesc(customer);
	}
	
	public String sendEmail(long payee,String email) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("OTP FOR PAYEE");
		mail.setText("Otp for Payee Reference Id"+payee +"  "+random(6));
		javaMailSender.send(mail);
		return "mailsent";
	}
	
	
	 public static String random(int size) {

	        StringBuilder generatedToken = new StringBuilder();
	        try {
	            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
	            // Generate 20 integers 0..20
	            for (int i = 0; i < size; i++) {
	                generatedToken.append(number.nextInt(9));
	            }
	        } catch (NoSuchAlgorithmException e) {
	        	log.debug("error thrown");
	            e.printStackTrace();
	        }

	        return generatedToken.toString();
	    }
	 
	 
	 public boolean checkExpiredOtp(long id) throws ResourceNotFoundException {		 
		 Payee objectPayee=payeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no payee found"));
		 return LocalDateTime.now().isAfter(objectPayee.getExpiryTime());	 
	 }
	 
	 

}
