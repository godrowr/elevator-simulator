package elevator_simulator_iter1;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class Floor_subsystem {
	private Scheduler scheduler;
	
	public Floor_subsystem(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public void sendInfoToScheduler(Button button) {
		scheduler.sendInfoToElevator(button);
	}
	
	public void readInEvent(URI inputFile) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(inputFile));
			for(String line : lines) {
				String[] splited = line.split("\\s+");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Floor extends Floor_subsystem{
	private int floorNo;
	private ArrayList<FloorButton> buttonList;

	public Floor(Scheduler scheduler, int floorNumber) {
		super(scheduler);
		this.floorNo = floorNumber;
		// TODO Auto-generated constructor stub
	}

	public int getFloorNo() {
		return floorNo;
	}

}
