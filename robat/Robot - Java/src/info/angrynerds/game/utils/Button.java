package info.angrynerds.game.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Button implements Paintable {
	
	private String title;
	private Image img;
	private int x, y, w, h;
	private Rectangle rect;
	
	public Button(String title, String url, int x, int y, int w, int h) {
		this.title = title;
		if(url != null) {
			img = new ImageIcon(this.getClass().getResource(url)).getImage();
		}
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rect = new Rectangle(x, y, w, h);
	}

	@Override
	public void paint(Graphics g, Point location) {
		// TODO Auto-generated method stub
		if(img != null) {
			if(location != null) {
				g.drawImage(img, location.x, location.y, w, h, null);
			}
			else {
				g.drawImage(img, x, y, w, h, null);
			}
		}
		else {
			g.fillRect(x, y, w, h);
			g.drawString(title, x+5, y+5);
		}
	}
	
	public Rectangle getRect() {
		return rect;
	}
}
