/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import GUIcomponent.Button;
import GUIcomponent.Card;
import GUIcomponent.Container;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CardLogin extends Card {
	public JButton loginButton = new Button("Login", "#94e2d5", 100, 25);
	public JButton registerButton = new Button("Register", "#9399b2", 100, 25);
	public JTextField usernameField = new JTextField(30);
	public JTextField passwordField = new JPasswordField(30);

	/**
	 * Creates Panel to show login
	 */
	public CardLogin() {
		super(500, 500);
		final int MARGIN = 70;
		final int W = super.WIDTH - 120;

		this.add(new Container(W, 100));
		this.add(new Container(W, MARGIN, "Username:", usernameField));
		this.add(new Container(W, MARGIN, "Password:", passwordField));
		this.add(new Container(W, 40, loginButton, registerButton));
	}
}
