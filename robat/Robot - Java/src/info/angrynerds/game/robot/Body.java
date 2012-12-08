package info.angrynerds.game.robot;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

import info.angrynerds.game.utils.Buyable;
import info.angrynerds.game.utils.StoreItem;

public class Body extends StoreItem implements Buyable {
	
	private String name;
	private Image img;
	private Vector<Joint> joints;
	private int price;
	private int w, h;

	public Body(String name, Vector<Joint> joints, String imgURL, int w, int h, int cost) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.joints = joints;
		setImg(new ImageIcon(this.getClass().getResource("bodies/"+imgURL)).getImage());
		this. w = w;
		this.h = h;
		price = cost;
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}

	public boolean partAtJoint(int joint) {
		// TODO Auto-generated method stub
		if(joints.get(joint).hasPart()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setPart(int joint, Part part) {
		// TODO Auto-generated method stub
		if(!joints.get(joint).hasPart()) {
			joints.get(joint).removePart();
		}
		joints.get(joint).setPart(part);
	}

	public void removePart(int joint) {
		// TODO Auto-generated method stub
		
	}

	public Vector<Joint> getJoints() {
		// TODO Auto-generated method stub
		return joints;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Image getImg() {
		return img;
	}

}
