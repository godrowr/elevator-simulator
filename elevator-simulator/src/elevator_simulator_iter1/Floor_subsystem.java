package elevator_simulator_iter1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Floor_subsystem implements Runnable{
	private Scheduler scheduler;
	
	public Floor_subsystem(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public void sendInfoToScheduler(List<Button> buttons) {
		scheduler.inputInfo(buttons);
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			List<String[]> morelines = new ArrayList<String[]>();
			InputStream in = Floor_subsystem.class.getResourceAsStream("inputFile.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String l;
			while ((l = reader.readLine()) != null) {
				System.out.println(l);
				String[] splited = l.split("\\s+");
				morelines.add(splited);
			}
            
			parseMoreLinesToEvents(morelines);
            
		} catch ( IOException e1) {
			// TODO Auto-generated catch block URISyntaxException |
			e1.printStackTrace();
		} 

	}
	
	public void parseMoreLinesToEvents(List<String[]> list) {
		List<Button> buttons = new ArrayList<Button>();
		FloorButton floor;
		ElevatorButton destination;
		for(String[] line : list) {
			floor = new FloorButton(line[0], Integer.parseInt(line[1]), line[2]);
			destination = new ElevatorButton(Integer.parseInt(line[1]), Integer.parseInt(line[3]));
			buttons.add(floor);
			buttons.add(destination);
		}
		
		sendInfoToScheduler(buttons);
		
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
