package info.angrynerds.game.framework;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

import info.angrynerds.game.robot.*;
import info.angrynerds.game.robot.Robot;
import info.angrynerds.game.utils.InventoryItem;
import info.angrynerds.game.view.View;

public class BuildingTableState extends State implements MouseListener {
	private static final long serialVersionUID = 7873312382300414069L;
	
	private HashMap<Rectangle, String> mouseLookup;
	private Robot r;
	private Body body;
	private PowerSource powerSource;
	private HardDrive hardDrive;
	private Image bg;
	private Rectangle factoryButton, sellButton;

	Vector<Joint> jts;
	
	public BuildingTableState() {
		
		bg = new ImageIcon(this.getClass().getResource("table_woodsurface.png")).getImage();
		
		Vector<Joint> joints = new Vector<Joint>();

		joints.add(new Joint(Joint.TOP, 100));
		joints.add(new Joint(Joint.RIGHT, 100));
		joints.add(new Joint(Joint.BOTTOM, 70));
		joints.add(new Joint(Joint.BOTTOM, 140));
		joints.add(new Joint(Joint.LEFT, 100));
		body = new Body("Basic Body", joints, "basic.jpg", 200, 200, 200);
		
		r = new Robot("robot1", body);
		
		mouseLookup = new HashMap<Rectangle, String>();
		factoryButton = new Rectangle(500, 40, 50, 20);
		mouseLookup.put(factoryButton, "factory");
		
		sellButton = new Rectangle(500, 80, 50, 20);
		mouseLookup.put(sellButton, "sell");

		for(int i=0; i<Inventory.length(); i++) {
			Rectangle r = new Rectangle(5, i*(View.getFrameHeight()-50)/10 + (i+1)*5, 80, (View.getFrameHeight()-50)/10);
			mouseLookup.put(r, "Inventory:"+i);
		}
		
		mouseLookup.put(new Rectangle(90, 10, 50, 50), "PowerSourceHolder");
		mouseLookup.put(new Rectangle(160, 10, 50, 50), "HardDriveHolder");
		//mouseLookup.put(new Rectangle((View.getFrameWidth()-r.getW())/2, (View.getFrameHeight()-r.getH()*2)/2, r.getW(), r.getH()*2), "Body");
			jts = r.getBody().getJoints();
			for(int i=0; i<jts.size(); i++) {
				Rectangle rect = null;
				Joint j = jts.elementAt(i);
				switch( j.getSide() ) {
				case 0: {//TOP
					rect = new Rectangle((View.getFrameWidth()-r.getW())/2 + j.getDistFromCorner(), (View.getFrameHeight()-r.getH())/2 -10, 15, 10);
					break;
				}
				case 1: {//RIGHT
					rect = new Rectangle((View.getFrameWidth()-r.getW())/2 + r.getW(), (View.getFrameHeight()-r.getBody().getH())/2 + j.getDistFromCorner() , 10, 15);
					break;
				}
				case 2: {//BOTTOM
					rect = new Rectangle(View.getFrameWidth()-(View.getFrameWidth()-r.getW())/2 - j.getDistFromCorner(), (View.getFrameHeight()-r.getBody().getH())/2 + r.getH(), 15, 10);
					break;
				}
				case 3: {//LEFT
					rect = new Rectangle((View.getFrameWidth()-r.getW())/2, View.getFrameHeight()-(View.getFrameHeight()-r.getH())/2 - j.getDistFromCorner(), 10, 15);
					break;
				}
				}

				if(r != null) {
					mouseLookup.put(rect, "Joint:"+i);
				}
		}
	}

