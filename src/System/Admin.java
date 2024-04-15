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

import java.util.Set;

public class Admin extends FlightBookingSystem {
	/**
	 * Constructor to populate the system object
	 * @param account The account to create the system with
	 * @param bookings The bookings which the system contains
	 */
	Admin(Account account, List<Booking> bookings) {
		super(account, bookings);
	}

	@Override
	public Booking makeBooking(Date begin, Date end, List<FlightSeat> rooms, Account user) {
		Booking b = super.makeBooking(begin, end, rooms, user, this.account);
		return b;
	}

	/**
	 * Returns the first user which username matches usernameToFind
	 * @param usernameToFind The username to match the account to
	 * @return Returns the first match of users which username matches usernameToFind.
	 */
	public Account getAccountByUsername(String usernameToFind) {
		List<Account> foundUser = FlightBookingData.getAccountsByUsername(usernameToFind);
		if (!foundUser.isEmpty()) return foundUser.get(0);
		else return null;
	}

	/**
	 * Gets the list of usernames of each user in the system
	 * @return Returns the list of usernames of each user in the system
	 */
	public Set<String> getUserList() {
		return FlightBookingData.getUsernameList();
	}

	/**
	 * Deletes a booking from the system where the specified ID matches bookingId
	 * @param bookingId The ID of the booking to delete
	 */
	public Booking deleteBookingByID(String bookingId) {
		Booking deleted = super.deleteBooking(bookingId);
		return deleted;
	}
}
