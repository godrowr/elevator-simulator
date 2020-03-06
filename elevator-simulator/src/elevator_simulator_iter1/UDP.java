import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.net.*;

public class UDP{

	private int receivePortNum;
	private int sendPortNum;
	private InetAddress iPAddress;
	private DatagramSocket sendReceiveSocket;
	private DatagramPacket receivePacket;
	private DatagramPacket sendPacket;
	
	public UDP(int receivePortNum, int sendPortNum, InetAddress iPAddress){
	
		this.receivePortNum = receivePortNum;
		this.sendPortNum = sendPortNum;
		this.iPAddress = iPAddress;
		initializeSocket();
		
	}
	
	private void initializeSocket(){
	
		try {
		   sendReceiveSocket = new DatagramSocket(receivePortNum);
	   } catch (SocketException se) {
		   se.printStackTrace();
		   System.exit(1);
	   }
	}
	

	private byte[] receive(){
	
		byte data[] = new byte[100];
		receivePacket = new DatagramPacket(data, data.length);
		
		try {
			sendReceiveSocket.receive(receivePacket);
		}catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		
		return data;
	}
	
	public void sendByte(byte[] inputMsg){
		   
		try {
			sendPacket = new DatagramPacket(inputMsg,inputMsg.length,iPAddress,sendPortNum);
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			sendReceiveSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
