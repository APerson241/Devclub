package info.angrynerds.game.framework;

import info.angrynerds.game.robot.HardDrive;
import info.angrynerds.game.staff.Programmer;
import info.angrynerds.game.utils.Console;
import info.angrynerds.game.view.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

public class FactoryState extends State implements MouseListener, MouseMotionListener {
	private HashMap<Rectangle, String> mouseLookup;
	private Font buttonFont;
	private Rectangle menuButton, mapButton, storeButton;
	private Vector<Programmer> programmers;
	Rectangle tableArea;
	private boolean programList;
	private int programListScrollAmt;
	private Image table;
	private Rectangle newProgrammer;
	
	public FactoryState() {
		buttonFont = new Font(Font.SANS_SERIF, 0, 13);
		
		mouseLookup = new HashMap<Rectangle, String>();
		menuButton = new Rectangle (690, 485, 80, 20);
		mouseLookup.put(menuButton, "State:mainmenu");
//		mapButton = new Rectangle (690, 510, 80, 20);
//		mouseLookup.put(mapButton, "State:map");
		storeButton = new Rectangle (690, 535, 80, 20);
		mouseLookup.put(storeButton, "State:store");
		
		for(int i=0; i<Inventory.length(); i++) {
			Rectangle r = new Rectangle(5, i*(View.getFrameHeight()-50)/10 + (i+1)*5, 80, (View.getFrameHeight()-50)/10);
			mouseLookup.put(r, "Inventory:"+i);
		}
		
		programmers = new Vector<Programmer>();
		programmers.addElement(new Programmer(20, 45, 20));
		newProgrammer = new Rectangle(400, 500, 90, 30);
		mouseLookup.put(newProgrammer, "newprogrammer");
		
		for(int x=0, y=0; y*40+x<programmers.size(); x++) {
			for(x=0; x<20 && y*40+x<programmers.size(); x++) {
				mouseLookup.put(new Rectangle(90+(y*40+x)*15, 30+(y*40+x)*15, 40, 40), "Programmer:0");
			}
		}
		
		
		tableArea = new Rectangle(600, 30, 60, 40);
		mouseLookup.put(tableArea, "State:buildingtable");
		table = new ImageIcon(this.getClass().getResource("table.png")).getImage();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight()); //Factory Floor
		
		g.setColor(new Color(162, 42, 42));
		g.drawImage(table, tableArea.x, tableArea.y, tableArea.width, tableArea.height, null);
		g.setColor(Color.WHITE);
		g.drawString("Build", tableArea.x+10, tableArea.y+15);
		g.drawString("Robot", tableArea.x+10, tableArea.y+28);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, View.getFrameHeight()-140, View.getFrameWidth(), 140); //Menu Panel
		
		g.setColor(Color.green);
		g.setFont(new Font(Font.MONOSPACED, 0, 15));
		g.drawString("$"+GameModel.getMoney()+".00", 90, View.getFrameHeight()-120);
		
		//Buttons
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
		g.fillRect(storeButton.x, storeButton.y, storeButton.width, storeButton.height);
		g.setColor(Color.WHITE);
		g.drawString("Go to Store", storeButton.x+8, storeButton.y+15);
		
		g.setColor(Color.BLACK);
		g.fillRect(newProgrammer.x, newProgrammer.y, newProgrammer.width, newProgrammer.height);
		g.setColor(Color.WHITE);
		g.drawString("Hire Programmer", newProgrammer.x+8, newProgrammer.y+15);
		
		for(int x=0, y=0; y*40+x<programmers.size(); x++) {
			for(x=0; x<20 && y*40+x<programmers.size(); x++) {
				g.setColor(Color.WHITE);
				g.fillRect(90+(y*40+x)*55, 30+(y*40+x)*55, 40, 40);
				g.setColor(Color.BLACK);
				g.drawString("Prog-",100+(y*40+x)*55, 45+(y*40+x)*55);
				g.drawString("rammer",100+(y*40+x)*55, 60+(y*40+x)*55);
			}
		}
		
		Inventory.paint(g);
	}
	
	public String getName() {
		return "factory";
	}

	public void mouseClicked(MouseEvent e) {
		Point location = e.getPoint();
		for(Rectangle rect:mouseLookup.keySet()) {
			if(rect.contains(location)) {
				if(mouseLookup.get(rect).startsWith("State:")) {
					State.switchState( mouseLookup.get(rect).replace("State:","") );
				}
				else if(mouseLookup.get(rect).startsWith("Programmer:") && Inventory.selectedItem() instanceof HardDrive) {
					for(int i=0; i<programmers.size(); i++) {
						programmers.elementAt(i).assignTask((HardDrive)Inventory.selectedItem());
						((HardDrive)Inventory.selectedItem()).setBeingProgrammed(true);
					}
				}
				else if(mouseLookup.get(rect).equals("newprogrammer")) {
					programmers.addElement(new Programmer(25, 25, 25));
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
	
	public void program() {
		for(int i=0; i<programmers.size(); i++) {
			programmers.elementAt(i).program();
		}
	}
	
	private void listPrograms() {
		programList = true;
		programListScrollAmt = 0;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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