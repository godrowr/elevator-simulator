package elevator_simulator_iter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.time;
import java.time.Clock;
import java.time.Instant;

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
	double ACCEL = 3.2; // Shaft accel m/s^2
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
	
}