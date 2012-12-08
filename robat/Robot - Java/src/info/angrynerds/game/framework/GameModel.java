package info.angrynerds.game.framework;

/**
 * The actual model.  This class holds all of the data for the game that pertains
 * to the gameplay.  For a description of the MVC design
 * pattern, see the Javadoc for {@link info.angrynerds.game.framework.GameController
 * GameController}.
 * @author Daniel Glus and John Lhota
 */
public class GameModel implements Model {
	
	public GameModel() {
		if(gameFileExists()) {
			loadGame();
		}
		else {
			setMoney(50000);
		}
	}
	
	private boolean gameFileExists() {
		// TODO Auto-generated method stub
		return false;
	}

	private static int money;
	
	public static void earnMoney(int amount) {
		money += amount;
	}
	
	public static void spendMoney(int amount) {
		if(money-amount >= 0) {
			money -= amount;
		}
	}
	public static void setMoney(int money) {
		GameModel.money = money;
	}
	
	public static int getMoney() {
		return money;
	}
	
	public void saveGame() {
		
	}
	
	public void loadGame() {
		
	}
}