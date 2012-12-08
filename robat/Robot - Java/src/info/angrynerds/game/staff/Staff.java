package info.angrynerds.game.staff;

import info.angrynerds.game.framework.GameModel;

public abstract class Staff {
	private int salary;
	private static int totalSalaries;
	static {
		totalSalaries = 0;
	}
	
	public Staff(int salary) {
		this.salary = salary;
		totalSalaries += salary;
	}
	
	public static void pay() {
		GameModel.spendMoney(totalSalaries);
	}
	
	public int getSalary() {
		return salary;
	}
}
