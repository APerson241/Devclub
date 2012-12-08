package info.angrynerds.game.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

public abstract class Sprite {
	private HashMap<String, Vector<Image>> actions;
	private String action;
	private int frameNum;
	private  int x, y, width, height;
	
	public void paint(Graphics g) {
		if(frameNum>((CharSequence) actions.get(action)).length()) {
			frameNum = 0;
		}
		g.drawImage(actions.get(action).elementAt(frameNum), x, y, width, height, null);
		frameNum++;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	/*
	 * @param baseUrl This argument will let us, instead of adding tons of individual url names, just input one name (assuming that all the files for the animation
	 * will be something like "running-1.jpg, running-2.jpg, running-3.jpg, etc.")
	 */
	public void addAction(String name, String baseUrl, String extension, int frames) {
		actions.put(name, null);
		for(int i=0; i<frames; i++) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(baseUrl + "-" + frames + "." + extension));
			actions.get(name).addElement(ii.getImage());
		}
	}
}
