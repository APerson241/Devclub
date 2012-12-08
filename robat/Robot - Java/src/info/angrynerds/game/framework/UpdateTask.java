package info.angrynerds.game.framework;

import info.angrynerds.game.staff.Staff;
import info.angrynerds.game.view.GameView;

import java.awt.event.MouseListener;
import java.util.TimerTask;

public class UpdateTask extends TimerTask {

	private GameView gView;
	private String prevState;
	private int calendar;
	private int gameYear;
	
	public UpdateTask (GameView gView) {
		this.gView = gView;
		prevState = State.CURRENT_STATE.getName();
		gameYear = (1000/5)*60*5; // One game year = 5 real world minutes.
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
        gView.repaintFrame();            

        	if(prevState!=State.CURRENT_STATE.getName()) {
        		gView.getPanel().removeMouseListener((MouseListener)State.getState(prevState));
                gView.getPanel().addMouseListener((MouseListener)State.CURRENT_STATE);
                
                prevState = State.CURRENT_STATE.getName();
        	}
            if(State.CURRENT_STATE.wantsMouseUpdate()) {
            	State.CURRENT_STATE.updateMouse();
            }
        
        calendar++;
        
        if(calendar==gameYear) {
        	Staff.pay();
        	calendar = 0;
        }
        
        ((FactoryState) State.getState("factory")).program();
	}

}
