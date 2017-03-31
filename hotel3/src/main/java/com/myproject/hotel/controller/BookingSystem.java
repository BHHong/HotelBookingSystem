package com.myproject.hotel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.myproject.hotel.exception.DuplicatedBookingException;
import com.myproject.hotel.exception.InvalidRoomException;
import com.myproject.hotel.exception.ThrowsCustomException;
import com.myproject.hotel.view.Main;

public class BookingSystem implements BookingManager {

	private static Map<Date, Hashtable<Integer, String>> allBookings = new ConcurrentHashMap<Date, Hashtable<Integer, String>>();
	private Integer[] allRooms = { 101, 102, 201, 203 };

	public BookingSystem() {
	}

	public boolean isRoomAvailable(Integer room, Date date) {
		if (Arrays.asList(allRooms).contains(room)) {
			if (allBookings.containsKey(date)) {
				if (allBookings.get(date).containsKey(room)) {
					return false;
				}
			}
			return true;
		} else {
			new ThrowsCustomException().InvalidRoom(room);
		}
		return false;
	}

	public void addBooking(String guest, Integer room, Date date) {
		synchronized (allBookings) {
			Hashtable<Integer, String> list = allBookings.get(date) == null ? new Hashtable<Integer, String>()
					: allBookings.get(date);
			String bookingGuest = list.putIfAbsent(room, guest);
			if (bookingGuest == null){
					allBookings.put(date, list);
					System.out.println(Thread.currentThread().getName() + " addBooking = Successful [" + guest + ", "
							+ room + ", " + date + "] \\^o^/");
					System.out.println("Total bookings: " + ++Main.SUCCESSCOUNT);
			} else {
				new ThrowsCustomException().DuplicatedBooking(room, date);
			}
		}
	}

	public Iterable<Integer> getAvailableRooms(Date date) {
		List<Integer> availableRooms = new ArrayList<Integer>(Arrays.asList(allRooms));
		if (allBookings.containsKey(date)) {
			List<Integer> bookedRoom = new ArrayList<Integer>(allBookings.get(date).keySet());
			availableRooms.removeAll(bookedRoom);
		}
		return availableRooms;
	}

	public void resetDatabase() {
		allBookings = new ConcurrentHashMap<Date, Hashtable<Integer, String>>();
	}
}
