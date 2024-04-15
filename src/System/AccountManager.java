/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

/**
 *
 * @author spark
 */
import Exceptions.AccountNotFoundException;
import Exceptions.MismatchingCredentialsException;
import Flight.Account;
import Flight.AccountPermission;
import Flight.LoginInfo;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;
import static System.FlightBookingData.*;

public class AccountManager {

	private AccountManager() { }

    /**
     * Checks whether a username exists 
     * @param username The user name to search for
     * @return Returns true if the username exists. Otherwise, returns false;
     */
	public static boolean checkUsernameExists(String username) {
        // Map each credential to its usernames
		Stream<String> usernames = getCredentials().stream().map(x -> x.getUsername());
        // Find the matches for the username
		Optional<String> findMatches = usernames.filter(x -> x.equalsIgnoreCase(username)).findAny();
        // Returns whether there were any matches
		return findMatches.isPresent();
	}

    /**
     * Creates an account and registers it to the Database objects
     * @param username The username of the account
     * @param password The password of the account
     * @param fname The first name of the account
     * @param lname The last name of the account
     * @param phone The phone number of the account
     * @param email The email of the account
     * @return Returns the account that was just created, or null if an error occurred
     */
	public static Account createAccount(String username, String password, String fname, String lname, String phone, String email) {
        // The account to be added
		Account account = new Account(username, fname, lname, phone, email, AccountPermission.USER);
        // The credentials to be created
		LoginInfo credentials = new LoginInfo(username, password);

		try {
            // Register into the database
			register(credentials, account);
		} catch (MismatchingCredentialsException e) {
            // Return null if an error occurred
			return null;
		}

        // Return the account
		return account;
	}

    /**
     * Logs in to an account and provides a system for the account to use
     * @param username The username of the account
     * @param password The password of the account
     * @return Returns a HotelSystem if the login was successful, or null if an error occurred.
     * @throws AccountNotFoundException Throws this exception if the account was not found.
     */
	public static FlightBookingSystem login(String username, String password) throws AccountNotFoundException {
		LoginInfo providedCredentials = new LoginInfo(username, password);
		HashSet<LoginInfo> credentialsHS = new HashSet<LoginInfo>(getCredentials());

		// First, determine whether there is a matching set of credentials
		Optional<LoginInfo> match = credentialsHS.stream().filter(x -> x.equals(providedCredentials)).findFirst();

		// If no matching credentials were found, throw an exception
		if (!match.isPresent())
			throw new AccountNotFoundException("The username did not match any existing account.");

		// This will only run if a pair of matching credentials were found.
		// Therefore, it is safe to filter through via the username parameter directly.
		Optional<Account> accountMatch = getAccounts().stream().filter(x -> x.username.equals(username)).findFirst();

		if (!accountMatch.isPresent()) {
			throw new AccountNotFoundException("The username did not match any existing account.");
		}

		Account account = accountMatch.get();

		if (account.getAccountType() == AccountPermission.ADMIN) {
			return new Admin(account, FlightBookingData.getBookings());
		} else if (account.getAccountType() == AccountPermission.USER) {
			return new User(account, FlightBookingData.getBookingsWhere(x -> x.getAccount().equals(account)));
		}

		throw new AccountNotFoundException("The username did not match any existing account.");
	}
}