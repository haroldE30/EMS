package com.adeptsource.ems.exception;

public class TransactionProcessException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionProcessException(String message) {
		super(message);
	}
	
	public TransactionProcessException(String message, Throwable t) {
		super(message, t);
	}
	
}
