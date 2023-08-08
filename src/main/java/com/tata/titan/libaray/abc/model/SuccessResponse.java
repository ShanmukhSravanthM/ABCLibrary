package com.tata.titan.libaray.abc.model;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Generic Success Response
 */

public class SuccessResponse {
	
	public String message;
	public String responseCode = "200";
	
	public SuccessResponse() {
		
	}
	
	public SuccessResponse(String message, String responseCode) {
		this.message = message;
		if(responseCode!=null) {
			this.responseCode = responseCode;
		}
	}

}
