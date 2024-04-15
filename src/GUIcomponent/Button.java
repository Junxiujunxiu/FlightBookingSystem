/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIcomponent;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class Button extends JButton {

	/**
	 * Creates a button with a name and colour.
	 * @param name to display
	 * @param color to display
	 */
	public Button(String name, String color) {
		super(name);
		this.setBackground(Color.decode(color));
		this.setBorderPainted(false);
	}

	public Button(String name, String color, int width, int height) {
		this(name, color);
		this.setPreferredSize(new Dimension(width, height));
	}
}