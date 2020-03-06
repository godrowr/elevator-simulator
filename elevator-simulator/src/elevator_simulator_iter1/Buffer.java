import java.util.*;

public class Buffer {
	
	private ArrayList<ElevatorInfo> storage = new ArrayList<ElevatorInfo>();
	private boolean writeable;
	
	/*
	 * [[elevatorNo,current,dest],[elevatorNo,current,dest]]
	 */
	public Buffer() {
		writeable = true;
	}
	/*
	 * receive packet with format: elevatorNo, current, dest
	 * adds ElevatorInfo object to storage
	 */
	public synchronized void add(byte[] inputMsg) {
		while(!writeable) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		writeable = false;
		storage.add(new ElevatorInfo(inputMsg[0],inputMsg[1],inputMsg[2]));
		writeable = true;
	}
	
	/*
	 * Scheduler gets info from buffer
	 */
	public synchronized int[] get() {
		return storage.remove(0).getElements();
	}
}
