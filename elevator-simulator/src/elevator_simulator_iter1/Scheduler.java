package elevator_simulator_iter1;

import java.util.*;
import java.time.Instant;
import java.lang.Math;


enum Direction{
	UP,
	DOWN,
	STOP
}

/**
 * 
 * @author Ryan Gaudreault
 * @author Andrew Cowan
 */
public class Scheduler implements Runnable{
	private ArrayList<FloorButton> floorRequest; //These are the buttons pushed on the floors by waiting patrons. 
	private FloorSubsystem floorSubsystem;
	private Instant start;

	public Scheduler() {
		// TO DELETE
		// elevatorRequest = new ArrayDeque<ElevatorButton>() ; //These are the buttons pushed in the elevator.
		this.floorSubsystem = new FloorSubsystem();
	
		// Tell the subsystem to read in the values from the file
		floorSubsystem.parseFile();

		// Record start time
		this.start = Instant.now();

		//These are the buttons pushed on the floors by waiting patrons. 
		// This will get updated every query period by scheduler
		floorRequest = new ArrayList<FloorButton>(); 
	}

	/**
	 * Query the floor subsystem for the new list of floors
	 * 
	 */
	private void querySubsystem(){
		Instant elapsed = Instant.now().minus(this.start);
		ArrayList<FloorButton> newReqs = this.floorSubsystem.getRequest(elapsed);
		// Add all the new requests to our list of requests
		for(FloorButton req: newReqs){
			floorRequest.add(req);
		}
	}


	/*
	 * Scheduler receives info from elevator (direction, current floor)
	 * Only returns next closest person in same direction (dont starve other elevators)
	 * @returns appropriate floorButton for elevator to service
	 */
	private synchronized ElevatorButton getNextFloor(int current, int dest) {
		boolean isEmpty;
		Direction dir;
		// Figure out state of the elevator
		if (current - dest < 0){
			dir = Direction.UP;
			isEmpty = false;
		} else if (current - dest > 0){
			dir = Direction.DOWN;
			isEmpty = false;
		} else {
			dir = Direction.STOP;
			isEmpty = true;
		}

		// Figure out next person in line
		FloorButton nextClosest = null;
		int counter = 0;
		int closestLoc = -1;
		for (FloorButton req: floorRequest) {
			// This should always execute first
			if (nextClosest == null) {
				nextClosest = req;
				closestLoc = counter;
				counter += 1;
				continue;
			} else if ((req.getFloor() < current && dir == Direction.UP) || 
				(req.getFloor() > current && dir == Direction.DOWN)){
					// We dont service people not in our way
					counter += 1;
					continue;
			} else if (dir == Direction.STOP){
				// Abs in case next closest is below / above
				// Check if the next request is closer than the
				// stored next closest value. If so, it becomes next
				// closest value.
				if(Math.abs(current - nextClosest.getFloor()) > 
				   Math.abs(current - req.getFloor())){
					nextClosest = req;
					closestLoc = counter;
					counter += 1;
					continue;
				}
			} else if(Math.abs(current - nextClosest.getFloor()) > 
					  Math.abs(current - req.getFloor())){
				// Getting to this elseif means that we've got a valid floor
				// that is in our path and we arent stopped.
				nextClosest = req;
				closestLoc = counter;
				counter += 1;
				continue;
		 	}

		}

		// Create the thing that we pass back to the elevator
		if (nextClosest == null && dir == Direction.STOP) {
			ElevatorButton nextStop = new ElevatorButton(1, 1);
		} else if (nextClosest == null && dir != Direction.STOP) {
			ElevatorButton nextStop = new ElevatorButton(-1, -1);
		} else {
			ElevatorButton nextStop = new ElevatorButton(nextClosest.getFloor(),
													     nextClosest.getDest());
		}
		return nextStop;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//serviceRequest();
		}
	}


	public Queue<ElevatorButton> getElevatorRequest() {
		return elevatorRequest;
	}

	public void setElevatorRequest(Queue<ElevatorButton> elevatorRequest) {
		this.elevatorRequest = elevatorRequest;
	}

	public Queue<FloorButton> getFloorRequest() {
		return floorRequest;
	}

	public void setFloorRequest(Queue floorRequest) {
		this.floorRequest = floorRequest;
	}
	
	/*
	 * NOTES
	 * Floor Button is potential passenger arriving outside elevator and seeking floor
	 * Elevator button is pressed from within elevator and seeking different floor
	 * Arrival Sensor notifies when elevator arrives
	 * Elevator lamp, list of floors to be visited
	 * Floor Lamp, direction of elevator sought by future passenger
	 * Direction Lamp, inside elevator, includes direction and arrival of elevator
	 */
}
