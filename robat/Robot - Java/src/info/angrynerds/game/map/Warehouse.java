package info.angrynerds.game.map;

import info.angrynerds.game.utils.InventoryItem;

public class Warehouse extends Terrain {
	
	private InventoryItem[][] items;

	public Warehouse() {
		super(2);
		// TODO Auto-generated constructor stub
		items = new InventoryItem[40][20];
	}

}
