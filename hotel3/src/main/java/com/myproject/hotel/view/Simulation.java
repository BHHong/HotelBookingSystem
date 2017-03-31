package com.myproject.hotel.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.myproject.hotel.controller.BookingManager;
import com.myproject.hotel.controller.BookingSystem;

public class Simulation {

	private BookingManager bm = new BookingSystem();

	public void reset(){
		bm.resetDatabase();
		Main.SUCCESSCOUNT=0;
	}
	
	public void populateDatabase() {

		Date d;
		String s, n;
		// populate database
		for (int k = 1990; k < 2015; k++) {
			for (int j = 1; j < 13; j++) {
				for (int i = 1; i < 28; i++) {
					s = k + "-" + j + "-" + i;
					n = "Customer-" + j + i;
					try {
						d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
						bm.addBooking(n, 101, d);
						bm.addBooking(n, 102, d);
						bm.addBooking(n, 201, d);
						bm.addBooking(n, 203, d);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("====================================");
	}
	
	public void run(int staff) throws InterruptedException{
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < staff; i++) {
			es.submit(new MyRunnable());
		}
		es.shutdown();
		es.awaitTermination(1, TimeUnit.SECONDS);
	}
}