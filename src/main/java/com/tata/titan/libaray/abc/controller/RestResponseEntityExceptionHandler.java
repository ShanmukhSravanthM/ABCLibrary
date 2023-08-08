package com.tata.titan.libaray.abc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tata.titan.libaray.abc.exception.ABCLibraryInvalidInputException;
import com.tata.titan.libaray.abc.model.ErrorResponse;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Global Exception Handler
 * 
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ABCLibraryInvalidInputException.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), "500");
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> exceptionHandle(RuntimeException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse("Some thing went wrong, we are working on it. Please try again!", "500");
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
} 