package com.hcl.fundtransfer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	public List<Transaction> findByCustomerIdAndTransactionDateGreaterThanAndTransactionDateLessThan(Customer customer,LocalDate fromDate,LocalDate toDate);
	
	public List<Transaction> findTop10ByCustomerIdOrderByTransactionDateDesc(Customer customer);

}
