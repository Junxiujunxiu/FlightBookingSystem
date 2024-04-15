/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import DataBase.FlightDatabase;
import Exceptions.MismatchingCredentialsException;
import Flight.Account;
import Flight.Booking;
import Flight.LoginInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class FlightBookingData {
	private static final List<Booking> bookings;
	private static final List<Account> accounts;
	private static final Set<LoginInfo> credentials;

	/**
	 * Static initializer to populate bookings and accounts Lists
	 */
	static {
		// later need to load credentials on each register instance
		// close session after logout.
		// open new session after login.
		// open a new session after register, make login after, or auto login
		credentials = FlightDatabase.loadCredentials();
		accounts = FlightDatabase.loadAccounts();
		bookings = FlightDatabase.loadBookings();
	}

	/**
	 * Books a file in the system
	 * @param booking The booking to add
	 */
	static void book(Booking booking) {
		bookings.add(booking);

		FlightDatabase.insertBookingTable(booking);
	}

	/**
	 * @return list of all registered usernames
	 */
	static Set<String> getUsernameList() {
		return credentials.stream().map(x -> x.getUsername()).collect(Collectors.toSet());
	}

	/**
	 * Gets a list of accounts which match a predicate
	 * @param predicate The predicate to match the accounts to
	 * @return Returns a list of accounts which match the predicate
	 */
	static List<Account> getAccountsWhere(Predicate<Account> predicate) {
		return accounts.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Gets a list of accounts which match a username
	 * @param username The username to match the accounts to
	 * @return Returns a list of accounts which username matches the parameter 'username'. If the size of the list is greater than 1 then there is an error in the data.
	 */
	static List<Account> getAccountsByUsername(String username) {
		return getAccountsWhere(x -> x.getUsername().equals(username));
	}

	/**
	 * Gets a list of bookings which match a predicate
	 * @param predicate The predicate to match the actions to
	 * @return Returns a list of bookings which match the predicate
	 */
	static List<Booking> getBookingsWhere(Predicate<Booking> predicate) {
		return bookings.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Gets a list of bookings for an account
	 * @param account The account to match the bookings to
	 * @return Returns a list of bookings which were booked by the account (not managed!)
	 */
	static List<Booking> getBookingsForAccount(Account account) {
		return getBookingsWhere(x -> x.getAccount().equals(account));
	}

	/**
	 * Registers an account into the system
	 * @param credentials The credentials of the account
	 * @param account The account object containing user information
	 */
	public static void register(LoginInfo credentials, Account account) throws MismatchingCredentialsException {
		if (!credentials.getUsername().equals(account.getUsername()))
			throw new MismatchingCredentialsException("The credentials and account provided do not match.");

		// maybe remove this so reads database each time, but then
		// auto login won't work
		FlightBookingData.credentials.add(credentials);
		accounts.add(account);

		FlightDatabase.insertCredentialTable(credentials);
		FlightDatabase.insertAccountTable(account);
	}

	// Getter methods
	static List<Booking> getBookings() { return bookings; }
	static List<Account> getAccounts() { return accounts; }
	static Set<LoginInfo> getCredentials() { return credentials; }
}
