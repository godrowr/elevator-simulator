import java.util.*;

/**
 * The Buffer class which holds an array of Recvdata objects. The buffer is called to provide the RecvData objects in order
 * and keeps all data stored in one location. 
 */
public class Buffer {
	
	private ArrayList<RecvData> storage = new ArrayList<RecvData>();
	private boolean empty;
	
	/*
	 * Creates an empty buffer. 
	 */
	public Buffer() {
		empty = true;
	}
	
	/**
	 * Receives packet with format: elevatorNo, current, dest
	 * adds ElevatorInfo object to storage
	 * @param inputPacket The value to add the storage of RecvData objects. 
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
