/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CardAccountPic extends JPanel {
	
	/**
	 * Creates a JPanel that is a placeholder for account picture
	 * @param width, of pic
	 * @param height, of pic
	 */
	public CardAccountPic(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
	}
}