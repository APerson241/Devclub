package info.angrynerds.game.robot;

import java.awt.*;
import java.util.*;

import info.angrynerds.game.utils.*;

public class Robot extends InventoryItem {
	private HardDrive hardDrive;
	private Body body;
	private PowerSource powerSrc;
//	private List<Part> parts; (commented out because all parts are stored in joints, which are stored in the body)
//	private List<Ability> abilities; (commented out because all the abilities will be stored in the hard-drive)
	private String name;
	private int value;
	//private RobotType type;
	public int abilities;
	
	public void paint(int w, int h, Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(w/2 - body.getW(), h/2 - body.getH(), body.getW()*2, body.getH()*2);

		Vector<Joint> jts = body.getJoints();
		for(int i=0; i<jts.size(); i++) {
			Joint j = jts.elementAt(i);
			switch( j.getSide() ) {
			case 0: {//TOP
				j.paint(g, new Point( (w-body.getW())/2 + j.getDistFromCorner(), (h-body.getH())/2 ));
				break;
			}
			case 1: {//RIGHT
				j.paint(g, new Point( (w-body.getW())/2 + body.getW(), (h-body.getH())/2 + j.getDistFromCorner() ));
				break;
			}
			case 2: {//BOTTOM
				j.paint(g, new Point( (w-(w-body.getW())/2) - j.getDistFromCorner(), (h-body.getH())/2 + body.getH() ));
				break;
			}
			case 3: {//LEFT
				j.paint(g, new Point( (w-body.getW())/2, h-(h-body.getH())/2 - j.getDistFromCorner() ));
				break;
			}
			}
		}
	}
	
	public Robot(String name, Body body) {
		this.name = name;
		this.body = body;
	}
	
	public void setPart(int joint, Part part) {
		if(body.partAtJoint(joint)) {
			body.removePart(joint);
		}
		body.setPart(joint, part);
		value += part.getPrice();
	}
	
	public void addPowerSource(PowerSource powerSrc) {
		this.setPowerSrc(powerSrc);
	}
	
	public void addAbility(Ability ability) {
		hardDrive.addProgram(ability);
		value += ability.complexity();
		for(int i=0; i<ability.getRequiredParts().size(); i++) {
			value += ability.getRequiredParts().elementAt(i).getPrice() * 0.8;
		}
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Value is proportional to the number of abilities a robot has.
	 * A robot gains abilities by obtaining the necessary parts, and being programmed.
	 * The more sophisticated the parts, the more skillfully the ability is done,
	 *  and the more valuable the robot is.
	 * @return The value of the robot.
	 */
	public int approximateValue() {
		return value;
	}
	
	public Part getPart(int part) {
		if(body.partAtJoint(part)) {
			return body.getJoints().get(part).getPart();
		}
		else {
			return null;
		}
	}
	
	public Body getBody() {
		return body;
	}
	
	//Methods Related to Building Robot
	
	public void addPart(Part part, int joint) {
		body.setPart(joint, part);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getH() {
		// TODO Auto-generated method stub
		return body.getH();
	}

	@Override
	public int getW() {
		// TODO Auto-generated method stub
		return body.getW();
	}

	public void setPowerSrc(PowerSource powerSrc) {
		this.powerSrc = powerSrc;
	}

	public PowerSource getPowerSrc() {
		return powerSrc;
	}

//	public void setParts(List<Part> parts) {
//		this.parts = parts;
//	}

//	public List<Part> getParts() {
//		List<Part> parts = new List<Part>();
//		for(int i=0; i<body.getJoints().size(); i++) {
//			parts.add(body.partAtJoint(i));
//		}
//		return parts;
//	}

//	public int getType() {
//		// TODO Auto-generated method stub
//		return type;
//	}
}
