/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.MainController;
import Controller.ControllerLogin;
import View.ViewGUI;

public class Main {

    /***
     * Application entry point.
     */
	public static void main(String[] args) {
		ViewGUI view = new ViewGUI();
		MainController controller = new ControllerLogin(view, new LoginAndRegister());
	}

	
}
