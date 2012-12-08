package info.angrynerds.game.map;

import info.angrynerds.game.framework.GameModel;
import info.angrynerds.game.utils.Buyable;
import info.angrynerds.game.utils.Console;
import info.angrynerds.game.utils.Paintable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Terrain implements Buyable, Paintable {
	private boolean isOwned;
	private int buildingType;
	/**
	 * Basic factory, place for storing parts,
	 * place specially optimized for making/programming robots, place for marketing.
	 */
	public static final int LAND=0, FACTORY=1, WAREHOUSE=2, LAB=3, OFFICE=4;
	private static Image landImg, factoryImg, warehouseImg, labImg, officeImg;
	private Image img;
	private static int width;
	private static int height;
	
	public Terrain(int type) {
		setOwned(false);
		
		buildingType = type;
		img = getImage(type);
	}

	public void purchase() {
		GameModel.spendMoney(getPrice());
		setOwned(true);
	}
	
	public void sell() {
		GameModel.earnMoney(getPrice());
		setOwned(false);
	}
	
	public void getForFree() {
		setOwned(true);
	}

	public int getPrice() {
		// TODO Auto-generated method stub
		return 2000;
	}

	public Image getImage(int type) {
		// TODO Auto-generated method stub
		return img;
	}

	public int getBuildingType() {
		// TODO Auto-generated method stub
		return buildingType;
	}
	
	public static void setDimensions(int w, int h) {
		width = w;
		height = h;
	}

	@Override
	public void paint(Graphics g, Point location) {
		// TODO Auto-generated method stub
		//g.drawImage(img, location.x, location.y, width, height, null);
		if(buildingType == LAND) {
			g.setColor(Color.GREEN);
		}
		else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(location.x, location.y, width, height);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
}