package com.myproject.hotel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myproject.hotel.exception.ThrowsCustomException;
import com.myproject.hotel.model.RoomDate;
import com.myproject.hotel.view.Main;

public class BookingSystem implements BookingManager {

	private static Map<RoomDate, String> allBookings = new ConcurrentHashMap<RoomDate, String>();
	private static Integer[] allRooms = { 101, 102, 201, 203 };

	public BookingSystem() {
	}

	public boolean isRoomAvailable(Integer room, Date date) {
		if (Arrays.asList(allRooms).contains(room)) {
			if (allBookings.get(new RoomDate(room, date))==null) {
				return true;
			}
		} else {
			new ThrowsCustomException().InvalidRoom(room);
		}
		return false;
	}
	
	public void addBooking(String guest, Integer room, Date date) {
		String addBookingCheck = allBookings.putIfAbsent(new RoomDate(room, date), guest);
		if(addBookingCheck == null){
			System.out.println(Thread.currentThread().getName() + " addBooking = Successful [" + guest + ", " + room + ", " + date + "] \\^o^/");
			System.out.println("Total bookings = "+ ++Main.SUCCESSCOUNT);
		} else {
			new ThrowsCustomException().DuplicatedBooking(room, date);
		}
	}

	public Iterable<Integer> getAvailableRooms(Date date) {
		List<Integer> availableRooms = new ArrayList<Integer>(Arrays.asList(allRooms));
		for (Integer room : new ArrayList<Integer>(Arrays.asList(allRooms))) {
			if (allBookings.get(new RoomDate(room, date)) == null) {
				availableRooms.remove(room);
			}
		}
		return availableRooms;
	}

	public void resetDatabase() {
		allBookings = new ConcurrentHashMap<RoomDate, String>();		
	}
}
