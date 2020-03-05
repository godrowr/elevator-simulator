import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class Main {
	
	public static void main(String[] args) {
		Thread elevatorSystem, floorSystem, schedulerSystem;
		Scheduler schedule;
		ElevatorSubsystem elevatorsys;
		FloorSubsystem floorsys;
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("Number of Elevators in the building?");
		int elevatorNo = Integer.parseInt(input.nextLine());
		
		System.out.println("Number of Floors in the building?");
		int floorNo = Integer.parseInt(input.nextLine());
		*/
		schedule = new Scheduler();
		elevatorsys = new ElevatorSubsystem(schedule, 1);
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
