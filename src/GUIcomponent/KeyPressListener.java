/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUIcomponent;

import javax.swing.JComponent;

public interface KeyPressListener {

	/**
	 * Adds listener to array of JComponents.
	 * @param components that will have listener added.
	 */
	abstract void addEnterKeyListener(JComponent... components);
}