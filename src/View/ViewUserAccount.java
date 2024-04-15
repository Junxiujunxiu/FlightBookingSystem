/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import GUIcomponent.Card;
import Card.CardBookingManage;
import Card.CardBookingList;
import Card.CardAccount;
import Controller.MainController;
import Flight.Booking;
import java.util.List;

public abstract class ViewUserAccount extends ViewMain {
	private CardAccount cardAccount;
	private CardBookingList cardBookingList;
	public CardBookingManage cardBookingManage;

	/**
	 * Abstract view that holds relevant elements for displaying client
	 * @param controller 
	 */
	public ViewUserAccount(MainController controller) {
		super(controller);
	}

	public CardAccount getCardAccount() { return this.cardAccount; }
	public CardBookingList getCardBookingList() { return this.cardBookingList; }
	public Card getCardBookingManage() { return this.cardBookingManage; }

	public void setCardAccount(CardAccount c) { this.cardAccount = c; }
	public void setCardBookingManage(CardBookingManage c) { this.cardBookingManage = c; }
	public void setCardBookingList(CardBookingList c) { this.cardBookingList = c; }
}
