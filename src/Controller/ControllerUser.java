/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author spark
 */
import Main.LoginAndRegister;
import Card.CardBookingManageUser;
import Flight.FlightSeat;
import View.ViewUser;
import View.ViewGUI;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControllerUser extends UserAccountController {

	/**
	 * User view of client
	 * 
	 * @param view
	 * @param model 
	 */
	public ControllerUser(ViewGUI view, LoginAndRegister model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		// sets clientView to be the admin verison
		super.clientView = new ViewUser(this, super.getAccountInfo(), super.getHotelInfo(), super.getBookings());
		super.updateDisplay(super.clientView.getBasePanel());// update the ViewGUI to display it
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardAccount()!= null) {
			if (e.getSource() == getCardAccount().editAccountButton) editHandler();
			else if (e.getSource() == getCardAccount().logoutButton) logoutHandler();
			else if (e.getSource() == getBookingUser().cancelButton) cancelHandler();
			else if (e.getSource() == getBookingUser().bookButton) bookHandler();
		}
	}

	// loads the Account edit panel to show, along with its controller
	private void editHandler() {
		if (getModel() != null) new ControllerAccountEdit(getView(), getModel());
	}

	// clears the input in the create booking panel
	private void cancelHandler() {
		getBookingUser().rooms.setText("");
		getBookingUser().startDate.clearDate();
		getBookingUser().endDate.clearDate();
	}

	// Handles the booking input, and makes booking if valid
	private void bookHandler() {
		Date startDate = getBookingUser().startDate.getDate();
		Date endDate = getBookingUser().endDate.getDate();
		String rooms = getBookingUser().rooms.getText();
		System.out.println(startDate);

		String start = (startDate != null) ? startDate.toString() : "";
		String end = (endDate != null) ? endDate.toString() : "";

		if (!hasEmptyField(getBookingUser(), start, end, rooms)) {
			if (startBeforeToday(startDate)) {
				getCardBookingManage().showWarningPopup("Start date must be after today date...");
				return;
			}
			if (endDate.before(startDate)) {
				getCardBookingManage().showWarningPopup("End date must be after start date...");
				return;
			}

			String[] parsed = parseRoom(rooms);
			Set<FlightSeat> selectedRooms = checkValidInput(parsed);
			if (selectedRooms != null && checkRoomAvailable(selectedRooms, startDate)) {
				List<FlightSeat> roomsToBook = new ArrayList<>(selectedRooms);
				createBooking(startDate, endDate, roomsToBook);
				getCardBookingManage().showWarningPopup(getBookingConfirm(startDate, endDate, roomsToBook.size()));
				renderClient();
			}
		}
	}

	// returns string showing booking dates and amount of rooms
	private String getBookingConfirm(Date startDate, Date endDate, int qty) {
		return "Booking created.\n" + getBookingPeriod(startDate, endDate) + "\nFor: " + qty + " rooms.";

	}

	// returns formatted string of date
	private String getBookingPeriod(Date begin, Date end) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return "Date: " + formatter.format(begin) + " to " + formatter.format(end);
	}

	// create the booking through model
	private void createBooking(Date startDate, Date endDate, List<FlightSeat> roomsToBook) {
		if (!roomsToBook.isEmpty()) { 
			getModel().hotelSystem.makeBooking(startDate, endDate, roomsToBook, getAccount());
		}
		else getCardBookingManage().showWarningPopup("Error booking. Try again.");
	}

	// check if the room is available through model
	private boolean checkRoomAvailable(Set<FlightSeat> selectRooms, Date startDate) {
		for (FlightSeat r : selectRooms) {
			if (!getModel().hotelSystem.roomIsAvailable(r, startDate)) {
				getCardBookingManage().showWarningPopup("Room: " + r.getRoomNumber() + " is not available.");
				return false;
			}
		}
		return true;
	}

	// check if inputed values are valid, returning a Set<Room>
	private Set<FlightSeat> checkValidInput(String[] rooms) {
		Set<FlightSeat> selectedRooms = new HashSet<>();

		for (String r : rooms) {
			if (r.length() < 2 || r.length() > 3) { // length is valid for format. e.g. 3F, 10F
				getCardBookingManage().showWarningPopup("Room: " + r + " is not valid input.");
				return null;
			}

			int floorNum = -1;
			try { floorNum = Integer.parseInt(r.substring(0, r.length() - 1)); }
			catch (Exception e) { System.out.println("Invalid floor number, please enter again: "); }

			char roomNum = r.charAt(r.length() - 1); // gets char from room string

			if (floorNum < 1 || floorNum > 9 || roomNum < 'A' || roomNum > 'J') {
				getCardBookingManage().showWarningPopup("Room: " + r + " is invalid.");
				return null;
			}

			FlightSeat toBook = new FlightSeat(floorNum, roomNum);
			selectedRooms.add(toBook);
		}
		return selectedRooms;
	}

	// Parses the string value, returning an array that is trimmed and uppercase
	private String[] parseRoom(String selectedRooms) {
		String[] parsedRoom = selectedRooms.split(",");
		for (int i = 0; i < parsedRoom.length; i++) parsedRoom[i] = parsedRoom[i].trim().toUpperCase();
		return parsedRoom;
	}

	// Checks if start date is in the past, relative to today.
	private boolean startBeforeToday(Date date) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		if (date.before(today.getTime())) return true;
		else return false;
	}

	// get card of booking manage user version
	private CardBookingManageUser getBookingUser() {
		if (clientView == null) return null;
		else return (CardBookingManageUser) clientView.getCardBookingManage();
	}
}
