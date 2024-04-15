/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Main.LoginAndRegister;
import Card.CardRegister;
import GUIcomponent.KeyPressListener;
import View.ViewGUI;
import View.ViewRegister;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class ControllerRegister extends MainController implements KeyPressListener {

	private ViewRegister registerView;

	/**
	 * Controller for registration
	 * @param view
	 * @param model 
	 */
	public ControllerRegister(ViewGUI view, LoginAndRegister model) {
		super(view, model);
		init();
	}

	@Override
	protected void init() {
		this.registerView = new ViewRegister(this);
		super.updateDisplay(registerView.getBasePanel());
		addEnterKeyListener(registerView.getCardComponents());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getCardRegister()!= null) {
			if (e.getSource() == getCardRegister().cancelButton) cancelHandler();
			else if (e.getSource() == getCardRegister().submitButton) submitHandler();
		}
	}

	@Override
	public void addEnterKeyListener(JComponent... components) {
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) { // add enter key listener to JComponents
					if(e.getKeyCode() == KeyEvent.VK_ENTER) getCardRegister().submitButton.doClick();
				}
			});
		}
	}

	// Cancels and loads new login
	private void cancelHandler() {
		new ControllerLogin(getView(), getModel());
	}

	/**
	 * Passes data into the model to check if username exists, and register users, saves to db
	 * and then logs user in automatically.
	 */
	private void submitHandler() {
		String usr = getCardRegister().userFieldNew.getText().toLowerCase();
		String pwd = getCardRegister().passFieldNew.getText();
		String fname = getCardRegister().firstNameNew.getText();
		String lname = getCardRegister().lastNameNew.getText();
		String email = getCardRegister().emailNew.getText().toLowerCase();
		String phone = getCardRegister().phoneNew.getText();

		if (!hasEmptyField(getCardRegister(), usr, pwd, fname, lname, email, phone)) {
			if (!getModel().checkUserNameExists(usr)) {
				getModel().registerPortal(usr, pwd, fname, lname, email, phone);
				if(getModel().loginPortal(usr, pwd)) super.renderClient();
				else getCardRegister().showWarningPopup("Incorrect username or password!");
			}
			else getCardRegister().showWarningPopup("Username is taken.");
		}
	}

	private CardRegister getCardRegister() {
		if (registerView == null) return null;
		else return registerView.getCard();
	}
}
