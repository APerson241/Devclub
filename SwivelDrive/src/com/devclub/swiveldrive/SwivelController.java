package com.devclub.swiveldrive;

/**
 * The "controller" class of the MVC design pattern.
 * @author Daniel Glus (APerson241)
 */
public class SwivelController {
	private SwivelView view;
	
	public static void main(String[] args) {
		(new SwivelController()).go();
	}
	
	/**
	 * The method that starts it all.
	 */
	public void go() {
		view = new SwivelView();
		view.setVisible(true);
	}
}