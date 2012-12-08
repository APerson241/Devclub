package info.angrynerds.game.framework;

import java.awt.Graphics;

public class CreditsState extends State {

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("Graphics by Miles Shebar", 40, 40);
		g.drawString("Coding by John Lhota/Daniel Glus", 40, 80);
		g.drawString("An AngryNerds Game", 40, 120);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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
