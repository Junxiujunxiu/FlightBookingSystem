/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Flight.Account;
import Flight.Booking;
import Flight.LoginInfo;

public class SQL {
	//////////////////////////////////////////////////
	// CREATE TABLE
	//////////////////////////////////////////////////

	/**
     * Returns the default query for creating the hotel table
     * @return Returns the default query for creating the hotel table (with preset data)
     */
	static String[] createHotelTable() {
		return new String[] 
		{
			"CREATE TABLE Hotel ( "
				+ "hotelName VARCHAR(50),"
				+ "street VARCHAR(50),"
				+ "suburb VARCHAR(50),"
				+ "city VARCHAR(50),"
				+ "postcode VARCHAR(5),"
				+ "country VARCHAR(50),"
				+ "phone VARCHAR(50),"
				+ "email VARCHAR(50)"
				+ ")",

			"INSERT INTO Hotel VALUES "
			+ "('Queens Hotel', '10 Main Street', 'CBD', 'Auckland', '1010', 'New Zealand', '+64090888', 'hotel@hotel.com')"
		};
	}

    /**
     * Returns the default query for creating the credentials table
     * @return Returns the default query for creating the credentials table (with preset data)
     */
	static String[] createCredentialTable() {
		return new String[]
		{
			"CREATE TABLE Credentials (\n" +
			"    username VARCHAR(20),\n" +
			"    password VARCHAR(20) NOT NULL,\n" +
			"    PRIMARY KEY (username)\n" +
			")",

			"INSERT INTO Credentials VALUES "
			+ "('admin', 'admin'),"
			+ "('user', 'user')"
		};
	}

	/**
     * Returns the default query for creating the accounts table
     * @return Returns the default query for creating the accounts table (with preset data)
     */
	static String[] createAccountTable() {
		return new String[]
		{
			"CREATE TABLE Accounts (\n" +
			"    username VARCHAR(20),\n" +
			"    firstName VARCHAR(30) NOT NULL,\n" +
			"    lastName VARCHAR(30) NOT NULL,\n" +
			"    phone VARCHAR(16),\n" +
			"    email VARCHAR(60) NOT NULL,\n" +
			"    permissions VARCHAR(10) NOT NULL,\n" +
			"    PRIMARY KEY (username),\n" +
			"    FOREIGN KEY (username) REFERENCES Credentials(username)\n" +
			")",

			"INSERT INTO Accounts VALUES "
			+ "('admin', 'admin', 'admin', '+21153324', 'admin@hotel.com', 'ADMIN'),"
			+ "('user', 'user', 'user', '+21152297', 'group5@comp603.com', 'USER')"
		};
	}

	/**
     * Returns the default query for creating the bookings table
     * @return Returns the default query for creating the bookings table
     */    
	static String[] createBookingTable() {
		return new String[]
		{
			"CREATE TABLE Bookings (\n" +
			"    bookingID VARCHAR(22),\n" +
			"    startDate DATE NOT NULL,\n" +
			"    endDate DATE NOT NULL,\n" +
			"    price REAL NOT NULL,\n" +
			"    booker VARCHAR(20) NOT NULL,\n" +
			"    manager VARCHAR(20) NOT NULL,\n" +
			"\n" +
			"    PRIMARY KEY (bookingID),\n" +
			"    FOREIGN KEY (booker) REFERENCES Accounts(username),\n" +
			"    FOREIGN KEY (manager) REFERENCES Accounts(username)\n" +
			")",
		};
	}

    /**
     * Returns the default query for creating the rooms table
     * @return Returns the default query for creating the rooms table
     */
	static String[] createRoomsTable() {
		return new String[] {
			"CREATE TABLE Rooms (\n" +
			"    bookingID VARCHAR(22) NOT NULL,\n" +
			"    room CHAR(2))"
		};
	}


	//////////////////////////////////////////////////
	// INSERT TABLE
	//////////////////////////////////////////////////

    /**
     * Gets the query to insert an account into the database
     * @param account The account to be added to the database
     * @return The SQL query to insert the account into the database
     */
	static String insertAccountTable(Account account) {
		return String.format("INSERT INTO accounts VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
			account.username, account.firstName, account.lastName, account.phone, account.email, account.getAccountTypeName());
	}

    /**
     * Gets the query to insert a credential into the database
     * @param credential The credential to be added to the database
     * @return The SQL query to insert the credential into the database
     */
	static String insertCredentialTable(LoginInfo credential) {
		return String.format("INSERT INTO credentials VALUES ('%s', '%s')", credential.getUsername(), credential.getPassword());
	}

    /**
     * Gets the query to insert a booking into the database
     * @param booking The booking to be added to the database
     * @return The SQL query to insert the booking into the database
     */
	static String[] insertBookingTable(Booking booking) {
		double price = booking.getRooms().size() * 100;
		String[] insertRoomsCommands = insertRoomsTable(booking);
		String[] result = new String[insertRoomsCommands.length + 1];
		for (int i = 0; i < insertRoomsCommands.length; i++) {
			result[i] = insertRoomsCommands[i];
		}

		result[insertRoomsCommands.length] =
			"INSERT INTO Bookings VALUES ('" +
			booking.bookingID + "','" +
			new java.sql.Date(booking.beginDate().getTime()) + "','" +
			new java.sql.Date(booking.endDate().getTime()) + "'," +
			price + ",'" +
			booking.getAccount().username + "','" +
			booking.getBookingManager().username + "')";

		return result;
	}

    /**
     * Gets the query to insert the rooms for a booking into the database
     * @param booking The booking containing the rooms to be added to the database
     * @return The SQL query to insert the rooms for the booking into the database
     */
	private static String[] insertRoomsTable(Booking booking) {
		String[] queries = new String[booking.getRooms().size()];
		for (int i = 0; i < queries.length; i++)
			queries[i] = String.format("INSERT INTO Rooms VALUES ('%s', '%s')", booking.bookingID, booking.getRooms().get(i).getRoomNumber());

		return queries;
	}

	//////////////////////////////////////////////////
	// UPDATE TABLE
	//////////////////////////////////////////////////
    
    /**
     * Updates an account's email
     * @param username The username of the account to be updated
     * @param newEmail The new email for the account
     * @return The query to update the email for the account
     */
	static String updateAccountEmail(String username, String newEmail) {
		return String.format("UPDATE accounts SET email = '%s' WHERE username = '%s'", newEmail, username);
	}

    /**
     * Updates an account's phone
     * @param username The username of the account to be updated
     * @param newPhone The new phone for the account
     * @return The query to update the phone for the account
     */
	static String updateAccountPhone(String username, String newPhone) {
		return String.format("UPDATE accounts SET phone = '%s' WHERE username = '%s'", newPhone, username);
	}

    /**
     * Deletes a booking 
     * @param bookingID The booking ID of the booking to be deleted
     * @return The query to delete the booking from the database
     */
	static String deleteBooking(String bookingID) {
		return String.format("DELETE FROM Bookings WHERE bookingID = '%s'", bookingID);
	}

	//////////////////////////////////////////////////
	// SELECT TABLE
	//////////////////////////////////////////////////
    /**
     * Selects all the rows from a table
     * @param name The name of the table to select from
     * @return The query to select all the rows from the table
     */
	static String selectAll(String name) {
		return "SELECT * FROM " + name;
	}

	//////////////////////////////////////////////////
	// DROP TABLE
	//////////////////////////////////////////////////
    /**
     * Deletes a table from the database
     * @param name The name of the table to be deleted
     * @return The query to delete the table from the database
     */
	static String dropTable(String name) {
		return "DROP TABLE " + name;
	}
}
