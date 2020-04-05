import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The SchedulerTest class tests the functions of Scheduler such as Decode, QuerySubsystem, and Getnext floor. 
 * @author Ryan Godrow
 */
class SchedulerTest {
	
	private static ElevatorButton eButton;
	private static FloorButton fButton;
	private static Scheduler scheduler;
	private static List<Button> buttonList;
	private static RecvData recv;

	public SchedulerTest() {}
	
	@BeforeAll
	static void BeforeAll()
	{
		scheduler = new Scheduler(2);
	}
	
	/*
	 * 
	 */
	@Test
	@DisplayName("Scheduler <> Test")
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

}
