package elevator_simulator_iter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Elevator_subsystem implements Runnable {
	private Scheduler scheduler;

	public Elevator_subsystem(Scheduler scheduler) {
		this.setScheduler(scheduler);
	}
	
	public void getInfoFromScheduler() {
		Queue buttons = scheduler.getFloorRequest();
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}

class Elevator extends Elevator_subsystem{
	private int ElevatorNo;
	private ArrayList<ElevatorButton> buttonlist;
	
	public Elevator(Scheduler scheduler, int ElevatorNo) {
		super(scheduler);
		this.ElevatorNo = ElevatorNo;
		// TODO Auto-generated constructor stub
	}

	public int getElevatorNo() {
		return ElevatorNo;
	}

	
}

class Motor {
	
}

class Door {
	
}