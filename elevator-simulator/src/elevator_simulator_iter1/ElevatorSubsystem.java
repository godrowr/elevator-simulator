package elevator_simulator_iter1;


import java.lang.Thread;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.time.Clock;
import java.time.Instant;

/**
 * This is the ElevatorSubsysem which creates the elevators in the building, starts them and manages their threads. 
 *
 */
public class ElevatorSubsystem  {
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();

	/**
	 * The constructor for the ElevatorSubsystem, builds as many elevators as specified and assigns them to 
	 * the specific scheduler. 
	 * @param scheduler The scheduler of the system
	 * @param elevatorNo The number of elevators in the building 
	 */
	public ElevatorSubsystem(Scheduler scheduler, int elevatorNo) {
		for(;elevatorNo > 0; elevatorNo--) {
			Elevator e = new Elevator(scheduler, elevatorNo);
			Thread t = new Thread(e, "Elevator");
			t.start();
			elevators.add(e);
			threads.add(t);
		}
	}
	
	/*
	 * Returns an elevator in the arraylist of elevators, used for testing. 
	 */
	public Elevator getElevator(int i) {
		return elevators.get(i);
	}

}

enum State {
	MOVING,
	STOPPED
}

/**
 * This is an elevator object, and will move from one part of the building to the next. 
 * It includes a Door and a Motor, along with a state of either {Moving, Stopped} and 
 * holds the current floor values (int) along with a buttonlist of buttons inside the 
 * elevator. 
 */
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
	
	/**
	 * The nextStop() method reads the button list, determines the closest request and 
	 * returns the floor number of the closest stop.
	 * @return floorNo returns the floor number of the closest stop. 
	 */
	public int nextStop(){
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

	/**
	 * This starts the elevator and binds all commands to said elevator to a port. When the elevator receives
	 * a command it moves to the floor, opens its doors, picks up a passenger and moves to the destination of 
	 * the passenger. It repeats this forever. 
	 */
	@Override
	public void run(){
		while(true){
			//Elevator sends string with [elevatorNo,currFloor,nextStop]
			String message = "" + elevatorNo + currFloor + this.nextStop(); //Dont use string! 
			
			// Do UDP in the same function so we dont hit the stupid nulling issues
			UDP uDP = null;
			try {
				if(Main.debug == 1) System.out.println("Elevator " + elevatorNo + " binding on port " + (elevatorNo+749));
				uDP = new UDP(elevatorNo+749, 570,InetAddress.getByName("127.0.0.1"));
				if(Main.debug == 1) System.out.println("Elevator " + elevatorNo + " bound to port " + (elevatorNo+749));
			}catch(Exception e) {
				System.out.println("Error binding port " + e);
			}

			if(Main.debug == 1) System.out.println("Elevator requesting command");
			uDP.sendByte(message.getBytes());
			
			//Elevator receive response from scheduler
			if(Main.debug == 1) System.out.println("Elevator waiting for response");
			RecvData receivePacket = uDP.receive();
			
			if(Main.debug == 1) System.out.println("Elevator got response");
			uDP.close(); // So that the port is open next time
			//decode response and create ElevatorButton to add to buttonlist
			addButtonList(receivePacket); //This is also an issue
			
			
			System.out.println("Elevator " + this.elevatorNo + " is currently at: " + this.currFloor);

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
			System.out.println("Elevator " + this.elevatorNo + "'s doors open at floor: "+ this.nextStop());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Elevator " + this.elevatorNo + "'s doors closed");
		}
		
	}

	/**
	 * Update our list of destinations to remove any that are at 
	 * the current floor
	 */
	public void update(){
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
	
	/**
	 * This method sets the current floor of the elevator. 
	 * @param floorNumber The floor the elevator moved to and is now at. 
	 */
	public void gotoFloor(int floorNumber) {
		// Make sure to close the doors
		int num_floors = Math.abs(this.currFloor - floorNumber);
		if (this.door.isOpen()) {this.door.toggle();}
		if (this.motor.isTravelling()) {this.motor.travelNum(num_floors);}
		this.currFloor = floorNumber;
	}
	
	/**
	 * This converts byte array information into an int. Since the message was created from a
	 * string, it must be converted back into a string then converted into an int. 
	 * @param inputMsg The byte array data received. 
	 * @return ElevatorButton a button pressed in an elevator to drop a patron off. 
	 */
	public ElevatorButton decodeMsg(byte[] inputMsg) {
		byte b = inputMsg[0];//get floor
		byte b1 = inputMsg[1];//get dest
		byte[] ba = new byte[1];
		byte[] ba1 = new byte[1];
		ba[0] = b;
		ba1[0] = b1;
		String s = new String(ba);
		String s1 = new String(ba1);
		
		return new ElevatorButton(Integer.parseInt(s),Integer.parseInt(s1));
	}
	
	/**
	 * Simple setter for buttonlist. 
	 * @param receivePacket
	 */
	public void addButtonList(RecvData receivePacket) {
		buttonlist.add(decodeMsg(receivePacket.data));
	}
	
}

/**
 * This class imitates the actions of the motor, moving the elevator from one floor to the next. 
 */
class Motor {

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

/**
 * This class imitates the actions of the door and the time it lasts when opening and closing. 
 */
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