package com.devclub.swiveldrive.engine;

import java.awt.*;

public class Robot {
	private Point location;
	private Dimension size;
	/**
	 * The position of the front wheels in respect to the robot; in degrees.
	 */
	private double frontWheelsPosition;
	/**
	 * The position of the back wheels in respect to the robot; in degrees.
	 */
	private double backWheelsPosition;

	public Robot(Dimension d) {
		size = d;
		location = new Point(0, 0);
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getX() {
		return location.x;
	}
	
	public int getY() {
		return location.y;
	}
	
	public int getWidth() {
		return size.width;
	}
	
	public int getHeight() {
		return size.height;
	}

	public double getFrontWheelsPosition() {
		return frontWheelsPosition;
	}

	public void setFrontWheelsPosition(double frontWheelsPosition) {
		if((frontWheelsPosition >= 0.0) && frontWheelsPosition <= 180.0) {
		this.frontWheelsPosition = frontWheelsPosition;
		} else {
			throw new IllegalArgumentException(String.format("Bad front wheel position: %f",
					frontWheelsPosition));
		}
	}

	public double getBackWheelsPosition() {
		return backWheelsPosition;
	}

	public void setBackWheelsPosition(double backWheelsPosition) {
		if((backWheelsPosition >= 0.0) && backWheelsPosition <= 180.0) {
			this.backWheelsPosition = backWheelsPosition;
			} else {
				throw new IllegalArgumentException(String.format("Bad back wheel position: %f",
						backWheelsPosition));
			}
	}
}