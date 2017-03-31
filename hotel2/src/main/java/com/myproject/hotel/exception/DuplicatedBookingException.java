package com.myproject.hotel.exception;

@SuppressWarnings("serial")
public class DuplicatedBookingException extends Exception{

	public DuplicatedBookingException(String message) {
		super(message);
	}

}
