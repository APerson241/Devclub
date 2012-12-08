package info.angrynerds.game.framework;

import info.angrynerds.game.map.MapState;
import info.angrynerds.game.utils.*;
import info.angrynerds.game.view.View;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

public abstract class State extends JPanel /* implements MouseListener */ {
	public static State CURRENT_STATE;
	private static HashMap<String, State> states;
	public static View GV;
	protected HashMap<Rectangle, String> mouseLookup;
	protected boolean wantsMouseUpdate;
	
	static {
		states = new HashMap<String, State>();
		states.put("welcome", new WelcomeState());
		states.put("mainmenu", new MainMenuState());
		states.get("mainmenu");
		State.setGV(State.GV);
		states.put("tutorial", new TutorialState());
		states.put("factory", new FactoryState());
		states.put("map", new MapState());
		states.put("store", new StoreState());
		states.put("buildingtable", new BuildingTableState());
		CURRENT_STATE = states.get("welcome");
	}
	
	public static void switchState(String name) {
		Console.println("[State] State changing from " +
				CURRENT_STATE.getName() + " to " + name + "...");
		if(!states.containsKey(name)) {
			Console.println("[State] " + name + " isn't a valid state.");
			throw new IllegalArgumentException(name + " isn't a valid state!");
		} else {
			CURRENT_STATE = states.get(name);
			if(name.equals("mainmenu")) {
				
			}
		}
	}
	
	public static State getState(String name) {
		if(states.containsKey(name)) {
			return states.get(name);
		} else {
			throw new IllegalArgumentException(name + " isn't a valid state!");
		}
	}
	
	public void alert(String txt, Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(Math.round(View.getFrameWidth()*0.2f), Math.round(View.getFrameHeight()*0.2f), Math.round(View.getFrameWidth()*0.8f), Math.round(View.getFrameHeight()*0.6f));
		
		g.drawString(txt, Math.round(View.getFrameWidth()*0.21f), Math.round(View.getFrameHeight()*0.21f));
	}
	
	public abstract void paint(Graphics g);
	public abstract String getName();
	public abstract boolean wantsMouseUpdate();
	public abstract void updateMouse();

	/**
	 * Sets the gameView.
	 * @param gameView The GameView
	 */
	public static void setGV(View gameView) {
		// TODO Auto-generated method stub
		GV = gameView;
	}

	
}
