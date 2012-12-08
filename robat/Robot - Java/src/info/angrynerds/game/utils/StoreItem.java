package info.angrynerds.game.utils;


import java.awt.Image;

public abstract class StoreItem extends InventoryItem {

	public abstract int getPrice();
	public abstract Image getImage();
	public abstract String getName();

}