	/**
	 * Paints this state!
	 */
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, View.getFrameWidth(), View.getFrameHeight(), null);
		
		g.fillRect(90, 10, 50, 50);
		g.fillRect(160, 10, 50, 50);
			//g.drawImage(r.getImage(), View.getFrameWidth()/2 - r.getW(), View.getFrameHeight()/2 - r.getH(), r.getW(), r.getH()*2, null);
			g.setColor(Color.DARK_GRAY);
			g.fillRect((View.getFrameWidth()-r.getW())/2, (View.getFrameHeight()-r.getH())/2, r.getW(), r.getBody().getH());
			
			if(powerSource != null) {
				g.drawImage(powerSource.getImage(), 90, 10, 50, 50, null);
			}
			else {
				g.setColor(Color.PINK);
				g.drawString("PwrSrc", 95, 30);
			}

			if(hardDrive != null) {
				g.drawImage(hardDrive.getImage(), 160, 10, 50, 50, null);
			}
			else {
				g.setColor(Color.PINK);
				g.drawString("HrdDriv", 161, 30);
			}

			Vector<Joint> jts = r.getBody().getJoints();
			for(int i=0; i<jts.size(); i++) {
				Joint j = jts.elementAt(i);
				switch( j.getSide() ) {
				case Joint.TOP: {//TOP
					j.paint(g, new Point( (View.getFrameWidth()-r.getW())/2 + j.getDistFromCorner(), (View.getFrameHeight()-r.getH())/2 ));
					break;
				}
				case Joint.RIGHT: {//RIGHT
					j.paint(g, new Point( (View.getFrameWidth()-r.getW())/2 + r.getW(), (View.getFrameHeight()-r.getH())/2 + j.getDistFromCorner() ));
					break;
				}
				case Joint.BOTTOM: {//BOTTOM
					j.paint(g, new Point( View.getFrameWidth()-(View.getFrameWidth()-r.getW())/2 - j.getDistFromCorner(), (View.getFrameHeight()-r.getH())/2 + r.getH() ));
					break;
				}
				case Joint.LEFT: {//LEFT
					j.paint(g, new Point( (View.getFrameWidth()-r.getW())/2, View.getFrameHeight()-(View.getFrameHeight()-r.getH())/2 - j.getDistFromCorner() ));
					break;
				}
				}
			}
			
			Inventory.paint(g);
			
			g.setColor(Color.BLACK);
			g.fillRect(factoryButton.x, factoryButton.y, factoryButton.width, factoryButton.height);
			g.setColor(Color.WHITE);
			g.drawString("Factory",factoryButton.x+5, factoryButton.y+15);
			
			g.setColor(Color.BLACK);
			g.fillRect(sellButton.x, sellButton.y, sellButton.width, sellButton.height);
			g.setColor(Color.WHITE);
			g.drawString("Sell Bot",sellButton.x+5, sellButton.y+15);
			
			// Money
			g.setColor(Color.GREEN);
			g.drawString("$"+GameModel.getMoney()+".00", 90, View.getFrameHeight()-40);
			
			// Title
			g.setColor(Color.DARK_GRAY);
			g.drawString("Robot Building Table", (View.getFrameWidth() - 50)/2, 25);
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point location = e.getPoint();
		for(Rectangle rect:mouseLookup.keySet()) {
			if(rect.contains(location)) {
				/*if(mouseLookup.get(rect).equals("Body") && Inventory.selectedItem() instanceof Body) {
					body = (Body) Inventory.getSelectedItem();
				}
				else */if(mouseLookup.get(rect).startsWith("Joint:") && Inventory.selectedItem() instanceof Part) {
					Part temp = (Part)Inventory.selectedItem();
					Inventory.removeItem();
					Inventory.addItem((InventoryItem)r.getBody().getJoints().elementAt(Integer.parseInt(mouseLookup.get(rect).replace("Joint:",""))).getPart(), Inventory.getSelectedInt());
					r.setPart(Integer.parseInt(mouseLookup.get(rect).replace("Joint:","")), temp);
				}
				else if(mouseLookup.get(rect).equals("PowerSourceHolder") && Inventory.selectedItem() instanceof PowerSource) {
					InventoryItem temp = powerSource;
					this.powerSource = (PowerSource) Inventory.selectedItem();
					Inventory.removeItem();
					Inventory.addItem(temp);
				}
				else if(mouseLookup.get(rect).equals("HardDriveHolder") && Inventory.selectedItem() instanceof HardDrive) {
					InventoryItem temp = hardDrive;
					this.hardDrive = (HardDrive) Inventory.selectedItem();
					Inventory.removeItem();
					Inventory.addItem(temp);
				}
				else if(mouseLookup.get(rect).equals("factory")) {
					State.switchState("factory");
				}
				else if(mouseLookup.get(rect).equals("sell")) {
					GameModel.earnMoney(r.approximateValue());
					Vector<Joint> joints = new Vector<Joint>();
					joints.add(new Joint(Joint.TOP, 100));
					joints.add(new Joint(Joint.RIGHT, 100));
					joints.add(new Joint(Joint.BOTTOM, 70));
					joints.add(new Joint(Joint.BOTTOM, 140));
					joints.add(new Joint(Joint.LEFT, 100));
					body = new Body("Basic Body", joints, "basic.jpg", 200, 200, 200);
					r = new Robot("robot1", body);
				}
				else if(mouseLookup.get(rect).startsWith("Inventory:")) {
					Inventory.select(Integer.parseInt(mouseLookup.get(rect).replace("Inventory:", "")));
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "buildingtable";
	}

	@Override
	public boolean wantsMouseUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMouse() {
		// TODO Auto-generated method stub
		
	}

}
