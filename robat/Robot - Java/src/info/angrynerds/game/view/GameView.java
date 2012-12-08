package info.angrynerds.game.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import info.angrynerds.game.framework.*;
import info.angrynerds.game.utils.*;

/**
 * The implementation of the {@link info.angrynerds.game.view.View View}.
 * This class holds all of the stuff to be drawn, but the 
 * {@link info.angrynerds.game.view.MDrawPanel MDrawPanel} draws it.
 * For a description of the MVC design pattern, see the Javadoc for 
 * {@link info.angrynerds.game.framework.GameController GameController}.
 * @author John Lhota and Daniel Glus (QA Testing by Miles Shebar)
 */
public class GameView extends View {
	/**
	 * The frame, which is the only frame that will be used in the application.
	 */
	private JFrame frame;
	private Controller controller;
	private Model model;
	private List<Paintable> entities;
	private MDrawPanel panel;
	Timer updater;
	
	public GameView(Controller gameController) {
		controller = gameController;
		model = controller.getModel();
		entities = new ArrayList<Paintable>();
		updater = new Timer();
	}

	/**
	 * The actual implementation of the setVisible() method from 
	 * {@link info.angrynerds.game.view.View View}.  Sets up the JFrame and such.
	 * @param visible Whether or not the frame is visible.
	 */
	public void setVisible(boolean visible) {
		if(frame == null) {
			frame = new JFrame("robot");
			panel = new MDrawPanel(this);
			frame.getContentPane().add(BorderLayout.CENTER, panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setBounds(50, 50, View.getFrameWidth(), View.getFrameHeight());
			updater.scheduleAtFixedRate(new UpdateTask(this), 5, 5);
			panel.addMouseListener( (MouseListener)State.getState("welcome") );
		}
		frame.setVisible(visible);
	}
	
	public JFrame getJF() {
		return frame;
	}
	
	public void repaintFrame() {
		frame.repaint();
	}
	
	public MDrawPanel getPanel() {
		return panel;
	}
	
	public Model getModel() {
		return model;
	}

	public List<Paintable> getPaintables() {
		return entities;
	}
}