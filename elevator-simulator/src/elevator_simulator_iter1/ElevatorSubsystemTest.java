package elevator_simulator_iter1;

//import static org.junit.jupiter.api.Assertions.*;
//import java.io.BufferedReader;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class ElevatorSubsystemTest {
	
	private static ElevatorSubsystem elevatorSystem;
	private static Scheduler scheduler;
	
	@BeforeAll
	static void BeforeAll() {
		scheduler = new Scheduler(2);
		elevatorSystem = new ElevatorSubsystem(scheduler,2);
	}

	@Test
	@DisplayName("Elevator System <> Test")
	void testNextStop() {
		
//		assertEquals(((FloorButton)newReqs.get(3)).getTime(),ftestButton.getTime());
//		assertEquals(((FloorButton)newReqs.get(3)).getFloor(),ftestButton.getFloor());
//		assertEquals(((FloorButton)newReqs.get(3)).getDest(),ftestButton.getDest());
		
	}
	
	@Test
	@DisplayName("Elevator System <> Test")
	void testDecode() {
		
//		assertEquals(((FloorButton)newReqs.get(3)).getTime(),ftestButton.getTime());
//		assertEquals(((FloorButton)newReqs.get(3)).getFloor(),ftestButton.getFloor());
//		assertEquals(((FloorButton)newReqs.get(3)).getDest(),ftestButton.getDest());
		
	}
	
	@Test
	@DisplayName("Elevator System <> Test")
	void testUpdate() {
		
//		assertEquals(((FloorButton)newReqs.get(3)).getTime(),ftestButton.getTime());
//		assertEquals(((FloorButton)newReqs.get(3)).getFloor(),ftestButton.getFloor());
//		assertEquals(((FloorButton)newReqs.get(3)).getDest(),ftestButton.getDest());
		
	}
	
	@Test
	@DisplayName("Elevator System <> Test")
	void testGotoFloor() {
		
//		assertEquals(((FloorButton)newReqs.get(3)).getTime(),ftestButton.getTime());
//		assertEquals(((FloorButton)newReqs.get(3)).getFloor(),ftestButton.getFloor());
//		assertEquals(((FloorButton)newReqs.get(3)).getDest(),ftestButton.getDest());
		
	}
	
	

}
