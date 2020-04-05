

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * 
 * @author Ryan Godrow
 *
 */
class FloorSubsystemTest {
	
	private static FloorSubsystem floorSystem = new FloorSubsystem();
	//private static Scheduler scheduler = new Scheduler(2);
	
	@BeforeAll
	static void BeforeAll() {
		//scheduler = new Scheduler(2);
		//floorSystem = new FloorSubsystem();
	}

	@Test
	@DisplayName("Floor System Parse Test")
	void parseTest() {
		
		floorSystem.parseFile();
		
		FloorButton ftestButton = new FloorButton("2020-11-29 00:00:15.0", 2, 4);
		
		//ElevatorButton etestButton = new ElevatorButton(2,4);
		ArrayList<FloorButton> newReqs = this.floorSystem.getRequest();
		
		assertEquals(((FloorButton)newReqs.get(0)).getTime(),ftestButton.getTime());
		assertEquals(((FloorButton)newReqs.get(0)).getFloor(),ftestButton.getFloor());
		assertEquals(((FloorButton)newReqs.get(0)).getDest(),ftestButton.getDest());
		
		ftestButton = new FloorButton("2020-11-29 00:00:30.0", 3, 5);
		
		assertEquals(((FloorButton)newReqs.get(1)).getTime(),ftestButton.getTime());
		assertEquals(((FloorButton)newReqs.get(1)).getFloor(),ftestButton.getFloor());
		assertEquals(((FloorButton)newReqs.get(1)).getDest(),ftestButton.getDest());
		
		
		ftestButton = new FloorButton("2020-11-29 00:00:59.0", 5, 1);
		
		assertEquals(((FloorButton)newReqs.get(2)).getTime(),ftestButton.getTime());
		assertEquals(((FloorButton)newReqs.get(2)).getFloor(),ftestButton.getFloor());
		assertEquals(((FloorButton)newReqs.get(2)).getDest(),ftestButton.getDest());
		
		
		ftestButton = new FloorButton("2020-11-29 00:01:10.0", 4, 1);
		
		assertEquals(((FloorButton)newReqs.get(3)).getTime(),ftestButton.getTime());
		assertEquals(((FloorButton)newReqs.get(3)).getFloor(),ftestButton.getFloor());
		assertEquals(((FloorButton)newReqs.get(3)).getDest(),ftestButton.getDest());
		
	}
	
	@Test
	@DisplayName("Floor Creation Test")
	void floorTest() {
		Floor floor = new Floor(1, FloorType.GROUND);
		ArrayList<Floor> floors = (ArrayList<Floor>) this.floorSystem.getFloors();	
		
		assertEquals(((Floor)floors.get(0)).getFloorNo(),floor.getFloorNo());
		assertEquals(((Floor)floors.get(0)).getType(),floor.getType());
		
		floor = new Floor(2, FloorType.NORMAL);
		
		assertEquals(((Floor)floors.get(1)).getFloorNo(),floor.getFloorNo());
		assertEquals(((Floor)floors.get(1)).getType(),floor.getType());
		
		floor = new Floor(5, FloorType.PENTHOUSE);
		
		assertEquals(((Floor)floors.get(4)).getFloorNo(),floor.getFloorNo());
		assertEquals(((Floor)floors.get(4)).getType(),floor.getType());
	}

}
