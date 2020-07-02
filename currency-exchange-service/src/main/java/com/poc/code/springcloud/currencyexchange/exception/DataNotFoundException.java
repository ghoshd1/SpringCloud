package com.poc.code.springcloud.currencyexchange.exception;

public class DataNotFoundException extends RuntimeException  {

	public DataNotFoundException(String message) {
		super(message);
	}
	
}
