/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import GUIcomponent.Button;
import GUIcomponent.Card;
import GUIcomponent.Container;
import javax.swing.JButton;

public class CardAccount extends Card {
	public JButton editAccountButton = new Button("Edit", "#9399b2", 100, 25);
	public JButton logoutButton = new Button("Logout", "#94e2d5", 100, 25);
	
	/**
	 * Creates a Panel that hold the user account info.
	 * 
	 * @param accountInfo, account info added into the container class
	 */
	public CardAccount(Container[] accountInfo) {
		super(300);
		final int W = this.WIDTH - 50;
		this.add(new Container(W, 300, new CardAccountPic(250, 250)));
		this.add(new CardInfo(W, 250, accountInfo));
		this.add(new Container(W, 50, editAccountButton, logoutButton));
	}
}
