package info.angrynerds.game.framework;

import info.angrynerds.game.robot.Body;
import info.angrynerds.game.robot.HardDrive;
import info.angrynerds.game.robot.Joint;
import info.angrynerds.game.robot.Part;
import info.angrynerds.game.robot.PowerSource;
import info.angrynerds.game.utils.Console;
import info.angrynerds.game.utils.InventoryItem;
import info.angrynerds.game.utils.StoreItem;
import info.angrynerds.game.view.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Vector;

public class StoreState extends State implements MouseListener {

	private Vector<StoreItem> items;
	private HashMap<Rectangle, String> mouseLookup;
	private Rectangle menuButton, mapButton, factoryButton;
	private Font buttonFont;
	private int scrollAmt;
	private Rectangle up, down;
	private boolean wantsMouseUpdate;

	public StoreState() {

		mouseLookup = new HashMap<Rectangle, String>();
		
		items = new Vector<StoreItem>();

		up = new Rectangle (580, 30, 60, 20);
		down = new Rectangle (580, 70, 90, 20);
		scrollAmt = 0;
		
		updateMouse();

		loadAllParts();
		loadAllHardDrives();
		loadAllPowerSources();
		//loadAllBodies();
		
//		int maxWidth = 0;
//		int maxHeight = 0;
//		
//		for(int i=0; i<items.size(); i++) {//Find the maximum width & height of any store item
//			if(((StoreItem) items.elementAt(i)).getW() > maxWidth) {
//				maxWidth = ((StoreItem) items.elementAt(i)).getW();
//			}
//			if(((StoreItem) items.elementAt(i)).getH() > maxHeight) {
//				maxHeight = ((StoreItem) items.elementAt(i)).getH();
//			}
//		}
//		
//		int widthSofar = 0;

//		int y = 0;
//		int x = 0;
//		for(y=0; y*10+x<items.size(); y++) {
//			for(x=0; x*maxWidth<=View.getFrameWidth() && y*10+x<items.size(); x++) {
//				int h = ((StoreItem) items.elementAt(y*10+x)).getH();
//				int w = ((StoreItem) items.elementAt(y*10+x)).getW();
//				mouseLookup.put(new Rectangle(90+x*maxWidth+x*5, 20+y*maxHeight+y*5, w, h), "StoreItem:"+y*10+x);
//			}
//		}
		
		int w, h, heightSofar = 0;
		for(int y=0; y<items.size(); y++) {
			w = ((StoreItem) items.elementAt(y)).getW();
			h = ((StoreItem) items.elementAt(y)).getH();
			mouseLookup.put(new Rectangle( 90, scrollAmt+20+heightSofar+y*15, w, h), "StoreItem:"+y);
			heightSofar += items.elementAt(y).getH();
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());

		//Paint the actual store
		paintBuyables(g);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, View.getFrameHeight()-140, View.getFrameWidth(), 140); //Menu Panel

		g.setColor(Color.green);
		g.setFont(new Font(Font.MONOSPACED, 0, 15));
		g.drawString("$"+GameModel.getMoney()+".00", 90, View.getFrameHeight()-120);

		g.setFont(buttonFont);
		g.setColor(Color.BLACK);
		g.fillRect(menuButton.x, menuButton.y, menuButton.width, menuButton.height);
		g.setColor(Color.WHITE);
		g.drawString("Main Menu", menuButton.x+8, menuButton.y+15);

//		g.setColor(Color.BLACK);
//		g.fillRect(mapButton.x, mapButton.y, mapButton.width, mapButton.height);
//		g.setColor(Color.WHITE);
//		g.drawString("View Map", mapButton.x+10, mapButton.y+15);

		g.setColor(Color.BLACK);
		g.fillRect(factoryButton.x, factoryButton.y, factoryButton.width, factoryButton.height);
		g.setColor(Color.WHITE);
		g.drawString("Go to Factory", factoryButton.x+8, factoryButton.y+15);

