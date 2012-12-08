package info.angrynerds.game.robot;

import java.awt.*;
import javax.swing.ImageIcon;

import info.angrynerds.game.utils.*;

public class Part extends StoreItem implements Paintable, Buyable {
	private String name;
	private int cost;
	private Image img;
	private int w, h; // These are measured as if the part were pointing straight up (like the robot's head)
	
	public Part(String name, String imgURL, int cost, int w, int h) {
		this.name = name;
		this.cost = cost;
		this.img = new ImageIcon(this.getClass().getResource("parts/"+imgURL)).getImage();
		this.w = w;
		this.h = h;
	}
	
	public void paint(Graphics g, Point location) {
		g.drawImage(img, location.x, location.y, 50, 50, null);
	}
	
	public int getValue() {
		return cost;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return cost;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
