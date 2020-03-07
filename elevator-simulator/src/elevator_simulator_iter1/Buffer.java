import java.util.*;

public class Buffer {
	
	private ArrayList<RecvData> storage = new ArrayList<RecvData>();
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
	public synchronized void add(RecvData inputPacket) {
		storage.add(inputPacket);
		empty = false;
	}
	
	/*
	 * Scheduler gets info from buffer
	 */
	public synchronized RecvData get() {
		while(empty) {
			// Wow such conditional synchronization
			try {
				wait();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		if(storage.size() <= 1) {
			empty = true;
		}
		return storage.remove(0);
	}
}
