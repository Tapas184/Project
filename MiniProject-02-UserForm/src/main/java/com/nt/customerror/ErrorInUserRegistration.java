package com.nt.customerror;

public class ErrorInUserRegistration extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;

	public ErrorInUserRegistration(String message) {
		super(message);
	}

}
