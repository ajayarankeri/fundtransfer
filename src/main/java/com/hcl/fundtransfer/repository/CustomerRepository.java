package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Customer;

	
	

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findByCustomerId(Long customerId);
	
	

	
}
