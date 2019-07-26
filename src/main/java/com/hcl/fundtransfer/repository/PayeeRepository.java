package com.hcl.fundtransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.Customer;
import com.hcl.fundtransfer.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long>{

	@Query("select p.payeeId from Payee p where p.customerId=:customerId and p.status=1 ")

	List<Customer> findByCustomerId(@Param("customerId")Customer customerDetails);

	Object findByPayeeIdAndCustomerId(Customer payee, Customer customerDetails);


}
