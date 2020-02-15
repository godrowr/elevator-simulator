package elevator_simulator_iter1;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This main class creates the three subsystem threads along with thier respective objects and starts them. 
 * @author Ryan Gaudreault
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
		System.out.println("Elevator requested at floor 2");
		System.out.println("Elevator moved to floor 2");
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