		Inventory.paint(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(up.x, up.y, up.width, up.height);
		g.fillRect(down.x, down.y, down.width, down.height);
		g.setColor(Color.WHITE);

		g.drawString("scroll up", up.x+5, up.y+10);
		g.drawString("scroll down", down.x+5, down.y+10);
	}

	private void paintBuyables(Graphics g) {
		int maxWidth = 0;
		int maxHeight = 0;
		
		int heightSoFar = 0;
		for(int i=0; i<items.size(); i++) {//Find the maximum width & height of any store item
			if(((StoreItem) items.elementAt(i)).getW() > maxWidth) {
				maxWidth = ((StoreItem) items.elementAt(i)).getW();
			}
			if(((StoreItem) items.elementAt(i)).getH() > maxHeight) {
				maxHeight = ((StoreItem) items.elementAt(i)).getH();
			}
		}
		
//		int y = 0;
//		int x = 0;
		int w, h;
//		for(y=0; y*10+x<items.size(); y++) {
//			for(x=0; widthSoFar+items.elementAt(y*10+x).getW()<(View.getFrameWidth()-90) && y*10+x<items.size(); x++) {
//				w = ((StoreItem) items.elementAt(y*10+x)).getH();
//				h = ((StoreItem) items.elementAt(y*10+x)).getW();
//				g.drawImage(((StoreItem) items.elementAt(y*10+x)).getImage(), 90+x*maxWidth+x*5, 20+y*maxHeight+y*5, w, h, null);
//				
////				if(items.elementAt(y*10+x) instanceof Part) {
////					g.setColor(Color.ORANGE);
////				}
////				else if(items.elementAt(y*10+x) instanceof HardDrive) {
////					g.setColor(Color.GREEN);
////				}
////				else if(items.elementAt(y*10+x) instanceof PowerSource) {
////					g.setColor(Color.BLUE);
////				}
////				g.drawRect(90+x*maxWidth+x*5, 20+y*maxHeight+y*5, w, h);
//				
//				g.setColor(Color.RED);
//				String cost = "$"+((StoreItem) items.elementAt(y*10+x)).getPrice();
//				g.drawString(cost, 90+x*maxWidth+x*5, 20+y*maxHeight+y*5+h);
//				
//				widthSoFar += items.elementAt(y*10+x).getW();
//			}
//		}
		
		for(int y=0; y<items.size(); y++) {
			w = ((StoreItem) items.elementAt(y)).getW();
			h = ((StoreItem) items.elementAt(y)).getH();
			g.drawImage(((StoreItem) items.elementAt(y)).getImage(), 90, scrollAmt+20+heightSoFar+y*10, w, h, null);
			g.setColor(Color.BLACK);
			//g.fillRect(90, 20+heightSoFar+5, View.getFrameWidth()-90, 5);
			
//			if(items.elementAt(y*10+0) instanceof Part) {
//				g.setColor(Color.ORANGE);
//			}
//			else if(items.elementAt(y*10+0) instanceof HardDrive) {
//				g.setColor(Color.GREEN);
//			}
//			else if(items.elementAt(y*10+0) instanceof PowerSource) {
//				g.setColor(Color.BLUE);
//			}
//			g.drawRect(90+0*0+0*5, 20+y*maxHeight+y*5, w, h);
			
			heightSoFar += maxHeight;
			
			g.setColor(Color.RED);
			
			String name = ((StoreItem) items.elementAt(y)).getName();
			g.drawString(name, 120+w, heightSoFar+y*15+scrollAmt-(h/2));
			
			String cost = "$"+((StoreItem) items.elementAt(y)).getPrice();
			g.drawString(cost, 300+w, heightSoFar+y*15+scrollAmt-(h/2));
		}
	}

