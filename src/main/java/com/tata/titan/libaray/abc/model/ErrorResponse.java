package com.tata.titan.libaray.abc.model;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Generic Error Response
 */

public class ErrorResponse {
	
	public String message;
	public String responseCode = "200";
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String message, String responseCode) {
		this.message = message;
		if(responseCode!=null) {
			this.responseCode = responseCode;
		}
	}

}
