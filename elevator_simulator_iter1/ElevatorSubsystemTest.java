

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;
//import java.io.BufferedReader;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * ElevatorSubsystemTest tests the functions of ElevatorSubsystem such as NextStop, Decode, Update, and GotoFloor. 
 * @author Ryan Godrow
 */
class ElevatorSubsystemTest {
	
	private static ElevatorSubsystem elevatorSystem;
	private static Scheduler scheduler;
	private static RecvData recv;
	private static Elevator elevator;
	
	@BeforeAll
	static void BeforeAll() {
		scheduler = new Scheduler(2);
		elevatorSystem = new ElevatorSubsystem(scheduler,2);
		recv = new RecvData();
		elevator = elevatorSystem.getElevator(0);
	}

	@Test
	@DisplayName("Elevator System NextStop Test")
	void testNextStop() {

		recv.data = ("" + 2 + 2).getBytes();
		elevator.addButtonList(recv);
		assertEquals(elevator.nextStop(),2);
		
	}
	
	@Test
	@DisplayName("Elevator System Decode Test")
	void testDecode() {
		
		String message = "" + 1 + 2;

		recv = new RecvData();
		recv.data = message.getBytes();
		
		ElevatorButton e = elevator.decodeMsg(recv.data);
		
		assertEquals(e.getFloor(),1);
		assertEquals(e.getDest(),2);
		
	}
	
	@Test
	@DisplayName("Elevator System Test Goto Floor")
	void testGotofloor() {
		assertEquals(elevator.gotoFloor(5), 5);
	}

}
