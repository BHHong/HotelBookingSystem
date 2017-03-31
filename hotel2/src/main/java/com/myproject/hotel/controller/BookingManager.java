package com.myproject.hotel.controller;

import java.util.Date;

public interface BookingManager {

	public boolean isRoomAvailable(Integer room, Date date);
	
	public void addBooking(String guest, Integer room, Date date);
	
	public Iterable<Integer> getAvailableRooms(Date date);

	public void resetDatabase();

}
