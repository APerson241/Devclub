package info.angrynerds.game.framework;

import info.angrynerds.game.view.GameView;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class MainMenuState extends State implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -4397485259385938378L;
	
	private Rectangle playButton;
	private Rectangle exitButton;
	private Rectangle creditsButton;
	private Rectangle selectedRectangle;
	
	private Image bg;
	
	private HashMap<Rectangle,String> mouseLookup;

	public MainMenuState() {
		playButton = new Rectangle (480, 310, 200, 40);
		exitButton = new Rectangle (680, 310, 200, 40);
		creditsButton = new Rectangle (880, 310, 200, 40);

		mouseLookup = new HashMap<Rectangle,String>();
		mouseLookup.put(playButton, "factory");
		mouseLookup.put(exitButton,"exit");
		mouseLookup.put(creditsButton,"credits");
		
		bg = new ImageIcon(this.getClass().getResource("main_menu.png")).getImage();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, GameView.getFrameWidth(), GameView.getFrameHeight(), null);
		if(selectedRectangle != null) {
			g.setColor(Color.BLACK);
			g.drawRect(selectedRectangle.x, selectedRectangle.y, selectedRectangle.width,
					selectedRectangle.height);
		}
/*		g.setFont(new Font("Ubuntu", Font.BOLD, 75));
		g.setColor(Color.BLACK);
		g.drawString("rõbä†", 290, 65);

		g.setFont(new Font("Arial", Font.BOLD, 22));
		g.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
		g.setColor(Color.WHITE);
		g.drawString("Play", playButton.x+20, playButton.y+30);

		g.setColor(Color.BLACK);
		g.fillRect(tutButton.x, tutButton.y, tutButton.width, tutButton.height);
		g.setColor(Color.WHITE);
		g.drawString("Tutorial", tutButton.x+20, tutButton.y+30);	*/
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "mainmenu";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point location = e.getPoint();
		for(Rectangle rect:mouseLookup.keySet()) {
			if(rect.contains(location)) {
				System.out.println("[MainMenuState] Clicked: " + mouseLookup.get(rect));
				if(mouseLookup.get(rect).equals("exit")) System.exit(0);
				else State.switchState( mouseLookup.get(rect));
			}
		}
	}
	
	public void setGV(GameView GV) {
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point location = e.getPoint();
		for(Rectangle rect:mouseLookup.keySet()) {
			if(rect.contains(location)) {
				System.out.println("Hovered: " + mouseLookup.get(rect));
				selectedRectangle = rect;
				break;
			}
		}
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