/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Card;

import GUIcomponent.Container;


public class CardInfo extends Container {

	/**
	 * Creates a Container that other containers
	 * @param width of container
	 * @param height of container
	 * @param containers to be held inside this container
	 */
	public CardInfo(int width, int height, Container...containers) {
		super(width, height);
		for (Container i : containers) this.add(i);
	}
}
