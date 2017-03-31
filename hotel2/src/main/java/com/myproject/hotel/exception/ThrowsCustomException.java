package com.myproject.hotel.exception;

import java.util.Date;

public class ThrowsCustomException {
	
	public void DuplicatedBooking(Integer room, Date date){
		try {
			throw new DuplicatedBookingException(Thread.currentThread().getName() + " Exception: Duplicate booking. [" + room + ", " + date + "]");
		} catch (DuplicatedBookingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void InvalidRoom(Integer room){
		try {
			throw new InvalidRoomException(Thread.currentThread().getName() + " Exception: Invalid room number. [" + room);
		} catch (InvalidRoomException e) {
			System.err.println(e.getMessage());
		}
	}
}
