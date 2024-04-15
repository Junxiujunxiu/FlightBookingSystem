/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIcomponent;

import java.awt.Font;
import javax.swing.JLabel;

public class Text extends JLabel {
	public enum FontSize {
		H1(20), H2(16), H3(12);

		private final int size;
		private FontSize(int size) { this.size = size; }
		public int getSize() { return this.size; }
	}

	/**
	 * Renders the text based on predefined font sizes.
	 * @param text to be rendered
	 * @param size, enum of predefined size
	 */
	public Text(String text, FontSize size) {
		super(text);
		this.setFont(new Font(getFont().getFontName(), Font.BOLD, size.getSize()));
	}
}
