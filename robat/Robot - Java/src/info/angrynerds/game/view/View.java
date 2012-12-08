package info.angrynerds.game.view;

import info.angrynerds.game.framework.Model;
import info.angrynerds.game.utils.*;

import java.util.List;

/**
 * The View of the program - manages all windows on the screen at the moment.
 * For a description of the MVC design
 * pattern, see the Javadoc for {@link info.angrynerds.game.framework.GameController
 * GameController}.
 * @author John Lhota and Daniel Glus
 */
public abstract class View {
	/**
	 * Sets the visibility of the JFrame
	 * @param visible Whether or not the JFrame will become visible
	 */
	public abstract void setVisible(boolean visible);
	public abstract void repaintFrame();
	public abstract List<Paintable> getPaintables();
	public abstract Model getModel();
	
	public static int getFrameHeight() {
		return 600;
	}
	
	public static int getFrameWidth() {
		return 800;
	}
}
