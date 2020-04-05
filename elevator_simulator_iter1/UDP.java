import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.net.*;

/**
 * The UDP class that is responsible for establishing communications with several classes if they are on different 
 * computer systems in a network. 
 */
public class UDP{

	private int receivePortNum;
	private int sendPortNum;
	private InetAddress iPAddress;
	private DatagramSocket sendReceiveSocket;
	private DatagramPacket receivePacket;
	private DatagramPacket sendPacket;
	
	/**
	 * Creates a UDP socket with a specified recieve and send port number, along with the ipaddress of its target.
	 * @param receivePortNum The port to listen on
	 * @param sendPortNum The port to send to 
	 * @param iPAddress The target
	 */
	public UDP(int receivePortNum, int sendPortNum, InetAddress iPAddress){
	
		this.receivePortNum = receivePortNum;
		this.sendPortNum = sendPortNum;
		this.iPAddress = iPAddress;
		initializeSocket();
		
	}
	
	/**
	 * Initializes the socket with its associated receive port. 
	 */
	private void initializeSocket(){
	
		try {
		    sendReceiveSocket = new DatagramSocket(receivePortNum);
	   } catch (SocketException se) {
		   se.printStackTrace();
		   System.exit(1);
	   }
	}
	
	/**
	 * Waits to receive data on the receive port, creates a recive packet and returns a RecvData object.  
	 * @return RecvData Convert from a packet and stored as this object for easier access by other classes. 
	 */
	public RecvData receive(){
	
		byte data[] = new byte[100];
		receivePacket = new DatagramPacket(data, data.length);
		
		try {
			sendReceiveSocket.receive(receivePacket);
		}catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		RecvData recv = new RecvData();
		recv.data = data;
		recv.port = receivePacket.getPort();
		return recv;
	}
	
	/**
	 * Sends a packet on the send port, taking in a byte array as its data. 
	 * @param inputMsg The byte array that holds the information in bytes. 
	 */
	public void sendByte(byte[] inputMsg){ //This appears to be the root of the issue. 
		
		
		sendPacket = new DatagramPacket(inputMsg,inputMsg.length,iPAddress,sendPortNum);

		try {
			sendReceiveSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * 
	 */
	public void close() {
		this.sendReceiveSocket.close();
	}
}
