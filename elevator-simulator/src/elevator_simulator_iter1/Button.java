package elevator_simulator_iter1;

/**
 * 
 * @author Ryan Gaudreault
 *
 */
public class Button {
	private boolean push; 
	
	public void pushButton() {
		push = true;
		//do something;
		push = false;
	}
	
	public void turnOnLamp() {
		
	}

}

/**
 * This class exteneds Button but is responsible for interfacing with user requests for an elevator on a paticular floor. 
 * @author Ryan Gaudreault
 *
 */
class FloorButton extends Button {
	private String time;
	private int floor;
	private String direction;
	private FloorLamp lamp;
	private int key;
	
	public FloorButton(String time, int floor, String direction, int key) {
		this.setTime(time);
		this.setFloor(floor);
		this.setDirection(direction); 
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public FloorLamp getLamp() {
		return lamp;
	}

	public void setLamp(FloorLamp lamp) {
		this.lamp = lamp;
	}
	
}


class ElevatorButton extends Button {
	private ElevatorLamp lamp;
	private int destinationFloor;
	private int key;
	
	public ElevatorButton(int destinationFloor, int key) {
		this.destinationFloor = destinationFloor;
		this.lamp = new ElevatorLamp(destinationFloor, 1);
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	
	
	
}