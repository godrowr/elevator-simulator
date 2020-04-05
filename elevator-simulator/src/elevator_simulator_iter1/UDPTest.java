
import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This test file tests the recieve and send functions of the UDP class. 
 * @author Ryan Godrow
 */
class UDPTest {
	static UDP udp;
	
	@BeforeAll
	static void BeforeAll() {
		try {
			 udp = new UDP(5701, 5701, InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("UDP Receive & Send Test")
	void receiveSendTest() {
		udp.sendByte(("Hello").getBytes());
		RecvData receivePacket = udp.receive();
		byte[] inputMsg = receivePacket.data;
		String s = new String(inputMsg).trim();
		assertTrue(s.equals("Hello"));
	}

}
