package com.prodapt.networkticketingapplicationproject.exceptions;

public class TicketNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException() {
		super();
	}

	public TicketNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TicketNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TicketNotFoundException(String message) {
		super(message);
	}

	public TicketNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
