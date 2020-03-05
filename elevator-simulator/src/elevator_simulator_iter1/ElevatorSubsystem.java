package elevator_simulator_iter1;
import java.lang.Thread;
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
public class ElevatorSubsystem  {
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
		Queue buttons = scheduler.getElevatorRequest();
		this.elevators.get(0).setButtonlist(buttons);
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

enum State {
	MOVING,
	STOPPED
}

/**
 * 
 * @author Ryan Gaudreault
 *
 */
class Elevator implements Runnable{
	private ArrayList<ElevatorButton> buttonlist;
	private int currFloor;
	private Motor motor;
	private Door door;
	private Scheduler scheduler;
	private State state;
	
	public Elevator(Scheduler sched) {
		this.buttonlist = new ArrayList<ElevatorButton> ();
		this.currFloor = 1;
		this.motor = new Motor();
		this.door = new Door();
		this.scheduler = sched;
	}
	
	/*
	 * @param queue of all floors Elevator must stop at
	 * Elevator is not functional and cannot serve request, therefore stall
	 */
	public void setButtonlist(Queue<ElevatorButton> buttons) {
		this.buttonlist = buttons;
		if(!buttonlist.isEmpty()) {
			
			for(ElevatorButton eButton : buttonlist) {
			}
		}
		
		
		//System.out.println("Updated buttonlist");
		//System.out.println(this.buttonlist);
	}


	private int nextStop(){
		// Figure out next person in line
		FloorButton nextClosest = null;
		for (FloorButton req: floorRequest) {
			if (nextClosest == null) {
				nextClosest = req;
				continue;
			} else if(Math.abs(current - nextClosest.getFloor()) > 
				      Math.abs(current - req.getFloor())){
				nextClosest = req;
				continue;
			}
		}

		if (nextClosest == null) {
			return currFloor;
		} else {
			return nextClosest.getFloor();
		}
	}


	@Override
	public void run(){
		while(1){
			ElevatorButton newButton = this.scheduler.getNextFloor(currFloor,
																   this.nextStop());
			buttonlist.add(newButton);

			if(nextStop() == currFloor){
				// Wait so that we dont overload the system
				Thread.sleep(1000); 
			}else{
				gotoFloor(this.nextStop());

			}

		}
		
	}

	/**
	 * Update our list of destinations to remove any that are at 
	 * the current floor
	 */
	private void update(){
		// Figure out next person in line
		int counter = 0;
		for (FloorButton req: floorRequest) {
			// This should always execute first
			if (req.getFloor() == currFloor) {
				buttonlist.remove(counter);
				continue;
			}
			counter += 1;
		}
	}
	
	private void gotoFloor(int floorNumber) {
		// Make sure to close the doors
		int num_floors = Math.abs(this.currFloor - floorNumber);
		if (this.door.isOpen()) {this.door.toggle();}
		if (this.motor.isTravelling()) {this.motor.travelNum(num_floors);}
		this.currFloor = floorNumber;
	}
	
}


class Motor {
	/**
	 * Vel is velocity of the elevator in the shaft, should always be > 0
	 * Dir is travel directon. 1 is up, 0  is stationary, -1 is down.
	 */
	private static final long DELAY = 2000; // ms per floor
	private boolean travelling;
	
	public Motor() {
		travelling = false;		
	}
	
	public void travelNum(int num_floors){
		travelling = true;

		Thread.sleep(DELAY * num_floors);
		travelling = false;
	}
	public boolean isTravelling() {
		return travelling;
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
	
	public boolean isOpen() {
		return this.open;
	}
	
}