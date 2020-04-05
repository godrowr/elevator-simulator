

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The SchedulerTest class tests the functions of Scheduler such as Decode, QuerySubsystem, and Getnext floor. 
 * @author Ryan Godrow
 */

class SchedulerTest {

	private static Scheduler scheduler;
	private static FloorSubsystem floorsystem;
	private static RecvData recv;

	public SchedulerTest() {}
	
	@BeforeAll
	static void BeforeAll()
	{
		scheduler = new Scheduler(2);
		floorsystem = new FloorSubsystem();
	}
	
	/*
	 * Tests the decode function on the scheduler to insure the scheduler can understand RecvData messages. 
	 */
	@Test
	@DisplayName("Scheduler Decode Test")
	public void testDecode() {
		
		String message = "" + 1 + 2 + 2;

		recv = new RecvData();
		recv.data = message.getBytes();
		recv.port = 4;
		
		int[] val = scheduler.decodeMsg(recv);
		
		assertEquals(val[0],1);
		assertEquals(val[1],2);
		assertEquals(val[2],2);
	}
	
	/*
	 * Tests the query subsystem test to insure that the scheduler can retreive information from the floor subsystem
	 */
	@Test
	@DisplayName("Scheduler Query Subsystem Test")
	public void testQuery() {
		floorsystem.parseFile();
		ArrayList<FloorButton> buttons = scheduler.querySubsystem();
		assertTrue(buttons.get(0).getFloor() == 2);
		assertTrue(buttons.get(1).getFloor() == 3);
		assertTrue(buttons.get(2).getFloor() == 5);
		assertTrue(buttons.get(3).getFloor() == 4);
	}
	
	

}
