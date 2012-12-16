package com.devclub.swiveldrive;

import java.awt.*;

import javax.swing.*;

import com.devclub.swiveldrive.engine.Robot;

/**
 * The main drawing panel that appears in the SwivelView.
 * @author Daniel Glus
 */
@SuppressWarnings("serial")
public class SwivelPanel extends JPanel {
	private SwivelView view;
	private Robot robot;
	
	public static final int MARGIN = 25;
	
	public SwivelPanel(SwivelView v) {
		view = v;
		robot = view.getRobot();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(MARGIN, MARGIN, getWidth() - MARGIN, getHeight() - MARGIN); // Border
		g.drawLine((int)Math.floor(this.getWidth()/2), 0,
				(int)Math.floor(this.getWidth()/2), this.getHeight()); // Coordinate line
		g.drawLine(0, (int)Math.floor(this.getHeight()/2), this.getWidth(),
				(int)Math.floor(this.getHeight()/2)); // Coordinate line
		g.setColor(Color.GREEN);
		g.fillRect(robot.getX(), robot.getY(), robot.getWidth(), robot.getHeight());
	}
}