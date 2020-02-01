package elevator_simulator_iter1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Ryan Gaudreault
 * @author Andrew Cowan
 */
public class Scheduler implements Runnable{
	private Queue<ElevatorButton> elevatorRequest; //These are the buttons pushed in the elevator.
	private Queue<FloorButton> floorRequest; //These are the buttons pushed on the floors by waiting patrons. 
	private boolean elevatorWriteable = true;
	public Scheduler() {

		elevatorRequest = new ArrayDeque<ElevatorButton>() ; //These are the buttons pushed in the elevator.
		floorRequest = new ArrayDeque<FloorButton>(); //These are the buttons pushed on the floors by waiting patrons. 
		elevatorWriteable = true;
	}
	/*
	 * @param List of Button Objects
	 * Thread safe method to add:
	 * Elevator Buttons to elevatorRequest Queue
	 * Floor Button to floorRequest Queue
	 */
	public synchronized void inputButtonInfo(List<Button> buttons) {
		
		while(!elevatorWriteable) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		//System.out.println(buttons);
		elevatorWriteable = false;
		
		FloorButton fButton = (FloorButton) buttons.get(0);
		floorRequest.add(fButton);
		
		ElevatorButton eButton = (ElevatorButton) buttons.get(1);
		elevatorRequest.add(eButton);
		
		elevatorWriteable = true;
		buttons.clear();
		notifyAll();
	}
	
	/*
	 * Receive info from Arrival Sensor
	 * Determine elevator direction, clear Lamp/Button
	 * TO BE IMPLEMENTED IN ITERATION 2
	 * @param ArrivalSensor
	 */
	private void inputArrivalInfo(ArrivalSensor floorArrived) {
		
		while(!elevatorWriteable) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		
		elevatorWriteable = false;
		
		for(ElevatorButton eButton : elevatorRequest) {
			if(eButton.getCurfloor() == floorArrived.getCurFloor()) {
				
			}
		}
		
		elevatorWriteable = true;
		notifyAll();
	}
	
	/*
	 * Scheduler receives info from elevator (direction, current floor)
	 * @returns appropriate floorButton for elevator to service
	 */
	private void getFloorButton(Elevator elevator) {
		
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
