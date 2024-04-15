/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import Flight.Account;
import Flight.FlightSeat;
import Flight.Booking;
import java.util.Date;
import java.util.List;

public class User extends FlightBookingSystem {
	// renamed from UserSystem

	User(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	/**
	 * Makes a booking for a user.
	 * @param begin The beginning date of the booking
	 * @param end The end date of the booking
	 * @param rooms The rooms to book
	 * @param account The account who is creating the booking. account must equal to the account stored inside the HotelSystemUser's account
	 * @return Returns the booking which was just created
	 */
	@Override
	public Booking makeBooking(Date begin, Date end, List<FlightSeat> rooms, Account account) {
		if (!account.equals(this.account)) {
			return null;
		}

		Booking booking = super.makeBooking(begin, end, rooms, account, this.account);
		bookings.add(booking);
		return booking;
	}

	/**
	 * Refreshes the bookings list in the system
	 */
	private void updateBookingsForAccount() {
		bookings = FlightBookingData.getBookingsForAccount(account);
	}

	/**
	 * Prints all the bookings for the current user
	 */
	public void printUserBookings() {
		updateBookingsForAccount();

		if (bookings.isEmpty()) {
			System.out.println("No bookings to view.");
			return;
		}

		for (Booking b : bookings)
			System.out.println(b.toString() + "\n");
	}
}
