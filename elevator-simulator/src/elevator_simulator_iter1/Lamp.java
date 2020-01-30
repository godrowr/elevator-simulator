package elevator_simulator_iter1;

public class Lamp {

}

/**
 * 
 * @author RG
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
	
}
