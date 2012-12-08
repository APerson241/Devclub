package info.angrynerds.game.framework;

/**
 * The interface for the GameController.  For a description of the MVC design
 * pattern, see the Javadoc for {@link info.angrynerds.game.framework.GameController
 * GameController}.
 * @author Daniel Glus and John Lhota
 */
public interface Controller {
	/**
	 * Starts everything going.  Need I say more? 
	 */
	public void runApplication();
	public Model getModel();
}
