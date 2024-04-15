/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import Flight.FlightSeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Represents the Bookings - Rooms tables relationship in the database
class SeatReservation {
    /**
     * The rooms table in the database
     * Maps a booking ID to a list of rooms
     */
	static HashMap<String, List<FlightSeat>> rooms;
    
    /**
     * Static initializer for the rooms
     */
	static {
		rooms = new HashMap<>();
	}
    
    /**
     * Adds a room and the booking ID it belongs to to the rooms
     * @param bookingID the booking ID which contains the room
     * @param room the room to add
     */
	static void addRoom(String bookingID, FlightSeat room) {
		if (rooms.containsKey(bookingID)) {
			rooms.get(bookingID).add(room);
		} else {
			List<FlightSeat> roomsList = new ArrayList<FlightSeat>();
			roomsList.add(room);
			rooms.put(bookingID, roomsList);
		}
	}
}
