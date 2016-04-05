package com.lijojacob.mls.exception;

import lombok.Data;

import org.springframework.validation.Errors;

public @Data class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
    
    public InvalidRequestException(String message) {
        super(message);
    }

	
}
