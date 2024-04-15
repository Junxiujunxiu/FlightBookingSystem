/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Card.CardLogin;
import GUIcomponent.GetCardComponent;
import Controller.MainController;
import javax.swing.JComponent;

public class ViewLogin extends ViewMain implements GetCardComponent {
	private CardLogin cardLogin;

	/**
	 * Login view
	 * @param controller 
	 */
	public ViewLogin(MainController controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardLogin = new CardLogin(); // fresh new form
		this.cardLogin.loginButton.addActionListener(getController());
		this.cardLogin.registerButton.addActionListener(getController());
		super.addToBase(cardLogin);
	}

	@Override
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[2];
		components[0] = cardLogin.usernameField;
		components[1] = cardLogin.passwordField;
		return components;
	}

	public CardLogin getCard() { return this.cardLogin; }
}
