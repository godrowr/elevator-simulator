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
	private String direction;
	
	public FloorLamp(int floorNo, String direction) {
		this.floorNo = floorNo;
		this.direction = direction;
		on = false; 
	}
}


class ElevatorLamp extends Lamp {
	
}
