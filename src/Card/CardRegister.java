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

public class CardRegister extends Card {
	public JButton cancelButton = new Button("Cancel", "#9399b2", 100, 25);
	public JButton submitButton = new Button("Submit", "#94e2d5", 100, 25);
	public JTextField userFieldNew = new JTextField(30);
	public JTextField passFieldNew = new JPasswordField(30);
	public JTextField firstNameNew = new JTextField(30);
	public JTextField lastNameNew = new JTextField(30);
	public JTextField emailNew = new JTextField(30);
	public JTextField phoneNew = new JTextField(30);

	/**
	 * Creates panel to show registration.
	 */
	public CardRegister() {
		super(500, 500);
		final int MARGIN = 50;
		final int W = super.WIDTH - 120;

		this.add(new Container(W, MARGIN, "New Username:", userFieldNew));
		this.add(new Container(W, MARGIN, "New Password:", passFieldNew));
		this.add(new Container(W, MARGIN, "First name:", firstNameNew));
		this.add(new Container(W, MARGIN, "Last name:", lastNameNew));
		this.add(new Container(W, MARGIN, "Email address:", emailNew));
		this.add(new Container(W, MARGIN, "Phone number:", phoneNew));
		this.add(new Container(W, 40, cancelButton, submitButton));
	}
}
