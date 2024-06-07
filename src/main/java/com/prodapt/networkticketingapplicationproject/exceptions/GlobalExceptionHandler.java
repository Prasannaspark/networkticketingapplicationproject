package com.prodapt.networkticketingapplicationproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
/**
* Global exception handler for handling various exceptions throughout the application.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {
	
 @ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<String> handleRoleNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
 
 @ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<String> handleTicketNotFoundException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}