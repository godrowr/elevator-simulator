package elevator_simulator_iter1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Ryan Gaudreault 
 */
public class Floor_subsystem implements Runnable{
	private Scheduler scheduler;
	private List<Floor> floors = new ArrayList<Floor>();
	
	
	/**
	 * 
	 * @param scheduler
	 * @param floorNo
	 */
	public Floor_subsystem(Scheduler scheduler, int floorNo) {
		this.scheduler = scheduler;
		for(int i = floorNo;i > 0; i--) {
			if (i == floorNo) { //Penthouse floor
				floors.add(new Floor(floorNo, 0));
			} else if (i == 1) { //Ground floor
				floors.add(new Floor(floorNo, 1));
			} else { //Any other floor
				floors.add(new Floor(floorNo, 2));
			}
						//System.out.println("Floor Added");
		}
	}
	
	/**
	 * 
	 * @param buttons
	 */
	public synchronized void sendInfoToScheduler(List<Button> buttons) {
		scheduler.inputButtonInfo(buttons);
	}
	
	/**
	 * This 
	 */
	@Override
	public void run() {
		inputFile();
		while(true) {
			getInfoFromScheduler();
		}
	}
	
	/**
	 * This method reads in the inputFile.txt and puts it in a array string, separating 
	 * by an empty space, it is then passed to parseFile to be created into objects. 
	 */
	public void inputFile() {
		try {
			List<String[]> morelines = new ArrayList<String[]>();
			InputStream in = Floor_subsystem.class.getResourceAsStream("inputFile.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String l;
			while ((l = reader.readLine()) != null) {
				String[] splited = l.split("\\s+");
				morelines.add(splited);
			}
            
			parseFile(morelines);
		} catch ( IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * This method takes the array string of words from inputFile.txt and creates floor and elevator
	 * requests then sends this info to the scheduler. 
	 * @param list
	 */
	public void parseFile(List<String[]> list) {
		List<Button> buttons = new ArrayList<Button>();
		FloorButton floor;
		ElevatorButton destination;
		for(String[] line : list) {
			floor = new FloorButton(line[0], Integer.parseInt(line[1]), line[2]);
			destination = new ElevatorButton(Integer.parseInt(line[1]), Integer.parseInt(line[3]));
			buttons.add(floor);
			buttons.add(destination);
			sendInfoToScheduler(buttons);
		}
	}
	
	/**
	 * This method is to receive information from the scheduler.
	 * For the first iteration, this is simply used for testing purposes. 
	 */
	public void getInfoFromScheduler() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Queue buttons = scheduler.getFloorRequest();
		this.floors.get(0).setRequests(buttons);
	}

}

/**
 * 
 * @author Ryan Gaudreault
 *
 */
class Floor {
	private int floorNo;
	private List<FloorButton> buttonList = new ArrayList<FloorButton>();
	private List<FloorLamp> lampList = new ArrayList<FloorLamp>();

	/**
	 * 
	 * @param floorNumber
	 * @param type
	 */
	public Floor(int floorNumber, int type) {
		this.floorNo = floorNumber;
		if (type == 0) {
			buttonList.add(new FloorButton(null,floorNo,"Down"));
			lampList.add(new FloorLamp(floorNo, "Down"));
		} else if (type == 1) {
			buttonList.add(new FloorButton(null,floorNo,"Up"));
			lampList.add(new FloorLamp(floorNo, "Up"));
		} else {
			buttonList.add(new FloorButton(null,floorNo,"Up"));
			lampList.add(new FloorLamp(floorNo, "Up"));
			buttonList.add(new FloorButton(null,floorNo,"Down"));
			lampList.add(new FloorLamp(floorNo, "Down"));
		}
	}
	
	/**
	 * This method is to demonstrate that the Floor class can receive information from the scheduler.
	 * This method will recieve further 
	 * @param buttons
	 */
	public void setRequests(Queue<FloorButton> buttons) {
		Queue<FloorButton> requests = buttons;
		
		if(!requests.isEmpty()) {
			
			for(FloorButton fButton : requests) {
				System.out.println("Patron requested to go " + fButton.getDirection() + " at " + fButton.getTime() + " on floor " + fButton.getFloor());
			}
		}

	}

}
