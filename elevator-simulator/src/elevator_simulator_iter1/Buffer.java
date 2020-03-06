import java.util.*;

public class Buffer {
	
	private ArrayList<ElevatorInfo> storage = new ArrayList<ElevatorInfo>();
	private boolean empty;
	
	/*
	 * [[elevatorNo,current,dest],[elevatorNo,current,dest]]
	 */
	public Buffer() {
		empty = true;
	}
	/*
	 * receive packet with format: elevatorNo, current, dest
	 * adds ElevatorInfo object to storage
	 */
	public synchronized void add(byte[] inputMsg) {
		storage.add(new ElevatorInfo(inputMsg[0],inputMsg[1],inputMsg[2]));
		empty = false;
	}
	
	/*
	 * Scheduler gets info from buffer
	 */
	public synchronized int[] get() {
		while(empty) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		if(storage.size() <= 1) {
			empty = true;
		}
		return storage.remove(0).getElements();
	}
}
