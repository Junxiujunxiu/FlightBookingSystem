/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Main.LoginAndRegister;
import Card.CardAccount;
import Card.CardBookingList;
import GUIcomponent.Card;
import GUIcomponent.Container;
import GUIcomponent.Text;
import GUIcomponent.Text.FontSize;
import Flight.Booking;
import View.ViewUserAccount;
import View.ViewGUI;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JOptionPane;


public abstract class UserAccountController extends MainController {

	public ViewUserAccount clientView;

	/**
	 * Abstract controller for client view
	 * @param view
	 * @param model 
	 */
	public UserAccountController(ViewGUI view, LoginAndRegister model) {
		super(view, model);
	}

	/**
	 * Confirms the logout from user.
	 */
	public void logoutHandler() {
		int result = JOptionPane.showConfirmDialog(getView(), "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION)  super.logout();
	}

	/**
	 * @return Container[] holding information about the account
	 */
	public Container[] getAccountInfo() {
		if (getModel().hotelSystem != null) {
			final int width = 249;
			final int margin = 44;
			String accountType = getAccount().toString();
			String fullname = getAccount().getFullName();
			String email = getAccount().email;
			String phone = getAccount().phone;
			Container[] accountInfo = new Container[4];
			accountInfo[0] = new Container(FlowLayout.LEFT, width, margin, new Text(accountType, FontSize.H1));
			accountInfo[1] = new Container(FlowLayout.LEFT, width, margin, new Text(fullname, FontSize.H2));
			accountInfo[2] = new Container(FlowLayout.LEFT, width, margin, new Text(email, FontSize.H2));
			accountInfo[3] = new Container(FlowLayout.LEFT, width, margin, new Text(phone, FontSize.H2));
			return accountInfo;
		}
		return null;
	}

	/**
	 * @return Container[] holding information about the hotel
	 */
	public Container[] getHotelInfo() {
		if (getModel().hotelSystem != null) {
			final int width = 450;
			final int margin = 20;
			String hotelname = getHotel().getHotelName();
			String address = getHotel().getAddress();
			String phone = getHotel().phone;
			String email = getHotel().email;
			Container[] hotelInfo = new Container[4];
			hotelInfo[0] = new Container(FlowLayout.LEFT, width, 30, new Text(hotelname, FontSize.H2));
			hotelInfo[1] = new Container(FlowLayout.LEFT, width, margin, new Text(address, FontSize.H3));
			hotelInfo[2] = new Container(FlowLayout.LEFT, width, margin, new Text(phone, FontSize.H3));
			hotelInfo[3] = new Container(FlowLayout.LEFT, width, margin, new Text(email, FontSize.H3));
			return hotelInfo;
		}
		return null;
	}

	// get bookings
	public List<Booking> getBookings() {
		return getModel().hotelSystem.getAllBookings();
	}

	// get the cardAccount panel
	public CardAccount getCardAccount() {
		if (clientView == null) return null;
		else return clientView.getCardAccount();
	}

	// get the cardbookinglist panel
	public CardBookingList getCardBookingList() {
		if (clientView == null) return null;
		else return clientView.getCardBookingList();
	}

	// get the cardbookingmanage panel
	public Card getCardBookingManage() {
		if (clientView == null) return null;
		else return clientView.getCardBookingManage();
	}
}
