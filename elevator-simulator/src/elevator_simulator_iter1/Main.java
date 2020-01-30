package elevator_simulator_iter1;
import java.util.Scanner;

/**
 * This main class creates the three subsystem threads along with thier respective objects and starts them. 
 * @author RG
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Thread elevatorSystem, floorSystem, schedulerSystem;
		Scheduler schedule;
		Elevator_subsystem elevatorsys;
		Floor_subsystem floorsys;
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("Number of Elevators in the building?");
		int elevatorNo = Integer.parseInt(input.nextLine());
		
		System.out.println("Number of Floors in the building?");
		int floorNo = Integer.parseInt(input.nextLine());
		*/
		
		schedule = new Scheduler();
		elevatorsys = new Elevator_subsystem(schedule, 1);
		floorsys = new Floor_subsystem(schedule, 5);
		schedulerSystem = new Thread (schedule,"Scheduler");
		elevatorSystem = new Thread (elevatorsys, "Elevator System");
		floorSystem = new Thread (floorsys, "Floor System");
		schedulerSystem.start();
		elevatorSystem.start();
		floorSystem.start();
		
	}
}
