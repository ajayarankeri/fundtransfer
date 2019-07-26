package com.hcl.fundtransfer.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	//        logger.error("exception occured------"+ex.getBindingResult().getAllErrors());
		 List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        ErrorResponse error = new ErrorResponse("Validation Failed", details,Integer.toString(HttpStatus.BAD_REQUEST.value()));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	 
//	// @ExceptionHandler(NoTicketException.class)
//	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public final ResponseEntity<Object> handleAllExceptions(NoTicketException ex, WebRequest request) {
//	    
//		// logger.error("no ticket exception occured------"+ex.getMessage());
//		 List<String> details = new ArrayList<>();
//	        details.add(ex.getMessage());
//	        ErrorResponse error = new ErrorResponse("Server Error", details,Integer.toString(HttpStatus.BAD_REQUEST.value()));
//	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	    }
//	 
//	 @ExceptionHandler(NoSameUserIdException.class)
//	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public final ResponseEntity<Object> handleNoSameUserIdException(NoSameUserIdException ex, WebRequest request) {
//		 logger.error("No same user id exception occured------"+ex.getMessage());   
//		 List<String> details = new ArrayList<>();
//	        details.add(ex.getMessage());
//	        ErrorResponse error = new ErrorResponse("Server Error", details,Integer.toString(HttpStatus.BAD_REQUEST.value()));
//	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	    }
//	 
	 
	 @ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		// logger.error(" Resource not found exception occured------"+ex.getMessage());   
		 List<String> details = new ArrayList<>();
	        details.add(ex.getMessage());	        
	        ErrorResponse error = new ErrorResponse("Server Error", details,Integer.toString(HttpStatus.NOT_FOUND.value()));
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	    }
}
