package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Account;
import com.hcl.fundtransfer.entity.Customer;



@Repository
public interface AccountRepository  extends JpaRepository<Account, Long>{

	Account findByCustomerId(Customer customer);

}
