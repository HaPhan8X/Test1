package com.tvo.puzzle.exception;

public class DataErrorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 386719109043477137L;

	public DataErrorException() {
		super();
	}

	public DataErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataErrorException(String message) {
		super(message);
	}

	public DataErrorException(Throwable cause) {
		super(cause);
	}

}
