package com.gfmotta.desafiotdd.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gfmotta.desafiotdd.services.exceptions.DatabaseException;
import com.gfmotta.desafiotdd.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError();
		error.setTimespan(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException exception, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error = new StandardError();
		error.setTimespan(Instant.now());
		error.setStatus(status.value());
		error.setError("Database exception");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
}
