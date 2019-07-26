package com.hcl.fundtransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class PayeeAndCustomerCannotBeSameException extends Exception{

private static final long serialVersionUID = -5224943416382123456L;
	
	public  PayeeAndCustomerCannotBeSameException(String message) {
		super(message);
	}


}
