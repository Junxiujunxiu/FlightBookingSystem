/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIcomponent;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class Base extends JPanel{
	/**
	 * Creates a base JPanel that acts as the background of the JFrame.
	 * This is what will be added to and removed from each time user goes to 
	 * a new view.
	 */
	public Base() {
		this.setBackground(Color.decode("#1e1e2e"));
		this.setLayout(new GridBagLayout());
	}

	/**
	 * Adds another JPanel, with margin, when multiple are added, there
	 * is a gap between them
	 */
	public void addWithGap(JPanel p) {
		GridBagConstraints constraints = new GridBagConstraints();
		int margin = 5;
		int cap = 0;
		constraints.insets = new Insets(cap, margin, cap, margin);
		this.add(p, constraints);
	}
}
