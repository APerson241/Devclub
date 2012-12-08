package info.angrynerds.game.framework;

import info.angrynerds.game.view.GameView;
import info.angrynerds.game.view.View;

/**
 * The brains of this game.<br>
 * <b>A Description of the MVC pattern</b><br>
 * So, the MVC pattern is a convention followed by Java programmers.  There are 3 main
 * players: the Model, the View, and the Controller.  The Model holds all of the data
 * and sometimes, the means to manipulate that data.  The View is responsible for all
 * of that nice GUI stuff, like putting the window on the screen and managing the
 * all-important <code>paint(Graphics g)</code> method.  And finally, the Controller
 * is the glue that holds it all together.
 * @author Daniel Glus and John Lhota
 */
public class GameController implements Controller{
	private View gameView;
	private Model gameModel;
	
	public GameController() {
		gameView = new GameView(this);
		State.setGV(gameView);
		gameModel = new GameModel();
	}
	
	public Model getModel() {
		return gameModel;
	}
	
	public View getView() {
		return gameView;
	}
	
	public void runApplication() {
		gameView.setVisible(true);
	}

	public void quit() {
		// TODO Auto-generated method stub
	}
}