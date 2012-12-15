package com.devclub.swiveldrive;

import javax.swing.*;

import com.devclub.swiveldrive.utils.Helper;

/**
 * The master UI class; the "View" in the MVC design pattern.
 * Operates in conjunction with the SwivelController.
 * @author Daniel Glus
 */
public class SwivelView {
	private JFrame frame;
	
	/**
	 * Sets whether the view is visible.
	 * @param visible Whether or not the view is visible.
	 */
	public void setVisible(boolean visible) {
		if(frame == null) buildGUI();
		frame.setVisible(visible);
	}
	
	private void buildGUI() {
		frame = new JFrame("The SwivelDrive Simulator");
		frame.setBounds(Helper.getCenteredBounds(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}