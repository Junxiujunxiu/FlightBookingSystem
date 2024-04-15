/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Flight.LoginInfo;
import Flight.Booking;
import Flight.AccountPermission;
import Flight.FlightSeat;
import Flight.Account;
import Flight.Flight;
import Exceptions.BookingNotFoundException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FlightDatabase {
	private static DatabaseManager dbManager;
    
    /**
     * Cache storage for the accounts
     */
	private static List<Account> cacheAccounts;

    /**
     * Constructor calls createTable for all of the tables
     */
	public FlightDatabase() {
		dbManager = new DatabaseManager();
		createTable("hotel", SQL.createHotelTable());
		createTable("credentials", SQL.createCredentialTable());
		createTable("accounts", SQL.createAccountTable());
		createTable("bookings", SQL.createBookingTable());
		createTable("rooms", SQL.createRoomsTable());
	}

    /**
     * Loads the hotel into the program's memory
     * @return A list of all hotels in the database
     */
	public static List<Flight> loadHotel() {
		String tableName = "hotel";
		if (!tableExists(tableName)) return null;

		List<Flight> hotels = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				hotels = new ArrayList<>();
				do {
					String hotelName = resultSet.getString("hotelName");
					String street = resultSet.getString("street");
					String suburb = resultSet.getString("suburb");
					String city = resultSet.getString("city");
					String postcode = resultSet.getString("postcode");
					String country = resultSet.getString("country");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					hotels.add(new Flight(hotelName, street, suburb, city, postcode, country, phone, email));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return hotels;
	}

    /**
     * Loads the credentials into the program's memory
     * @return A set of all the credentials in the database
     */
	public static Set<LoginInfo> loadCredentials() {
		String tableName = "credentials";
		if (!tableExists(tableName)) return null;

		Set<LoginInfo> credentials = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				credentials = new HashSet<>();
				do {
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					credentials.add(new LoginInfo(username, password));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return credentials;
	}

    /**
     * Loads all the accounts into the program's memory
     * @return A list of all the accounts in the database
     */
	public static List<Account> loadAccounts() {
		String tableName = "accounts";
		if (!tableExists(tableName)) return null;

		List<Account> accounts = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				accounts = new ArrayList<>();
				do {
					String username = resultSet.getString("username");
					String firstName = resultSet.getString("firstname");
					String lastName = resultSet.getString("lastName");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					String permission = resultSet.getString("permissions");

					AccountPermission accountPermission = null;
					if (permission.equalsIgnoreCase("ADMIN")) accountPermission = AccountPermission.ADMIN;
					else accountPermission = AccountPermission.USER;

					accounts.add(new Account(username, firstName, lastName, phone, email, accountPermission));
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		cacheAccounts = accounts;
		return accounts;
	}

    /**
     * Loads all the rooms into the program's memory
     * @return A mapping of all booking IDs and the rooms that were included inside that booking
     */
	private static Map<String, List<FlightSeat>> getRooms() {
		String tableName = "rooms";
		if (!tableExists(tableName)) return null;

		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				do {
					String bookingID = resultSet.getString("bookingID");
					String room = resultSet.getString("room");
					FlightSeat roomObj = new FlightSeat(room.charAt(0) - '0', room.charAt(1));

					SeatReservation.addRoom(bookingID, roomObj);
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}
		return SeatReservation.rooms;
	}

    /**
     * Loads all the bookings into the program's memory
     * @return A list of all the bookings in the database
     */
	public static List<Booking> loadBookings() {
        // Gets all the rooms in the database
		Map<String, List<FlightSeat>> bookingRooms = getRooms();
		String tableName = "bookings";
		if (!tableExists(tableName)) return null;

		List<Booking> bookings = null;
		ResultSet resultSet = dbManager.query(SQL.selectAll(tableName));
		try {
			if (!resultSet.next()) System.out.println("No results");
			else {
				bookings = new ArrayList<>();
				do {
					String bookingID = resultSet.getString("bookingID");
					java.util.Date startDate = resultSet.getTimestamp("startDate");
					java.util.Date endDate = resultSet.getTimestamp("endDate");
					float price = resultSet.getFloat("price");
					String booker = resultSet.getString("booker");
					String manager = resultSet.getString("manager");
                    
                    // Get all the rooms for the current booking ID
					List<FlightSeat> currentRooms = bookingRooms.get(bookingID);
                    
                    // Gets the booker and manager account from the cached accounts, for the booking
					Optional<Account> bookerAcc = cacheAccounts.stream().filter(x -> x.getUsername().equals(booker)).findFirst();
 					Optional<Account> managerAcc = cacheAccounts.stream().filter(x -> x.getUsername().equals(manager)).findFirst();

					Booking booking = new Booking(bookingID, startDate, endDate, currentRooms, price, bookerAcc.get(), managerAcc.get());
					bookings.add(booking);
				} while (resultSet.next());
			}
			resultSet.close();
		} catch (SQLException ex) { System.out.println(ex.getMessage());}

        // Destroy the cached accounts
		cacheAccounts = null;
		if (bookings == null) bookings = new ArrayList<>();
		return bookings;
	}

    /**
     * Inserts a credential into the credentials table
     * @param credential The credential to insert
     */
	public static void insertCredentialTable(LoginInfo credential) {
			try {
				dbManager.update(SQL.insertCredentialTable(credential));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

    /**
     * Inserts an account into the accounts table
     * @param account The account to insert
     */
	public static void insertAccountTable(Account account) {
			try { 
                dbManager.update(SQL.insertAccountTable(account));
            } catch (Exception e) {
                throw new RuntimeException(e); 
            }
	}

    /**
     * Inserts a booking in to the bookings table
     * @param booking The booking to be inserted
     */
	public static void insertBookingTable(Booking booking) {
			try { 
                for (String command : SQL.insertBookingTable(booking)) dbManager.update(command);
            } catch (Exception e) { 
                throw new RuntimeException(e);
            }
	}

    /**
     * Updates the phone for an account
     * @param account The account to update (containing the new phone number)
     */
	public static void updateAccountPhone(Account account) {
		String username = account.getUsername();
		String newPhone = account.getPhone();
        
		try {
            dbManager.update(SQL.updateAccountPhone(username, newPhone)); 
        } catch (SQLException e) { 
            throw new RuntimeException(e);
        }
	}
	
    /**
     * Updates the email for an account
     * @param account The account to update (containing the new email)
     */
	public static void updateAccountEmail(Account account) {
		String username = account.getUsername();
		String newEmail = account.getEmail().toLowerCase();

		try { 
            dbManager.update(SQL.updateAccountEmail(username, newEmail)); 
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        }
	}

    /**
     * Deletes a booking from the database
     * @param booking The booking to be deleted
     * @throws BookingNotFoundException Throws this exception if the booking does not exist in the database
     */
	public static void deleteBooking(Booking booking) throws BookingNotFoundException {
		try {
			ResultSet result = dbManager.query("SELECT * FROM Bookings WHERE bookingID = '" + booking.bookingID + "'");
            // If there is no result from the SELECT WHERE statement
			if (!result.next()) 
                throw new BookingNotFoundException("The booking ID does not exist.");
            
			dbManager.update(SQL.deleteBooking(booking.bookingID));
		} catch (SQLException e) { 
            throw new BookingNotFoundException("The booking ID does not exist.");
        }
	}

    /**
     * Creates a table in the database if it does not already exist
     * @param name The name of the table to create
     * @param sql The SQL queries to execute to create the table
     */
	private static void createTable(String name, String...sql) {
		try { 
            if (!tableExists(name)) 
                dbManager.updateBatch(sql);
        } catch (SQLException e) { 
            System.out.println("Table " + name + " exists.");
        }
	}

    /**
     * Checks if a table already exists in the database
     * @param name The name of the table
     * @return Returns whether the table already exists in the database.
     */
	private static boolean tableExists(String name) {
		try {
			DatabaseMetaData metadata = dbManager.getConnection().getMetaData();
			ResultSet resultSet = metadata.getTables(null, null, null, null);

			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(name))
                    return true;
			}
			resultSet.close();
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
		return false;
	}

    /**
     * Closes the connection on the database manager
     */
	public void closeConnection() {
		this.dbManager.closeConnection();
	}
}