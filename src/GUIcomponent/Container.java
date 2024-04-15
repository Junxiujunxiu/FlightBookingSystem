/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIcomponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Container extends JPanel {
	/**
	 * Extends JPanel, acts a container that holds other JComponenets
	 * 
	 * @param width of panel
	 * @param height of panel
	 */
	public Container(int width, int height) {
		this.setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(width, height));
	}

	public Container(int align, int width, int height, JComponent...components) {
		this(width, height);
		this.setLayout(new FlowLayout(align));
		for (JComponent c : components) this.add(c);
	}

	public Container(int width, int height, JComponent...components) {
		this(width, height);
		for (JComponent c : components) this.add(c);
	}

	public Container(int width, int height, String label) {
		this(width, height);
		this.add(new JLabel(label));
	}

	public Container(int width, int height, String label, JComponent component) {
		this(width, height, label);
		this.add(component);
	}
}
