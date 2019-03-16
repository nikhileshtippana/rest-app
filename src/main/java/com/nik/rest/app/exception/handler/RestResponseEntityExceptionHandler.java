package com.nik.rest.app.exception.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nik.rest.app.exception.ResourceNotFoundException;
import com.nik.rest.app.exception.ValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ValidationException.class})
	protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
		
		Map<String, Object> body = new HashMap<>();
		body.put("reason", "Input Validation Failed");
		body.put("errors", ex.getErrors());
		
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		
		Map<String, Object> body = new HashMap<>();
		body.put("reason", "Resource Not Found");
		body.put("errors", Arrays.asList(ex.getMessage()));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
}
