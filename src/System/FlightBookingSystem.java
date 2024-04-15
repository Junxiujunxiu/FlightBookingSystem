/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

/**
 *
 * @author spark
 */
import DataBase.FlightDatabase;
import Exceptions.BookingNotFoundException;
import Flight.Account;
import Flight.FlightSeat;
import Flight.Booking;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Flight.Flight;

public abstract class FlightBookingSystem {
	protected Flight hotel;
	protected Account account;
	protected List<Booking> bookings;

	/**
	 * Constructor to populate the system object
	 * @param account The account to create the system with
	 * @param bookings The bookings which the system contains
	 */
	FlightBookingSystem(Account account, List<Booking> bookings) {
		this.hotel = FlightDatabase.loadHotel().get(0);
		this.account = account;
		this.bookings = bookings;
	}

	/**
	 * Makes a booking.
	 * @param begin The beginning date for the booking
	 * @param end The end date for the booking
	 * @param rooms The rooms to book
	 * @param account The account that wants to book
	 * @return Returns a booking object which represents the booking
	 */
	public abstract Booking makeBooking(Date begin, Date end, List<FlightSeat> rooms, Account account);

	/**
	 * Makes a booking in the system. This is the method called by any system which would make bookings inside the system
	 * @param begin The beginning date for the booking
	 * @param end The end date for the booking
	 * @param rooms The rooms to book
	 * @param account The account that wants to book
	 * @param manager The account which manages the booking
	 * @return Returns a booking object which represents the booking
	 */
	protected Booking makeBooking(Date begin, Date end, List<FlightSeat> rooms, Account account, Account manager) {
		Booking booking = new Booking(String.valueOf(System.currentTimeMillis()), begin, end, rooms, rooms.size() * 100, account, manager);
		FlightBookingData.book(booking);
		// add bookings to master list like accoutns etc
//		bookings.add(booking);
		return booking;
	}

	/**
	 * Gets a list of bookings which match a predicate
	 * @param predicate The predicate to match the bookings to
	 * @return Returns a list of bookings which match the predicate.
	 */
	protected List<Booking> getBookingsWhere(Predicate<Booking> predicate) {
		return bookings.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Deletes a booking from the system
	 * @param bookingId The ID of the booking to be deleted
	 * @return Returns the booking which was deleted from the system
	 */
	public Booking deleteBooking(String bookingId) {
		try {
			List<Booking> bookingsList = getBookingsWhere(x -> x.bookingID.equals(bookingId));
			if (bookingsList.isEmpty()) return null;
			Booking booking = bookingsList.get(0);
			FlightDatabase.deleteBooking(booking);
            int index = bookings.stream().map(x -> x.bookingID).collect(Collectors.toList()).indexOf(bookingId);
            bookings.remove(index);
			return booking;
		}
		catch (BookingNotFoundException e) { return null; }
//		try {
//			Booking booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
////			booking = getBookingsWhere(x -> x.bookingID.equals(bookingId)).get(0);
//			bookings.remove(booking);
////			FileIO.saveBookings(bookings);
//			HotelDatabase.deleteBooking(booking);
//			return booking;
//		} catch (BookingNotFoundException e) {
//			return null;
//		}
	}

	/**
	 * Checks whether a room is available on a specific date.
	 * @param room The room to check availability for
	 * @param date The date to check availability on
	 * @return Returns true if the room is available. Otherwise false will be returned.
	 */
	public boolean roomIsAvailable(FlightSeat room, Date date) {
		// need an all bookings list to compare against
		// when the user logins in it on ly loads there bookings,
		// so only compares these to their own bookings
		for (Booking b : FlightBookingData.getBookings()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(b.beginDate());
			cal.add(Calendar.DATE, -1);
 			// if the date is on or after the begin date of this room, AND before (end-1) date of this room, then return true
			if (date.after(cal.getTime()) && date.before(b.endDate()))
				for (FlightSeat r : b.getRooms()) if (r.equals(room)) return false;
		}
		return true;
	}

	// Getter methods for bookings, user details, login confirmation, hotel details, and account.
	public List<Booking> getAllBookings() { return bookings; }
	public String getUserDetails() { return account.toString() + "\n" + account.getAccountDetails() + "\n"; }
	public String getConfirmLogin() { return "\nLogged in as " + account.toString() + "\n"; }
	public String getHotelDetails() { return this.hotel.toString(); }
	public Flight getHotel() { return this.hotel;}
	public Account getAccount() { return account; }
	public boolean bookingIsEmpty() { return bookings.isEmpty(); }

	/**
	 * Update the email of account and writes to database.
	 * 
	 * @param newEmail 
	 */
	public void updateEmail(String newEmail) {
		if (this.account.getEmail() != null) {
			this.account.updateEmail(newEmail);
			FlightDatabase.updateAccountEmail(this.account);
		}
	}

	/**
	 * Update the email of account and writes to database.
	 * 
	 * @param newEmail 
	 */
	public void updatePhone(String newPhone) {
		if (this.account.getPhone() != null) {
			this.account.updatePhone(newPhone);
			FlightDatabase.updateAccountPhone(this.account);
		}
	}
}
