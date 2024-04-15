/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import GUIcomponent.Card;
import GUIcomponent.Base;
import Main.LoginAndRegister;
import Flight.Account;
import Flight.Flight;
import System.Admin;
import System.User;
import View.ViewGUI;
import java.awt.event.ActionListener;

public abstract class MainController implements ActionListener {
	private ViewGUI view;
	private LoginAndRegister model;

	protected abstract void init();

	/**
	 * Abstract controller class that holds the ViewGUI and AppSession
	 * 
	 * @param view the JFrame
	 * @param model the AppSession.
	 */
	public MainController(ViewGUI view, LoginAndRegister model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * Factory pattern, will load relevant Controller based on type of system, user or admin.
	 */
	public void renderClient() {
		if (model != null) {
			if (model.hotelSystem instanceof User) new ControllerUser(view, model);
			
		}
	}

	/**
	 * Runs the AppSession logout method. Then creates a new app session and loades the loginController
	 */
	public void logout() { 
		this.getModel().logout();
		this.model = new LoginAndRegister();
		if (model != null) new ControllerLogin(view, getModel());
	}

	/**
	 * Takes a Card(JPanel) and the input data from JComponents, checking if any are empty.
	 * @param card to display warning
	 * @param fields array of String to check.
	 * @return 
	 */
	public boolean hasEmptyField(Card card, String...fields) {
		for (String s : fields) {
			if (s.isEmpty()) {
				card.showWarningPopup("Please fill in all empty sections...");
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the ViewGUI(JFrame) with a new Panel.
	 * @param newBase 
	 */
	public void updateDisplay(Base newBase) {
		this.view.updateDisplay(newBase);
	}

	// get methods
	public ViewGUI getView() { return this.view; }
	public LoginAndRegister getModel() { return this.model; }
	public Account getAccount() { return getModel().hotelSystem.getAccount(); }
	public Flight getHotel() { return getModel().hotelSystem.getHotel(); }
}
