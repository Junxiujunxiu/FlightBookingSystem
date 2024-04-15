/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import System.FlightBookingSystem;
import System.AccountManager;
import DataBase.FlightDatabase;
import Exceptions.AccountNotFoundException;
import Flight.Account;

public class LoginAndRegister {

	public FlightBookingSystem hotelSystem;
	private FlightDatabase hotelDatabase = new FlightDatabase();

	/***
	 * @param username, to be checked
	 * @return boolean value of if username exists.
	 */
	public boolean checkUserNameExists(String username) {
		return AccountManager.checkUsernameExists(username); 
	}

	/***
	 * Logs user in and instanitates the correct hotelSystem, either USER or ADMIN.
	 * 
	 * @param enteredUsername
	 * @param enteredPassword
	 * @return boolean value if it loaded the hotelSystem
	 */
	// exception go one level deeperin into AccountManager instead
	// not able to return boolean false
	public boolean loginPortal(String enteredUsername, String enteredPassword) {
		try {
			this.hotelSystem = AccountManager.login(enteredUsername, enteredPassword);
			return true;
		} catch (AccountNotFoundException e) {
			return false;
		}
	}

	/***
	 * Creates a new account, and saves to database.
	 * 
	 * @param usr, entered username
	 * @param pwd, entered password
	 * @param fname, entered firstname
	 * @param lname, entered lastname
	 * @param email, entered email
	 * @param phone, entered phone
	 */
	public void registerPortal(String usr, String pwd, String fname, String lname, String email, String phone) {
		Account newAccount = AccountManager.createAccount(usr, pwd, fname, lname, phone, email);
		if (newAccount == null) {
			System.out.println("An error occurred when creating your account; please try again.");
			return;
		}
	}

	/***
	 * Logout process, closes connection to database and sets class attributes to null.
	 */
	public void logout() {
		this.hotelDatabase.closeConnection();
		this.hotelSystem = null;
		this.hotelDatabase = null;
		System.out.println("Logged out\n");
	}
}
