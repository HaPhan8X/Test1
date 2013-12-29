package com.tvo.puzzle.exception;

public class NotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 386719109043477137L;

	public NotExistsException() {
		super();
	}

	public NotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistsException(String message) {
		super(message);
	}

	public NotExistsException(Throwable cause) {
		super(cause);
	}

}
