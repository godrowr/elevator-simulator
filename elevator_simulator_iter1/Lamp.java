/**
 * 
 * @author Xander May
 *
 */
public abstract class Lamp {
	private boolean on;
	public Lamp () {
		this.on = false;
	}

	public void toggle(){
		this.on = !this.on;
	}

	public boolean isOn(){
		return this.on;
	}
}

/**
 * 
 * @author Xander May
 *
 */
class FloorLamp extends Lamp {
	public FloorLamp (){
		super();
	}
}


/**
 * 
 * @author Xander May
 *
 */
class ElevatorLamp extends Lamp {
	public ElevatorLamp (){
		super();
	}
}
