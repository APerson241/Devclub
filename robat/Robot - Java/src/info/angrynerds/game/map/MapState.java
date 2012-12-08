package info.angrynerds.game.map;

import info.angrynerds.game.framework.GameModel;
import info.angrynerds.game.framework.State;
import info.angrynerds.game.utils.Console;
import info.angrynerds.game.view.View;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MapState extends State implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 7491802807536030262L;

	private Terrain[][] terrain;
	private int selectedTerrainX, selectedTerrainY;
	
	private Rectangle zoomIn, zoomOut;
	
    private int zoomLevel;
    private int xLoc, yLoc, startingXLoc, startingYLoc;
    
	public MapState() {
		terrain = new Terrain [25][25];
		zoomLevel = 40;
        xLoc = 7*zoomLevel;
        yLoc = 7*zoomLevel;
        Terrain.setDimensions(zoomLevel, zoomLevel);
        
        zoomIn = new Rectangle(View.getFrameWidth()-150, View.getFrameHeight()-80, 75, 80);
        zoomOut = new Rectangle(View.getFrameWidth()-75, View.getFrameHeight()-80, 75, 80);
        
		for(int x=0; x<25; x++) {
			for(int y=0; y<25; y++) {
				terrain[x][y] = new Terrain(Terrain.LAND);
			}
		}
		
		terrain[10][9].getForFree(); // Give the user 1 factory to start out with
		build(Terrain.FACTORY, 10, 9, true);
		
		mouseLookup = new HashMap<Rectangle, String>();
		updateMouse();
	}
    
    private void zoomIn() {
        if(zoomLevel<150) {
        	zoomLevel += 20;
        	Terrain.setDimensions(zoomLevel, zoomLevel);
        }
        wantsMouseUpdate = true;
    }
    private void zoomOut() {
    	if (zoomLevel>20) {
            zoomLevel -= 20;
            Terrain.setDimensions(zoomLevel, zoomLevel);
        }
    	wantsMouseUpdate = true;
    }
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Point p;
		for(int y=0; y<25; y++) {
			for(int x=0; x<25; x++) {
				p = new Point((x*zoomLevel)-xLoc, (y*zoomLevel)-yLoc);
				terrain[x][y].paint(g, p);
			}
		}
		
		g.setColor(Color.RED);
		g.drawRect((selectedTerrainX*zoomLevel)-xLoc, (selectedTerrainY*zoomLevel)-yLoc, zoomLevel, zoomLevel);
		
		paintControls(g);
	}
	
	private void paintControls(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(View.getFrameWidth()-150, 0, 150, View.getFrameHeight());
		
		g.setColor(Color.GRAY);
		g.fillRect(View.getFrameWidth()-150, View.getFrameHeight()-80, 150, 80);
		
		g.setColor(Color.DARK_GRAY);
		g.drawRect(zoomIn.x, zoomIn.y, zoomIn.width, zoomIn.height);
		g.drawRect(zoomOut.x, zoomOut.y, zoomOut.width, zoomOut.height);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "map";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(Rectangle rect: mouseLookup.keySet()) {
			if(mouseLookup.get(rect).equals("zoomIn"))
				zoomIn();
			else if(mouseLookup.get(rect).equals("zoomOut"))
				zoomOut();
			else if(mouseLookup.get(rect).startsWith("Terrain:"))
				selectTerrain(Integer.parseInt(mouseLookup.get(rect).replace("Terrain:", "")));
		}
	}
	
	private void selectTerrain(int t) {
		// TODO Auto-generated method stub
		selectedTerrainX = Math.round(t/25);
		selectedTerrainY = t % 25;
	}

	public void build(int buildingType, int x, int y, boolean free) {
		if(terrain[x][y].isOwned() && !free) {
			switch(buildingType) {
			case Terrain.LAND: {
				GameModel.spendMoney(3000);
				terrain[x][y] = new Land();
				break;
			}
			case Terrain.FACTORY: {
				GameModel.spendMoney(5000);
				terrain[x][y] = new Factory();
				break;
			}
			case Terrain.WAREHOUSE: {
				GameModel.spendMoney(4000);
				terrain[x][y] = new Warehouse();
				break;
			}
			case Terrain.LAB: {
				GameModel.spendMoney(6000);
				terrain[x][y] = new Lab();
				break;
			}
			case Terrain.OFFICE: {
				GameModel.spendMoney(7000);
				terrain[x][y] = new Office();
				break;
			}
			}
		}
		else if(terrain[x][y].isOwned() && free) {
			switch(buildingType) {
			case Terrain.LAND: {
				terrain[x][y] = new Land();
				break;
			}
			case Terrain.FACTORY: {
				terrain[x][y] = new Factory();
				break;
			}
			case Terrain.WAREHOUSE: {
				terrain[x][y] = new Warehouse();
				break;
			}
			case Terrain.LAB: {
				terrain[x][y] = new Lab();
				break;
			}
			case Terrain.OFFICE: {
				terrain[x][y] = new Office();
				break;
			}
			}
		}
		else {
			Console.println("You do not own that plot of land.");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//startingXLoc = e.getPoint().x;
        //startingYLoc = e.getPoint().y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getPoint().x > startingXLoc && startingXLoc-1>=0) {
            xLoc--;
        }
        else if(e.getPoint().y > startingYLoc && startingYLoc-1>=0) {
            yLoc--;
        }
        else if(e.getPoint().x < startingXLoc && startingXLoc+1<=25*zoomLevel) {
            xLoc++;
        }
        else if(e.getPoint().y < startingYLoc && startingYLoc+1<=25*zoomLevel) {
            yLoc++;
        }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		Point location = e.getPoint();
//		for(int x=0; x<25; x++) {
//			for(int y=0; y<25; y++) {
//				if(new Rectangle (x, y, 25, 25).contains(location)) {
//					g.drawRect();
//					//confirm("buy this plot of land for $500?");
//				}
//			}
//		}
		xLoc +=15;
	}

	public static boolean hasRoomInWarehouse() {
		// TODO Auto-generated method stub
		return false;
	}

	public static int sendToWarehouse() {
		// TODO Auto-generated method stub
		return (Integer) null;
	}

	@Override
	public boolean wantsMouseUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMouse() {
		// TODO Auto-generated method stub
		mouseLookup.clear();
		mouseLookup.put(zoomIn, "zoomIn");
		mouseLookup.put(zoomOut, "zoomOut");
		
		for(int y=0; y<25; y++) {
			for(int x=0; x<25; x++) {
				Rectangle r = new Rectangle((x*zoomLevel)-xLoc, (y*zoomLevel)-yLoc, zoomLevel, zoomLevel);
				mouseLookup.put(r, "Terrain:"+y*25+x);
			}
		}
	}

}
