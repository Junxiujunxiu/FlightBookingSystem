/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import GUIcomponent.Card;
import GUIcomponent.Text;
import GUIcomponent.Container;
import GUIcomponent.Text.FontSize;

public abstract class CardBookingManage extends Card {

	public final int W;

	/**
	 * Abstract class Panel for managing bookings.
	 * @param title of panel
	 * @param width of panel
	 * @param hotelInfo of hotel
	 */
	public CardBookingManage(String title, int width, Container...hotelInfo) {
		super(500);
		this.W = this.WIDTH - 50;
		this.add(new Container(W, 50, new Text(title, FontSize.H1)));
		this.add(new CardInfo(W, 125, hotelInfo));
	}
}