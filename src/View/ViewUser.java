/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import GUIcomponent.Container;
import Card.CardAccount;
import Card.CardBookingList;
import Card.CardBookingManageUser;
import Controller.MainController;
import Flight.Booking;
import java.util.List;

public class ViewUser extends ViewUserAccount {

	public CardBookingManageUser cardBookingManage;

	/**
	 * View for user client
	 * @param controller, controller
	 * @param accountInfo, container with user account info
	 * @param hotelInfo, container with hotel info
	 * @param bookings, all user bookings
	 */
	public ViewUser(MainController controller, Container[] accountInfo, Container[] hotelInfo, List<Booking> bookings) {
		super(controller);
		init(accountInfo, hotelInfo, bookings);
	}

	private void init(Container[] accountInfo, Container[] hotelInfo, List<Booking> bookings) {
		super.resetBasePanel();
		if (accountInfo.length > 0) setCardAccount(new CardAccount(accountInfo));
		getCardAccount().editAccountButton.addActionListener(getController()); // add controller
		getCardAccount().logoutButton.addActionListener(getController());

		this.cardBookingManage = new CardBookingManageUser(hotelInfo);
		this.cardBookingManage.cancelButton.addActionListener(getController());
		this.cardBookingManage.bookButton.addActionListener(getController());
		setCardBookingManage(cardBookingManage);

		setCardBookingList(new CardBookingList(bookings)); // card booking list showing user booking
		super.addToBaseWithGap(getCardAccount(), cardBookingManage, getCardBookingList()); // add to base
	}
}
