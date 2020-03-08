package elevator_simulator_iter1;

/**
 * A data object that holds bytes sent from one system to the next. 
 */
public class RecvData {
	public int port;
	public byte[] data;
	
	RecvData(byte[] data, int port){
		this.data = data;
		this.port = port; 
	}
}