package elevator_simulator_iter1;

public class Lamp {

}

/**
 * 
 * @author Ryan Gaudreault
 *
 */
class FloorLamp extends Lamp {
	private int floorNo;
	private boolean on;
	
	public FloorLamp(int floorNo) {
		this.floorNo = floorNo;
		on = false; 
	}
}


class ElevatorLamp extends Lamp {
	private int lampStatus;
	private int lampFloor;
	
	public ElevatorLamp(int lampFloor, int lampStatus) {
		this.lampFloor = lampFloor;
		this.lampStatus = lampStatus;
	}
	
	public void lampOn() {
		lampStatus = 1;
	}
	
	public void lampOff() {
		lampStatus = 0;
	}
	
	public void setLampFloor(int lampFloor) {
		this.lampFloor = lampFloor;
	}
}
