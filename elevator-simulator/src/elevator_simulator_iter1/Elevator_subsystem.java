package elevator_simulator_iter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.time.Clock;
import java.time.Instant;

/**
 * 
 * @author Ryan Gaudreault
 *
 */
public class Elevator_subsystem implements Runnable {
	private Scheduler scheduler;
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();

	public Elevator_subsystem(Scheduler scheduler, int ElevatorNo) {
		this.setScheduler(scheduler);
		for(;ElevatorNo > 0; ElevatorNo--) {
			elevators.add(new Elevator(ElevatorNo));
			//System.out.println("Elevator Added");
		}
	}
	
	public void getInfoFromScheduler() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Queue buttons = scheduler.getElevatorRequest();
		this.elevators.get(0).setButtonlist(buttons);
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
		while(true) {
			getInfoFromScheduler();
		}
	}


}

/**
 * 
 * @author Ryan Gaudreault
 *
 */
class Elevator {
	private int ElevatorNo;
	private Queue<ElevatorButton> buttonlist;
	private int currFloor;
	private int elevatorDirection;
	private Motor motor;
	private Door door;
	
	public Elevator(int ElevatorNo) {
		this.ElevatorNo = ElevatorNo;
		this.motor = new Motor();
		this.door = new Door();
		// TODO Auto-generated constructor stub
	}

	public int getElevatorNo() {
		return ElevatorNo;
	}
	
	/*
	 * @param queue of all floors Elevator must stop at
	 * Elevator is not functional and cannot serve request, therefore stall
	 */
	public void setButtonlist(Queue<ElevatorButton> buttons) {
		this.buttonlist = buttons;
		if(!buttonlist.isEmpty()) {
			
			for(ElevatorButton eButton : buttonlist) {
				System.out.println("Elevator is at " + eButton.getCurfloor());
				System.out.println("Elevator requested at " + eButton.getFloorNo());
			}
		}
		
		
		//System.out.println("Updated buttonlist");
		//System.out.println(this.buttonlist);
	}
	
	public int getCurrFloor() {
		return currFloor;
	}

	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}
	
	public void moveUp() {
		currFloor++;
		
	}
	public void moveDown() {
		currFloor--;
	}
	
	
}


class Motor {
	/**
	 * Vel is velocity of the elevator in the shaft, should always be > 0
	 * Dir is travel directon. 1 is up, 0  is stationary, -1 is down.
	 */
	double vel;
	int dir;
	double ACCEL = 3.2/1000; // Shaft accel m/s^2
	double time;
	long timer;
	
	public Motor() {
		this.vel = 0;
		this.dir = 0;
		this.time = 0;
		this.timer = Instant.now().toEpochMilli();
	}
	
	public void start(int dir) {
		this.dir = dir;
		this.timer = Instant.now().toEpochMilli();
	}
	
	public double get_dist() {
		long elapsed = (Instant.now().toEpochMilli() - timer);
		double traveled = this.vel * elapsed * this.dir;
		this.vel = this.vel + (this.ACCEL * elapsed);
		this.timer = Instant.now().toEpochMilli();
		
		return traveled;
	}
	
	public double stop() {
		double traveled = this.get_dist();
		this.dir = 0;
		this.vel = 0;
		
		return traveled;
	}
	
}

class Door {
	private boolean open;
	Door(){
		this.open = false;
	}
	
	public void toggle() {
		this.open = ! this.open;
	}
	
	public boolean is_open() {
		return this.open;
	}
	
}