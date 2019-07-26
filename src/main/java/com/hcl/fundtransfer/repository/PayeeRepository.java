package com.hcl.fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.fundtransfer.entity.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Long> {

}
