package info.angrynerds.game.robot;

import java.awt.*;

import javax.swing.ImageIcon;

import info.angrynerds.game.utils.*;

public class PowerSource extends StoreItem implements Paintable, Buyable {
	
	private String name;
	private int price;
	private int efficiency;
	private Image img;

	public PowerSource(String name, int cost, int efficiency, String imgURL) {
		this.name = name;
		this.setEfficiency(efficiency);
		this.price = cost;
		this.img = new ImageIcon(this.getClass().getResource("powersources/"+imgURL)).getImage();
	}

	public int getPrice() {
		return price;
	}

	@Override
	public Image getImage() {
		return img;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getH() {
		return 100;
	}

	@Override
	/**
	 * Gets the width of the object, when painted
	 * @returns 100
	 */
	public int getW() {
		return 100;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public int getEfficiency() {
		return efficiency;
	}

	@Override
	public void paint(Graphics g, Point location) {
		// TODO Auto-generated method stub
		g.drawImage(img, location.x, location.y, 50, 50, null);
	}

}
