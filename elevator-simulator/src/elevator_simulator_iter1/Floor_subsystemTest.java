package elevator_simulator_iter1;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Floor_subsystemTest {
	
	private static Floor_subsystem floorSystem;
	private static Scheduler scheduler;
	
	@BeforeAll
	static void BeforeAll() {
		scheduler = new Scheduler();
		floorSystem = new Floor_subsystem(scheduler,5);
	}

	@Test
	@DisplayName("Floor System Test")
	void parseTest() {
		
		List<Button> parsedList = floorSystem.inputFile();
		
		FloorButton ftestButton = new FloorButton("14:05:15.0", 2, "Up");
		ElevatorButton etestButton = new ElevatorButton(2,4);
		
		assertEquals(((FloorButton)parsedList.get(0)).getTime(),ftestButton.getTime());
		assertEquals(((FloorButton)parsedList.get(0)).getFloor(),ftestButton.getFloor());
		assertEquals(((FloorButton)parsedList.get(0)).getDirection(),ftestButton.getDirection());

		assertEquals(((ElevatorButton)parsedList.get(1)).getFloorNo(),etestButton.getFloorNo());
		assertEquals(((ElevatorButton)parsedList.get(1)).getCurfloor(),etestButton.getCurfloor());
		
	}

}
