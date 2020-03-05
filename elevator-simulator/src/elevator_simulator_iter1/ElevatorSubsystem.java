
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.time.Clock;
import java.time.Instant;


public class ElevatorSubsystem  {
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();

	public ElevatorSubsystem(Scheduler scheduler, int elevatorNo) {
		for(;elevatorNo > 0; elevatorNo--) {
			Elevator e = new Elevator(scheduler, elevatorNo);
			Thread t = new Thread(e, "Elevator");
			t.start();
			elevators.add(e);
			threads.add(t);
			//System.out.println("Elevator Added");
		}
	}

}

enum State {
	MOVING,
	STOPPED
}


class Elevator implements Runnable{
	private ArrayList<ElevatorButton> buttonlist;
	private int currFloor;
	private Motor motor;
	private Door door;
	private Scheduler scheduler;
	private State state;
	private int elevatorNo;
	
	public Elevator(Scheduler sched, int elevatorNo) {
		this.buttonlist = new ArrayList<ElevatorButton> ();
		this.currFloor = 1;
		this.elevatorNo = elevatorNo;
		this.motor = new Motor();
		this.door = new Door();
		this.scheduler = sched;
	}
	
	private int nextStop(){
		// Figure out next person in line
		ElevatorButton nextClosest = null;
		for (ElevatorButton req: buttonlist) {
			if (nextClosest == null) {
				nextClosest = req;
				continue;
			} else if(Math.abs(currFloor - nextClosest.getFloor()) > 
				      Math.abs(currFloor - req.getFloor())){
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
		while(true){
			ElevatorButton newButton = this.scheduler.getNextFloor(currFloor,
																   this.nextStop());
			
			buttonlist.add(newButton);
			System.out.println("El " + this.elevatorNo +" at: " + this.currFloor);

			if(nextStop() == currFloor){
				// Wait so that we dont overload the system
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}else{
				gotoFloor(this.nextStop());
				this.update();
			}
			System.out.println("El went to floor: "+ this.nextStop());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Update our list of destinations to remove any that are at 
	 * the current floor
	 */
	private void update(){
		// Figure out next person in line
		ArrayList<ElevatorButton> remove = new ArrayList<ElevatorButton>();
		for (ElevatorButton req: buttonlist) {
			// This should always execute first
			if (req.getFloor() == currFloor) {
				remove.add(req);
			}
		}
		this.buttonlist.removeAll(remove);
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

		try {
			Thread.sleep(DELAY * num_floors);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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