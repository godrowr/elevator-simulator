package elevator_simulator_iter1;

public class Main {
	
	public static void main(String[] args) {
		Thread elevatorSystem, floorSystem, schedulerSystem;
		Scheduler schedule;
		Elevator_subsystem elevators;
		Floor_subsystem floors;
		
		schedule = new Scheduler();
		elevators = new Elevator_subsystem(schedule);
		floors = new Floor_subsystem(schedule);
		schedulerSystem = new Thread (schedule,"Scheduler");
		elevatorSystem = new Thread (elevators, "Elevator System");
		floorSystem = new Thread (floors, "Floor System");
		schedulerSystem.start();
		elevatorSystem.start();
		floorSystem.start();
		
	}
}
