package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class CustomerAlreadyExistException extends RuntimeException{

	
	public CustomerAlreadyExistException(String msg)
	{
		super(msg);
	}
}
