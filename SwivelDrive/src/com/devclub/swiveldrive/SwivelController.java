package com.devclub.swiveldrive;

import java.awt.Dimension;

import com.devclub.swiveldrive.engine.Robot;

/**
 * The "controller" class of the MVC design pattern.
 * @author Daniel Glus (APerson241)
 */
public class SwivelController {
	private SwivelView view;
	private Robot robot;
	
	public static void main(String[] args) {
		(new SwivelController()).go();
	}
	
	/**
	 * The method that starts it all.
	 */
	public void go() {
		robot = new Robot(new Dimension(100, 161));
		view = new SwivelView(this);
		view.setVisible(true);
	}
	
	public Robot getRobot() {
		return robot;
	}
}