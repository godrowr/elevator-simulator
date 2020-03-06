
import java.util.*;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.net.*;
import java.time.Instant;
import java.lang.Math;
import java.net.InetAddress;


enum Direction{
	UP,
	DOWN,
	STOP
}



public class Scheduler implements Runnable{
	private ArrayList<FloorButton> floorRequest; //These are the buttons pushed on the floors by waiting patrons. 
	private FloorSubsystem floorSubsystem;
	private Instant start;
	private Buffer buffer;
	private UDP uDP;

	public Scheduler(Buffer buffer, int elevatorNo) {
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
		
		this.buffer = buffer;
		
		//
		for(;elevatorNo > 0; elevatorNo--) {
			Worker w = null;
			try {
				w = new Worker(750+elevatorNo,70+elevatorNo,InetAddress.getLocalHost(),buffer);
			}catch(Exception e) {
				System.out.println(e);
			}
			Thread t = new Thread(w, "Workers");
			t.start();
		}
	}

	/**
	 * Query the floor subsystem for the new list of floors
	 * 
	 */
	private void querySubsystem(){
		//Instant elapsed = Instant.now().minus(this.start);
		ArrayList<FloorButton> newReqs = this.floorSubsystem.getRequest(Instant.now());
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
	public synchronized void getNextFloor() {
		boolean isEmpty;
		Direction dir;
		
		int[] getFromBuffer = buffer.get();
		int elevatorNo = getFromBuffer[0];
		int current = getFromBuffer[1];
		int dest = getFromBuffer[2];
		
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

		ElevatorButton nextStop;
		// Create the thing that we pass back to the elevator
		if (nextClosest == null && dir == Direction.STOP) {
			nextStop = new ElevatorButton(1, 1);
		} else if (nextClosest == null && dir != Direction.STOP) {
			nextStop = new ElevatorButton(-1, -1);
		} else {
			nextStop = new ElevatorButton(nextClosest.getFloor(), nextClosest.getDest());
		}
		ArrayList<FloorButton> remove = new ArrayList<FloorButton>();
		remove.add(nextClosest);
		this.floorRequest.removeAll(remove);
		
		//Do UDP send to elevator
		
		try {
			uDP = new UDP(elevatorNo+749,elevatorNo+60,InetAddress.getByName("100000"));
		}catch(Exception e) {
			System.out.println(e);
		}
		
		String returnMsg = "" + nextStop.getFloor() + nextStop.getDest();
		uDP.sendByte(returnMsg.getBytes());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		querySubsystem();
		while(true) {
			getNextFloor();
		}
	}

}

class Worker implements Runnable {
	
	private UDP uDP;
	private Buffer buffer;
	
	//Note receive port and send port must be hard coded between elevator and scheduler
		public Worker(int receivePortNum, int sendPortNum, InetAddress iPAddress, Buffer buffer) {
			try {
				UDP uDP = new UDP(receivePortNum,sendPortNum,InetAddress.getByName("100000"));
			}catch(Exception e) {
				System.out.println(e);
			}
			this.buffer = buffer;
		}
		
		public void run() {
			while(true) {
				buffer.add(uDP.receive());
			}
		}
}

