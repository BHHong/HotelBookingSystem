package com.myproject.hotel.view;

public class Main {

	public static int SUCCESSCOUNT = 0;

	public static void main(String[] args) throws InterruptedException {

		// Stress Test
		int numberOfTestsToRun = 10000;
		int numberOfStaff = 100;
		int expectedSuccessCount = 4;
		
		int testsCompleted = 0;
		do {
			new Simulation().reset();
			double t1 = System.currentTimeMillis();
			// populate Database for more realistic testing
			// new Simulation().populateDatabase();
			double t2 = System.currentTimeMillis();

			// Simulate multiple staffs executing same procedures.
			new Simulation().run(numberOfStaff);
			double t3 = System.currentTimeMillis();
			Thread.sleep(5);
			System.out.println("===============================");
			System.out.println("PreProcess Time: " + (t2 - t1) + " ms");
			System.out.println("Process Time: " + (t3 - t2) + " ms");
			System.out.println("Total bookings added: " + SUCCESSCOUNT);
			System.out.println("===============================");
			testsCompleted++;
			if (testsCompleted == numberOfTestsToRun){
				System.out.println("Stress test passed.");
				break;
			}
		} while (SUCCESSCOUNT == expectedSuccessCount);
		System.err.println("Tests completed: "+ testsCompleted);
	}
}
