package com.hcl.fundtransfer.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.fundtransfer.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        ErrorResponse error = new ErrorResponse("Validation Failed", details,Integer.toString(HttpStatus.BAD_REQUEST.value()));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {   
		 List<String> details = new ArrayList<>();
	        details.add(ex.getMessage());	        
	        ErrorResponse error = new ErrorResponse("Server Error", details,Integer.toString(HttpStatus.NOT_FOUND.value()));
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(PayeeAndCustomerCannotBeSameException.class)
		@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
		    public final ResponseEntity<ErrorResponse> handlePayeeAndCustomerCannotBeSameException(PayeeAndCustomerCannotBeSameException ex, WebRequest request) {
			 List<String> details = new ArrayList<>();
		        details.add(ex.getMessage());	        
		        ErrorResponse error = new ErrorResponse("Server Error", details,Integer.toString(HttpStatus.NOT_ACCEPTABLE.value()));
		        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
		    }
}
