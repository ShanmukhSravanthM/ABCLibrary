package com.tata.titan.libaray.abc.exception;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Custom Exception handler
 */
public class ABCLibraryInvalidInputException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	//Passing actual message
	public ABCLibraryInvalidInputException(String errorMessage) {
		super(errorMessage); 
	}

}
