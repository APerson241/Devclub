package info.angrynerds.game.robot;

public enum RobotType {
	ARM(2),	// Robot arm
	WALKING(3),
	ROLLING(4),
	MILITARY_FLYING(6),
	MILITARY_ROLLING(5),
	HUMMER(7),
	TURTLE(1);	// The easiest one
	
	private int difficulty;
	
	private RobotType(int fail) {
		difficulty = fail;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
