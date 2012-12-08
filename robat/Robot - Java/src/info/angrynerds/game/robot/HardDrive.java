package info.angrynerds.game.robot;

import info.angrynerds.game.utils.*;

import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;

/**
 * The brain of the robot - ESSENTIAL to the robot's operation
 * @author Daniel Glus and John Lhota
 */
public class HardDrive extends StoreItem implements Buyable {
	private String name;
	private int speed;
	private Image img;
	private List<Ability> programs;
	public int abilities;
	private boolean isBeingProgrammed;
	
	public HardDrive(String name, int price, int speed, String imgURL) {
		this.name = name;
		this.speed = speed;
		this.img = new ImageIcon(this.getClass().getResource("harddrives/"+imgURL)).getImage();
		isBeingProgrammed = false;
	}
	
	public void setBeingProgrammed(boolean b) {
		// TODO Auto-generated method stub
		isBeingProgrammed = b;
	}
	
	public boolean beingProgrammed() {
		return isBeingProgrammed;
	}
	
	public List<Ability> getPrograms() {
		return programs;
	}

	public void addProgram(Ability program) {
		programs.add(program);
	}
	
	public boolean hasProgram(String program) {
		return programs.contains(program);
	}
	
	public int getPrice() {
		return 50 + (speed * 5);
	}
	
	public int getSpeed() {
		return speed;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getH() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getW() {
		// TODO Auto-generated method stub
		return 100;
	}
}