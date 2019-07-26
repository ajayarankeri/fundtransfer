package com.hcl.fundtransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5224943416382684312L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
