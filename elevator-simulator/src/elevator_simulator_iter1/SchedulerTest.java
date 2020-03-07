package elevator_simulator_iter1;

/**
 * 
 */

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author DREW
 *
 */
class SchedulerTest {
	
	private static ElevatorButton eButton;
	private static FloorButton fButton;
	private static Scheduler scheduler;
	private static List<Button> buttonList;

	public SchedulerTest() {
	
	}
	
	@BeforeAll
	static void BeforeAll()
	{
		eButton = new ElevatorButton(0,4);
		fButton = new FloorButton("14:05:15.0", 4, "Up");
		scheduler = new Scheduler();
		buttonList = new ArrayList<Button>();
		
		buttonList.add(fButton);
		buttonList.add(eButton);
	}
	/*
	 * Tests inputButtonInfo method and getter/setter for elevator and floor buttons
	 */
	@Test
	@DisplayName("Scheduler Test")
	public void testInputButtonInfo() {
		
		scheduler.inputButtonInfo(buttonList);
		Queue<ElevatorButton> elevatorButtons = scheduler.getElevatorRequest();
		for (ElevatorButton testButton : elevatorButtons) {
			assertEquals(testButton.getFloorNo(),eButton.getFloorNo());
			assertEquals(testButton.getCurfloor(),eButton.getCurfloor());
		}
		
		Queue<FloorButton> floorButtons = scheduler.getFloorRequest();
		for (FloorButton testButton : floorButtons) {
			assertEquals(testButton.getTime(),fButton.getTime());
			assertEquals(testButton.getFloor(),fButton.getFloor());
			assertEquals(testButton.getDirection(),fButton.getDirection());
		}
	}
}
