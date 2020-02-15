package elevator_simulator_iter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Ryan Gaudreault
 *
 */
public class Elevator_subsystem implements Runnable {
	private Scheduler scheduler;
	private List<Elevator> elevators = new ArrayList<Elevator>();
	
	
	public Elevator_subsystem(Scheduler scheduler, int ElevatorNo) {
		this.setScheduler(scheduler);
		for(;ElevatorNo > 0; ElevatorNo--) {
			elevators.add(new Elevator(ElevatorNo));
			//System.out.println("Elevator Added");
		}
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

/**
 * 
 * @author Ryan Gaudreault
 *
 */
class Elevator {
	private int ElevatorNo;
	private ArrayList<ElevatorButton> buttonlist;
	private int currFloor;
	private int elevatorDirection;
	
	
	

	
	public Elevator(int ElevatorNo) {
		this.ElevatorNo = ElevatorNo;
		// TODO Auto-generated constructor stub
	}

	public int getElevatorNo() {
		return ElevatorNo;
	}

	public int getCurrFloor() {
		return currFloor;
	}

	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}
	
	public void moveUp() {
		currFloor++;;
	}
	public void moveDown() {
		currFloor--;
	}
	
	
}


class Motor {
	private Boolean movingUp; //Track if the elevator is going up, FOR I2
	private Boolean movingDown; // Track if the elevator is going down, FOR I2
	private Boolean stopped; // Track if the elevator is stopped, FOR I2
	
	public Boolean getMovingUp() {// FOR I2
		return this.movingUp;
	}
	
	public Boolean getMovingDown() {// FOR I2
		return this.movingDown;
	}
	
	public Boolean getStopped() {// FOR I2
		return this.stopped;
	}
	
}

class Door {
	
	private Boolean doorOpen;// True: Door is open, False: door is closed
	
	public Boolean getDoorStatus() { // FOR I2
		return this.doorOpen;
		//return: true if open
	}
}