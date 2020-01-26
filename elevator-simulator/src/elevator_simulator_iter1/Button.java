package elevator_simulator_iter1;

public class Button {
	
	public void pushButton() {
		
	}
	
	public void turnOnLamp() {
		
	}

}

class FloorButton extends Button {
	private String time;
	private int floor;
	private String direction;
	private FloorLamp lamp;
	
	public FloorButton(String time, int floor, String direction) {
		this.time = time;
		this.floor = floor;
		this.direction = direction; 
	}
	
}

class ElevatorButton extends Button {
	private ElevatorLamp lamp;
	private int floorNo;
	private int curfloor;
	
	public ElevatorButton(int curfloor, int floorNo) {
		this.floorNo = floorNo;
	}
	
}