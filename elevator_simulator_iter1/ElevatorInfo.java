import java.io.*;

/**
 * This file simply holds the data of an elevator in a human readable format. 
 * @author RG
 *
 */
public class ElevatorInfo{
	private int dest;
	private int elevatorNo;
	private int current;
	
	public ElevatorInfo(int elevatorNo, int current, int dest) {
		this.elevatorNo=elevatorNo;
		this.current=current;
		this.dest=dest;
	}
	
	public int[] getElements() {
		return (new int[]{this.dest, this.current, this.elevatorNo});
	}
}
