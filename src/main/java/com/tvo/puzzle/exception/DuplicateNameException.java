package com.tvo.puzzle.exception;

public class DuplicateNameException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720768616500806516L;

	public DuplicateNameException()
	{
		super();
	}

	public DuplicateNameException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DuplicateNameException(String message)
	{
		super(message);
	}

	public DuplicateNameException(Throwable cause)
	{
		super(cause);
	}
}
