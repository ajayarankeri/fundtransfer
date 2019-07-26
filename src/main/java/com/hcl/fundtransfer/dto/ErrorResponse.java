package com.hcl.fundtransfer.dto;

import java.util.List;

public class ErrorResponse {
	

    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
    
    private String statusCode;
    
    public ErrorResponse(String message, List<String> details,String statusCode) {
        super();
        this.message = message;
        this.details = details;
        this.statusCode=statusCode;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
	public void setStatusCode(String statusCode) {
		this.statusCode=statusCode;
	}
	
	public String getStatusCode() {
		return this.statusCode;
	}
}
