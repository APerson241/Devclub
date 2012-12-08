package info.angrynerds.game.robot;

import java.awt.*;

import javax.swing.ImageIcon;

import info.angrynerds.game.utils.Paintable;

public class Joint implements Paintable {
	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;
	
	
	
	private Image jointImg;
	
	private int side;
	private int distFromCorner;
	Part part;
	
	public Joint(int side, int dist) {
		this.setSide(side);
		setDistFromCorner(dist);
		
		jointImg = new ImageIcon(this.getClass().getResource("joint.png")).getImage();
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public void removePart() {
		part = null;
	}
	
	public boolean hasPart() {
		if(part!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Part getPart() {
		return part;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public int getDistFromCorner() {
		return distFromCorner;
	}

	public void setDistFromCorner(int distFromCorner) {
		this.distFromCorner = distFromCorner;
	}

	@Override
	public void paint(Graphics g, Point location) {
		// TODO Auto-generated method stub

		if(side==TOP) {
			g.drawImage(jointImg, location.x, location.y-10, 15, 10, null);
		}
		else if(side==RIGHT) {
			g.drawImage(jointImg, location.x, location.y, 10, 15, null);
		}
		else if(side==BOTTOM) {
			g.drawImage(jointImg, location.x, location.y, 15, 10, null);
		}
		else if(side==LEFT) {
			g.drawImage(jointImg, location.x-10, location.y, 10, 15, null);
		}
		
		if(hasPart()) {
			if(side==TOP) {
				g.drawImage(part.getImage(), location.x-10, location.y-part.getH()-10, part.getW(), part.getH(), null);
			}
			else if(side==RIGHT) {
				g.drawImage(part.getImage(), location.x+10, location.y, part.getH()-(part.getH()/2), part.getW(), null);
			}
			else if(side==BOTTOM) {
				g.drawImage(part.getImage(), location.x, location.y+10, part.getW(), part.getH(), null);
			}
			else if(side==LEFT) {
				g.drawImage(part.getImage(), location.x-part.getW()-10, location.y+(part.getH()/2), part.getH(), part.getW(), null);
			}
		}
	}
}
