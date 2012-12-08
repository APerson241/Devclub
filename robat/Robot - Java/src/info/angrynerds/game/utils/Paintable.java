package info.angrynerds.game.utils;

import java.awt.*;

/**
 * So we can display almost anything in the {@link info.angrynerds.game.view.View View}
 * @author Daniel Glus
 */
public interface Paintable {
	public void paint(Graphics g, Point location);
}
