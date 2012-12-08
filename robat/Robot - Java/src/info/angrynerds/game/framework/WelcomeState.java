package info.angrynerds.game.framework;

import info.angrynerds.game.view.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.swing.ImageIcon;

public class WelcomeState extends State implements MouseListener {
	
	private Image agLogo;
	private float alphaBG, alphaAN, alphaLogo, alphaGAME;
	
	public void paint(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 40));
		if(alphaBG<1) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(new Color((float)1, (float)1, (float)1, alphaBG));
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			alphaBG += 0.002;
		}
		else if(alphaAN<1) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(new Color((float)1, (float)1, (float)1, 1));
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(new Color((float)0, (float)0, (float)0, alphaAN));
			g.drawString("AN", View.getFrameWidth()/2-25, 40);
			alphaAN += 0.005;
		}
		else if(alphaLogo<1) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(new Color((float)1, (float)1, (float)1, 1-alphaLogo));
			g.drawImage(agLogo, 50, 50, View.getFrameWidth()-110, View.getFrameHeight()-140, null);
			g.fillRect(0, 10, View.getFrameWidth(), View.getFrameHeight()-40);
			alphaLogo += 0.005;
			
			g.setColor(Color.BLACK);
			g.drawString("AN", View.getFrameWidth()/2-25, 40);
		}
		else if(alphaGAME<1) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.setColor(new Color((float)1, (float)1, (float)1, 1));
			g.fillRect(0, 0, View.getFrameWidth(), View.getFrameHeight());
			
			g.drawImage(agLogo, 50, 50, View.getFrameWidth()-110, View.getFrameHeight()-140, null);
			
			g.setColor(Color.BLACK);
			g.drawString("AN", View.getFrameWidth()/2-25, 40);
			
			g.setColor(new Color((float)0, (float)0, (float)0, alphaGAME));
			g.drawString("GAME", View.getFrameWidth()/2-35, View.getFrameHeight()-50);
			alphaGAME += 0.005;
		}
		else {
			State.switchState("mainmenu");
		}
	}
	
	public WelcomeState() {
		alphaBG= 0;
		alphaAN = 0;
		alphaLogo = 0;
		alphaGAME = 0;
		agLogo = new ImageIcon(this.getClass().getResource("angrynerds.png")).getImage();
	}

	public String getName() {
		return "welcome";
	}
	
	public void mouseClicked(MouseEvent e) {
		if(alphaGAME<1) {
			alphaBG = alphaAN = alphaLogo = alphaGAME = 1;
		}
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
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