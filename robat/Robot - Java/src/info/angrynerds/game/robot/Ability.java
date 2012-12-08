package info.angrynerds.game.robot;

import java.awt.Image;
import java.util.Vector;

import info.angrynerds.game.utils.Buyable;

public class Ability implements Buyable {
	private int complexity;
	private String name; // i.e. "How to Cook"
	private String description; // i.e. "This task lets your robot cook, making people looking for robotic chefs want to buy him."
	private Vector<Part> requiredParts;
	
	public int complexity() {
		return complexity;
	}
	
	public String getName() {
		return name;
	}
	
	public String description() {
		return description;
	}
	
	public Vector<Part> getRequiredParts() {
		return requiredParts;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
