/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import GUIcomponent.Button;
import GUIcomponent.Card;
import GUIcomponent.Container;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CardAccountEdit extends Card {

	public JButton cancelButton = new Button("Cancel", "#9399b2", 100, 25);
	public JButton saveButton = new Button("Save", "#94e2d5", 100, 25);
	public JTextField emailNew = new JTextField(30);
	public JTextField phoneNew = new JTextField(30);
	
	/**
	 * Creates panel for account edit.
	 */
	public CardAccountEdit() {
		super(500);
		final int W = this.WIDTH - 100;
		final int MARGIN = 100;

		this.add(new Container(W, 300, new CardAccountPic(300, 300)));
		this.add(new Container(W, MARGIN, "New email:", emailNew));
		this.add(new Container(W, MARGIN, "New phone:", phoneNew));
		this.add(new Container(W, 50, cancelButton, saveButton));
	}

	// Sets the email textfield to existing
	public void setTextEmail(String exsitingEmail) {
		emailNew.setText(exsitingEmail);
	}

	// Sets the phone textfield to existing
	public void setTextPhone(String exsitingPhone) {
		phoneNew.setText(exsitingPhone);
	}
}
