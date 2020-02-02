package elevator_simulator_iter1;

import java.util.concurrent.TimeUnit;

/**
 * This main class creates the three subsystem threads along with their respective objects and starts them. 
 * @author Ryan Gaudreault
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Thread elevatorSystem, floorSystem, schedulerSystem;
		Scheduler schedule;
		Elevator_subsystem elevatorsys;
		Floor_subsystem floorsys;
		int floors = 5;
		int elevators = 1;

		schedule = new Scheduler();
		elevatorsys = new Elevator_subsystem(schedule, elevators);
		floorsys = new Floor_subsystem(schedule, floors);
		schedulerSystem = new Thread (schedule,"Scheduler");
		elevatorSystem = new Thread (elevatorsys, "Elevator System");
		floorSystem = new Thread (floorsys, "Floor System");
		
		schedulerSystem.start();
		elevatorSystem.start();
		floorSystem.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//schedulerSystem.interrupt();
		//elevatorSystem.interrupt();
		//floorSystem.interrupt();
		
	}
}
