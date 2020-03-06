
import java.time.Instant;


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


class FloorButton extends Button {
	private Instant time;
	
	public FloorButton(String time, int floor, int dest) {
		super(floor, dest);
		System.out.println(time);
		this.time = java.sql.Timestamp.valueOf(time).toInstant();
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


class ElevatorButton extends Button {
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