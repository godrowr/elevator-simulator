import java.time.Instant;

/**
 * The superclass of button. Every button has a lamp, a destination to go to, 
 * a source to come from, and a boolean of if its been pushed or not. 
 */
class Button {
	private boolean pushed;
	protected Lamp lamp; 
	protected int floorNo;
	protected int dest;

	public Button(int floorNumber, int dest){
		this.floorNo = floorNumber;
		this.pushed = false;
		this.dest = dest;

	}
	
	public void pushButton() {
		if (!this.lamp.isOn()) {this.lamp.toggle();}
	}
}

/**
 * The class of button that is on floors, which requires a time object to prioritize when
 * buttons are pressed since the elevators are both first come first serve but also should decide if they
 * can pick up a passenger along the way. 
 */
class FloorButton extends Button {
	private Instant time;
	
	/**
	 * Builds a floor button. 
	 * @param time Time the button was pressed. 
	 * @param floor The floor it was pressed on.
	 * @param dest The destination of the passenger to descern if the passenger is going up or down.
	 */
	public FloorButton(String time, int floor, int dest) {
		super(floor, dest);
		//if(Main.debug == 1)System.out.println(time);
		this.time = java.sql.Timestamp.valueOf(time).toInstant();
		if(Main.debug == 1)System.out.println(this.time);
		this.lamp = new FloorLamp();
	}

	public Instant getTime() {
		return time;
	}

	public int getFloor() {
		return floorNo;
	}

	public int getDest() {
		return dest;
	}
	
}

/**
 * The class of button that is in elevators, which only requires a destination and a source. 
 */
class ElevatorButton extends Button {
	/**
	 * Builds a Elevator Button. 
	 * @param floorNo The floor it was pressed on.
	 * @param dest The floor the passenger wishs to travel to. 
	 */
	public ElevatorButton(int floorNo, int dest) {
		super(floorNo, dest);
		this.lamp = new ElevatorLamp();
	}

	public int getFloor() {
		return floorNo;
	}
	
	public int getDest() {
		return dest;
	}
}