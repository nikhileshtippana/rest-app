package com.nik.rest.app.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends ServiceException {

	private static final long serialVersionUID = 3186583176442949547L;

	private List<String> errors;
	
	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, List<String> errors) {
		super(message);
		
		this.errors = errors;
	}
	
	public List<String> getErrors() {
		
		if (errors == null) {
			errors = new ArrayList<>();
		}
		
		return errors;
	}
}
