
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 
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
	@DisplayName("UDP Receive Test")
	void receivetest() {
		udp.sendByte(("Hello").getBytes());
		RecvData receivePacket = udp.receive();
		byte[] inputMsg = receivePacket.data;
		String s = new String(inputMsg);
		System.out.print(s);
	}

}
