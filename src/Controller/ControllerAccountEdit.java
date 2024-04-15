/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Main.LoginAndRegister;
import Card.CardAccountEdit;
import GUIcomponent.KeyPressListener;
import View.ViewAccountEdit;
import View.ViewGUI;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class ControllerAccountEdit extends MainController implements KeyPressListener {

	private ViewAccountEdit accountEditView;

	/**
	 * Controller for the account edit view.
	 * @param view, the current ViewGUI(JFrame)
	 * @param model, AppSession
	 */
	public ControllerAccountEdit(ViewGUI view, LoginAndRegister model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		this.accountEditView = new ViewAccountEdit(this); // new view with this as controller
		getCardAccountEdit().setTextEmail(getAccount().email); // set emailField to populate with current
		getCardAccountEdit().setTextPhone(getAccount().phone); // set phoneField to populate with current
		super.updateDisplay(accountEditView.getBasePanel()); // updates the display with account edit panel
		addEnterKeyListener(accountEditView.getCardComponents()); // add key listener to components
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if (getCardAccountEdit()!= null) {
		if (e.getSource() == getCardAccountEdit().cancelButton) super.renderClient();
		else if (e.getSource() == getCardAccountEdit().saveButton) saveHandler();
		}
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) getCardAccountEdit().saveButton.doClick();
				}
			});
		}
	}

	/**
	 * Will save the changes to db and then go back the render the client view with updated details
	 */
	private void saveHandler() {
		String newEmail = getCardAccountEdit().emailNew.getText();
		String newPhone = getCardAccountEdit().phoneNew.getText();
		if (!hasEmptyField(getCardAccountEdit(), newEmail, newPhone)) {
			getModel().hotelSystem.updateEmail(newEmail);
			getModel().hotelSystem.updatePhone(newPhone);
			super.renderClient();
			System.out.println("Details updated.");
		}
	}

	/**
	 * @return the accountEditView panel
	 */
	private CardAccountEdit getCardAccountEdit() {
		if (accountEditView == null) return null;
		else return accountEditView.getCard();
	}
}