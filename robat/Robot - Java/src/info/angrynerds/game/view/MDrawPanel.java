package info.angrynerds.game.view;

import info.angrynerds.game.GameLauncher;
import info.angrynerds.game.utils.*;
import info.angrynerds.game.framework.*;

import java.awt.*;

import javax.swing.*;

/**
 * The main panel that controls what is drawn on the screen.
 * This class draws the items, but the {@link info.angrynerds.game.view.View View}
 * actually holds the information to be drawn.  For a description of the MVC design
 * pattern, see the Javadoc for {@link info.angrynerds.game.framework.GameController
 * GameController}.
 * @author Daniel Glus and John Lhota (QA Testing by Miles Shebar)
 */
public  class MDrawPanel extends JPanel {
	private static final long serialVersionUID = 6048125838704795625L;
	private View view;
	
	public MDrawPanel(View gameView) {
		view = gameView;
	}
	
	public void paint(Graphics g) {
		//Console.println("[MDrawPanel] Attempting to paint " + State.CURRENT_STATE.getName() + "...");
		State.CURRENT_STATE.paint(g);
		//int i = 0;
		for(Paintable p:view.getPaintables()) {
			/* Console.println("[MDrawPanel] Painting paintable #" +
					i++ + "."); */
			p.paint(g, new Point(0, 0));
		}

		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.ORANGE);
		g.drawString(GameLauncher.getVersion(), 10, 10);
	}
}
