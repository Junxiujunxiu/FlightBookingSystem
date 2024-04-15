/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import GUIcomponent.Base;
import Controller.MainController;
import javax.swing.JPanel;

public abstract class ViewMain extends JPanel {

	private Base base = new Base();
	private MainController controller;

	/**
	 * Abstract class used to create view that holds panels inside it
	 * @param controller to control
	 */
	public ViewMain(MainController controller) {
		this.controller = controller;
	}

	// returns controller
	public MainController getController() {
		return this.controller;
	}

	// adds to the Base (Panel)
	public void addToBase(JPanel p) {
		this.base.add(p);
	}

	// adds to the Base with gaps (Panel)
	public void addToBaseWithGap(JPanel...panel) {
		for (JPanel p : panel) this.base.addWithGap(p);
	}

	// returns base
	public Base getBasePanel() {
		return this.base;
	}

	// removes all from base
	public void resetBasePanel() {
		if (getBasePanel() != null) getBasePanel().removeAll();
	}
}
