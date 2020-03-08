package elevator_simulator_iter1;

import java.io.*;

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