	public void purchase(int item) {
		if(GameModel.getMoney() >= items.elementAt(item).getPrice()) {
			GameModel.spendMoney(((StoreItem) items.elementAt(item)).getPrice());
			Inventory.addItem((InventoryItem) items.elementAt(item));
		}

//		if(MapState.hasRoomInWarehouse()) {
//			Console.println(item.getName() + " sent to warehouse #" + MapState.sendToWarehouse());
//		}
	}

	public String getName() {
		return "store";
	}
	
	
	
	//METHODS RELATED TO LOADING STOREITEMS
	private void loadAllParts() {
		Vector<Part> parts = new Vector<Part>();

		parts.add(new Part("Basic Arm", "arm.png", 250, 70, 50));
		parts.add(new Part("Leg", "leg.png", 350, 30, 100));
		parts.add(new Part("Sophisticated Arm", "arm_fancy.png", 700, 40, 30));
		parts.add(new Part("Wheels", "wheels.png", 150, 120, 80));
		parts.add(new Part("Rocket", "rocket.png", 925, 60, 70));
		parts.add(new Part("Super Chill Laser", "laser.png", 820, 20, 100));
		parts.add(new Part("Basic Head", "head_basic.png", 600, 100, 80));
		parts.add(new Part("Advanced Head", "head_advanced.png", 800, 100, 80));
		parts.add(new Part("Scanner", "scanner.png", 100, 70, 100));

		items.addAll(parts);
	}

	private void loadAllBodies() {
		Vector<Body> bodies = new Vector<Body>();
		
		Vector<Joint> joints = new Vector<Joint>();

		joints.add(new Joint(Joint.TOP, 50));
		joints.add(new Joint(Joint.RIGHT, 60));
		joints.add(new Joint(Joint.BOTTOM, 50));
		joints.add(new Joint(Joint.LEFT, 60));
		bodies.add(new Body("Basic Body", joints, "basic.jpg", 150, 50, 100));

		joints.clear();
		joints.add(new Joint(Joint.TOP, 50));
		joints.add(new Joint(Joint.RIGHT, 60));
		joints.add(new Joint(Joint.BOTTOM, 30));
		joints.add(new Joint(Joint.BOTTOM, 60));
		joints.add(new Joint(Joint.LEFT, 60));
		bodies.add(new Body("Android Body", joints, "fancy.jpg", 130, 50, 150));

//		joints.clear();
//		joints.add(new Joint(Joint.TOP, 50));
//		joints.add(new Joint(Joint.RIGHT, 50));
//		joints.add(new Joint(Joint.RIGHT, 110));
//		joints.add(new Joint(Joint.BOTTOM, 30));
//		joints.add(new Joint(Joint.BOTTOM, 60));
//		joints.add(new Joint(Joint.LEFT, 50));
//		joints.add(new Joint(Joint.LEFT, 110));
//		bodies.add(new Body("Four-Armed", joints, "4armed.png", 150, 80, 300));

		items.addAll(bodies);
	}

	private void loadAllHardDrives() {
		Vector<HardDrive> hardDrives = new Vector<HardDrive>();

		hardDrives.add(new HardDrive("Extremely Slow Hard Drive", 150, 30, "harddrive_slow.png"));
		hardDrives.add(new HardDrive("Medium Speed Hard Drive", 500, 90, "harddrive_medium.png"));
		hardDrives.add(new HardDrive("High End ", 750, 140, "harddrive_sleek.png"));
		hardDrives.add(new HardDrive("Really, Really Fast", 1000, 280, "harddrive_awesome.png"));
		hardDrives.add(new HardDrive("Quantum Computer", 5000, 8200, "quantum2.png"));

		items.addAll(hardDrives);
	}

