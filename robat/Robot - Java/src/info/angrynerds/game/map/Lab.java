package info.angrynerds.game.map;

import info.angrynerds.game.staff.Programmer;
import info.angrynerds.game.staff.Scientist;

public class Lab extends Terrain {
	
	private Scientist[] scientists;
	private Programmer[] programmers;

	public Lab() {
		super(Terrain.LAB);
		// TODO Auto-generated constructor stub
		scientists = new Scientist[15];
		programmers = new Programmer[15];
	}
	
}
