package com.devclub.swiveldrive;

import java.awt.BorderLayout;

import javax.swing.*;

import com.devclub.swiveldrive.engine.Robot;
import com.devclub.swiveldrive.utils.Helper;

/**
 * The master UI class; the "View" in the MVC design pattern.
 * Operates in conjunction with the SwivelController.
 * @author Daniel Glus
 */
public class SwivelView {
	private JFrame frame;
	private SwivelPanel panel;
	private SwivelController controller;
	
	public SwivelView(SwivelController c) {
		controller = c;
	}
	
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
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel = new SwivelPanel(this);
		mainPanel.add(panel);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.setBounds(Helper.getCenteredBounds(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Returns the SwivelController's reference to the Robot.
	 * @return
	 */
	public Robot getRobot() {
		return controller.getRobot();
	}
}