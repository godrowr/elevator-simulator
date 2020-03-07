package elevator_simulator_iter1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class Main {
	
	public static int debug = 1;
	
	public static void main(String[] args) {
		Thread elevatorSystem, floorSystem, schedulerSystem;
		Scheduler schedule;
		ElevatorSubsystem elevatorsys;
		FloorSubsystem floorsys;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Debug? (yes == 1 | 0 == no)");
		debug = Integer.parseInt(input.nextLine());
		
		
		schedule = new Scheduler(2);
		elevatorsys = new ElevatorSubsystem(schedule, 2);
		floorsys = new FloorSubsystem();
		schedulerSystem = new Thread (schedule,"Scheduler");
		
		schedulerSystem.start();
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
