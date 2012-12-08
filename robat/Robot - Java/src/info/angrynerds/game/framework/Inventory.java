package info.angrynerds.game.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import info.angrynerds.game.robot.*;
import info.angrynerds.game.utils.Console;
import info.angrynerds.game.utils.InventoryItem;
import info.angrynerds.game.utils.StoreItem;
import info.angrynerds.game.view.View;

public abstract class Inventory {
	private static InventoryItem[] items;
	private static int selectedItem;
	
	static {
		items = new InventoryItem[9];
		selectedItem = -1;
	}
	
	public static void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 80, View.getFrameHeight());
		
		int h = (View.getFrameHeight()-50)/10;
		for(int i=0; i<items.length; i++) {
			if(selectedItem!=-1 && i==selectedItem) {
				g.setColor(Color.WHITE);
				if(items[i] != null) {
					g.drawImage(items[i].getImage(), 5, i*h + (i+1)*5, 70, h, null);
				}
				g.drawRect(5, i*h + (i+1)*5, 70, h);
			}
			else {
				if(items[i] != null) {
					g.drawImage(items[i].getImage(), 5, i*h + (i+1)*5, 70, h, null);
				}
				g.setColor(Color.GRAY);
				g.drawRect(5, i*h + (i+1)*5, 70, h);
			}
			
			if(items[i] instanceof HardDrive && ((HardDrive)items[i]).beingProgrammed()) {
				g.setColor(new Color(1f, 1f, 1f, 0.5f));
				g.fillRect(5, i*h + (i+1)*5, 70, h);
				
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 11));
				g.drawString("Being", 15, i*h + (i+1)*5+15);
				g.drawString("Progra", 15, i*h + (i+1)*5+25);
				g.drawString("mmed...", 15, i*h + (i+1)*5+35);
			}
		}	
	}
	
	public static void switchItems(int item2) {
		if(selectedItem!=-1 && items[selectedItem]!=null) {
			InventoryItem temp;
			temp = items[selectedItem];
			items[selectedItem] = items[item2];
			items[item2] = temp;
		}
		
	}
	
	public static boolean itemExists(int i) {
		if(items[i] == null || i>=length()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static InventoryItem getItem(int i) {
		return items[i];
	}

	public static void select(int item) {
		// TODO Auto-generated method stub
		selectedItem = item;
	}

	public static void addItem(InventoryItem inventoryItem) {
		// TODO Auto-generated method stub
		for(int i=0; i<items.length; i++) {
			if(items[i] == null) {
				items[i] = inventoryItem;
				return;
			}
			Console.println("Your inventory has no space.");
		}
	}
	
	public static void addItem(InventoryItem inventoryItem, int item) {
		// TODO Auto-generated method stub
			if(items[item] != null) {
				items[item] = inventoryItem;
			}
	}
	
	public static void sellItem() {
		if(selectedItem != -1 && items[selectedItem] != null) {
			if(items[selectedItem] instanceof StoreItem) {
				GameModel.earnMoney((int) Math.round(((StoreItem) items[selectedItem]).getPrice()*0.6f));
			}
			else if(items[selectedItem] instanceof Robot) {
				GameModel.earnMoney((int) (((Robot)items[selectedItem]).approximateValue() * 0.6f));
			}
			items[selectedItem] = null;
		}
	}
	
	public static void removeItem() {
		if(selectedItem != -1) {
			items[selectedItem] = null;
		}
	}

	public static void selectNone() {
		// TODO Auto-generated method stub
		selectedItem = -1;
	}

	public static int length() {
		// TODO Auto-generated method stub
		return items.length;
	}

	public static InventoryItem selectedItem() {
		// TODO Auto-generated method stub
		if(selectedItem != -1) {
			return items[selectedItem];
		}
		return null;
	}

	public static InventoryItem getSelectedItem() {
		// TODO Auto-generated method stub
		if(selectedItem != -1) {
			return items[selectedItem];
		}
		return null;
	}
	
	public static int getSelectedInt() {
		// TODO Auto-generated method stub
		return selectedItem;
	}
}
