package elevator_simulator_iter1;

import java.util.List;
import java.util.Queue;

public class Scheduler implements Runnable{
	private Queue elevatorRequest; //These are the buttons pushed in the elevator.
	private Queue floorRequest; //These are the buttons pushed on the floors by waiting patrons. 
	
	public Scheduler() {
		
	}
	
	public void inputInfo(List<Button> buttons) {
		for (Button button : buttons) {
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	public Queue getElevatorRequest() {
		return elevatorRequest;
	}

	public void setElevatorRequest(Queue elevatorRequest) {
		this.elevatorRequest = elevatorRequest;
	}

	public Queue getFloorRequest() {
		return floorRequest;
	}

	public void setFloorRequest(Queue floorRequest) {
		this.floorRequest = floorRequest;
	}
}
