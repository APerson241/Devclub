package info.angrynerds.game;

import info.angrynerds.game.framework.*;
import info.angrynerds.game.utils.Console;

/**
 * The class where it all begins.<br>"In the beginning, there was GameLauncher..."
 * @author Daniel Glus and John Lhota, with QA Testing & Graphics by Miles Shebar.
 * @version v.0.1.9
 */
public class GameLauncher {
	/**
	 * The main method.
	 * @param args Some useless command-line arguments that we don't really
	 * care about.
	 */
	public static void main(String[] args) {
		new GameLauncher().go();
	}
	
	/**
	 * So we can run everything in a non-static method...
	 */
	public void go() {
		Console.setVisible(true);
		Controller controller = new GameController();
		controller.runApplication();
	}

	public static String getVersion() {
		// TODO Auto-generated method stub
		return "v.0.1.9";
	}
}