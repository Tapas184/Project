package com.nt.customerror;

public class ProblemInMailSend extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1006L;

	public ProblemInMailSend(String message) {
		super(message);
	}


}
