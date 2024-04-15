/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Card.CardRegister;
import GUIcomponent.GetCardComponent;
import Controller.MainController;
import javax.swing.JComponent;

public class ViewRegister extends ViewMain implements GetCardComponent {
	private CardRegister cardRegister;
	
	/**
	 * Register view
	 * @param controller 
	 */
	public ViewRegister(MainController controller) {
		super(controller);
		init();
	}

	private void init() {
		super.resetBasePanel();
		this.cardRegister = new CardRegister(); // so fresh new form
		this.cardRegister.cancelButton.addActionListener(getController());
		this.cardRegister.submitButton.addActionListener(getController());
		super.addToBase(cardRegister);
	}

	@Override
	public JComponent[] getCardComponents() {
		JComponent[] components = new JComponent[6];
		components[0] = cardRegister.userFieldNew;
		components[1] = cardRegister.passFieldNew;
		components[2] = cardRegister.firstNameNew;
		components[3] = cardRegister.lastNameNew;
		components[4] = cardRegister.emailNew;
		components[5] = cardRegister.phoneNew;
		return components;
	}

	public CardRegister getCard() { return this.cardRegister; }
}
