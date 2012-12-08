package info.angrynerds.game.staff;

import info.angrynerds.game.robot.*;

public class Programmer extends Staff {
	private int laziness;
	private int experience;
	private int stuffToDo;
	private int salary;
	//private static Robot robot;
	private HardDrive hardDrive;
	private Ability ability;
	
	public Programmer(int salary, int laziness, int experience) {
		super(salary);
		this.laziness = laziness;
		this.experience = experience;
	}
	
	public void assignTask(HardDrive h/*, Ability ability*/) {
		hardDrive = h;
		//stuffToDo = ability.complexity();
	}
	
	public void program() {
		if(laziness > Math.round(Math.random()*100)){
//			stuffToDo -= experience;
//			if(stuffToDo <= 0) {
//				robot.addAbility(ability);
//			}
			if(hardDrive != null) {
				hardDrive.abilities++;
			}
		} //else { playSolitaire() }
		
	}

	public int getLaziness() {
		return laziness;
	}

	public void setLaziness(int laziness) {
		this.laziness = laziness;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getStuffToDo() {
		return stuffToDo;
	}

	public void setStuffToDo(int stuffToDo) {
		this.stuffToDo = stuffToDo;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public HardDrive getHardDrive() {
		return hardDrive;
	}

	public void setHardDrive(HardDrive hardDrive) {
		this.hardDrive = hardDrive;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}
}