	private void loadAllPowerSources() {
		Vector<PowerSource> powerSources = new Vector<PowerSource>();

		powerSources.add(new PowerSource("Gasoline Engine", 100, 45, "gas.png"));
		powerSources.add(new PowerSource("Battery", 110, 65, "battery.png"));
		powerSources.add(new PowerSource("Solar Panels", 150, 90, "solar.png"));
		powerSources.add(new PowerSource("Cold Fusion Reactor", 1200, 965, "fusion.png"));

		items.addAll(powerSources);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point location = e.getPoint();
		for(Rectangle rect:mouseLookup.keySet()) {
			if(rect.contains(location)) {
				if(mouseLookup.get(rect).equals("up")) {
					scrollAmt += 30;
					wantsMouseUpdate = true;
					
//					for(Rectangle r:mouseLookup.keySet()) {
//						if(mouseLookup.get(r).startsWith("StoreItem:")) {
//							mouseLookup.remove(r);
//						}
//					}
//					
//					int w, h, heightSofar = 0;
//					for(int y=0; y<items.size(); y++) {
//						w = ((StoreItem) items.elementAt(y)).getW();
//						h = ((StoreItem) items.elementAt(y)).getH();
//						mouseLookup.put(new Rectangle( 90, scrollAmt+20+heightSofar+y*15, w, h), "StoreItem:"+y);
//						heightSofar += items.elementAt(y).getH();
//					}
				}
				else if(mouseLookup.get(rect).equals("down")) {
					scrollAmt -= 30;
					wantsMouseUpdate = true;
					
//					for(Rectangle r:mouseLookup.keySet()) {
//						if(mouseLookup.get(r).startsWith("StoreItem:")) {
//							mouseLookup.remove(r);
//						}
//					}
//					
//					int w = 0, h = 0, heightSofar = 0;
//					for(int y=0; y<items.size(); y++) {
//						w = ((StoreItem) items.elementAt(y)).getW();
//						h = ((StoreItem) items.elementAt(y)).getH();
//						mouseLookup.put(new Rectangle( 90, scrollAmt+20+heightSofar+y*15, w, h), "StoreItem"+y);
//						heightSofar += items.elementAt(y).getH();
//					}
				}
				else if(mouseLookup.get(rect).startsWith("StoreItem")) {
					purchase(Integer.parseInt(mouseLookup.get(rect).replace("StoreItem:","")));
				}
				else if(mouseLookup.get(rect).startsWith("State:")) {
					State.switchState( mouseLookup.get(rect).replace("State:","") );
				}

				if(mouseLookup.get(rect).startsWith("Inventory:")) {
					Inventory.select( Integer.parseInt(mouseLookup.get(rect).replace("Inventory:", "")) );
				}
				else {
					Inventory.selectNone();
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
	
//	@Override
//	public boolean wantsMouseUpdate() {
//		return wantsMouseUpdate;
//		
//	}
//	
//	@Override
//	public void setMouseUpdate() {
//		wantsMouseUpdate = false;
//		
//	}
	
	@Override
	public boolean wantsMouseUpdate() {
		// TODO Auto-generated method stub
		return wantsMouseUpdate;
	}

	@Override
	public void updateMouse() {
		// TODO Auto-generated method stub


		mouseLookup.clear();
		
		mouseLookup.put(up, "up");
		mouseLookup.put(down, "down");
		
		//State Navigation
		menuButton = new Rectangle (690, 485, 80, 20);
		mouseLookup.put(menuButton, "State:mainmenu");

		factoryButton = new Rectangle (690, 510, 80, 20);
		mouseLookup.put(factoryButton, "State:factory");
		
		for(int i=0; i<Inventory.length(); i++) {
			Rectangle r = new Rectangle(5, i*(View.getFrameHeight()-50)/10 + (i+1)*5, 80, (View.getFrameHeight()-50)/10);
			mouseLookup.put(r, "Inventory:"+i);
		}
		
		int w, h, heightSofar = 0;
		for(int y=0; y<items.size(); y++) {
			w = ((StoreItem) items.elementAt(y)).getW();
			h = ((StoreItem) items.elementAt(y)).getH();
			mouseLookup.put(new Rectangle( 90, scrollAmt+20+heightSofar+y*10, w, h), "StoreItem:"+y);
			heightSofar += items.elementAt(y).getH();
		}

		wantsMouseUpdate = false;
	}
}
