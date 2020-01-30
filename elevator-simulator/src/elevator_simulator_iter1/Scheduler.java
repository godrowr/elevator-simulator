package elevator_simulator_iter1;

import java.util.List;
import java.util.Queue;

/**
 * 
 * @author RG
 *
 */
public class Scheduler implements Runnable{
	private Queue<ElevatorButton> elevatorRequest; //These are the buttons pushed in the elevator.
	private Queue<FloorButton> floorRequest; //These are the buttons pushed on the floors by waiting patrons. 
	
	public Scheduler() {
		
	}
	
	public void inputInfo(List<Button> buttons) {
		for(Button b: buttons) {
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			
		}
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
