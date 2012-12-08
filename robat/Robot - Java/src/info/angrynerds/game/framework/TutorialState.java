package info.angrynerds.game.framework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class TutorialState extends State implements MouseListener {
	
	private Image tut;
	
	public TutorialState() {
		tut = new ImageIcon(this.getClass().getResource("tutorial.png")).getImage();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(tut, 0, 0, 800, 590, null);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "tutorial";
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		State.switchState("factory");
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
	public boolean wantsMouseUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMouse() {
		// TODO Auto-generated method stub
		
	}

}
