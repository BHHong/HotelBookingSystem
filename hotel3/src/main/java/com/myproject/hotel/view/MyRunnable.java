package com.myproject.hotel.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.myproject.hotel.controller.BookingManager;
import com.myproject.hotel.controller.BookingSystem;

public class MyRunnable implements Runnable {

	private BookingManager bm = new BookingSystem();

	@Override
	public void run() {
		Date d1, d2, d3, d4;
		try {
			 d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-01");
			 d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-06-15");
			 d3 = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-20");
			 d4 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-30");
			 
			// trial 1
//			System.out.println(Thread.currentThread().getName() + " Available rooms on " + d1 + " " + bm.getAvailableRooms(d1));
//			System.out.println(bm.isRoomAvailable(101, d1));
			bm.addBooking("Smith", 101, d1);

			// trial 2
//			System.out.println(Thread.currentThread().getName() + " Available rooms on " + d2 + " " + bm.getAvailableRooms(d2));
//			System.out.println(bm.isRoomAvailable(102, d2));
			bm.addBooking("Jones", 102, d2);
			
			// trial 3
//			System.out.println(Thread.currentThread().getName() + " Available rooms on " + d3 + " " + bm.getAvailableRooms(d3));
//			System.out.println(bm.isRoomAvailable(201, d3));
			bm.addBooking("James", 201, d3);
			
			// trial 4
//			System.out.println(Thread.currentThread().getName() + " Available rooms on " + d3 + " " + bm.getAvailableRooms(d3));
//			System.out.println(bm.isRoomAvailable(201, d4));
			bm.addBooking("Simon", 203, d4);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
