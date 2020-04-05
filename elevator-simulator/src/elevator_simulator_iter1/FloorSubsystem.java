import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
	

enum FloorType {
	PENTHOUSE,
	NORMAL,
	GROUND
}

/**
 * The Floor Subsystem class is responsible for handling all the requests in the building by parsing an inputText file
 * that contains the requests. It also builds the floors for the building. 
 * @author Ryan Godrow
 */
public class FloorSubsystem {
	private static final int NUMFLOORS = 5;
	private List<Floor> floors = new ArrayList<Floor>();
	private ArrayList<FloorButton> buttons = new ArrayList<FloorButton>();

	/**
	 * The constructor for the floor subsystem creates the floors and assigns it a type. 
	 */
	public FloorSubsystem() {
		for(int i = 1; i <= NUMFLOORS; i++) {
			if (i == NUMFLOORS) { //Penthouse floor
				floors.add(new Floor(i, FloorType.PENTHOUSE));
			} else if (i == 1) { //Ground floor
				floors.add(new Floor(i, FloorType.GROUND));
			} else { //Any other floor
				floors.add(new Floor(i, FloorType.NORMAL));
			}
		}
	}

	/**
	 * This method parses the input text file and creates button requests from it. 
	 */
	public void parseFile() {
		List<String[]> morelines = new ArrayList<String[]>();
		try {
			InputStream in = FloorSubsystem.class.getResourceAsStream("inputFile.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String l;
			while ((l = reader.readLine()) != null) {
				//System.out.println(l);
				String[] splited = l.split("\\s+");
				morelines.add(splited);
			}
            
		} catch ( IOException e1) {
			e1.printStackTrace();
		} 
		for(String[] line : morelines) {
			FloorButton request = new FloorButton(line[0]+" "+line[1], Integer.parseInt(line[2]), Integer.parseInt(line[4]));
			buttons.add(request);
		}
	}

	/**
	 * Send back the floor requests that have become available since we last checked
	 * Works based off of elapsed time.
	 * @param time
	 * @return tempbuttons 
	 */
	public ArrayList<FloorButton> getRequest(Instant time){ 
		return buttons;
	}
	
	/**
	 * Send back the floor requests that have become available since we last checked 
	 * @return buttons;
	 */
	public ArrayList<FloorButton> getRequest(){ 
		return buttons; 
	}
	
	/**
	 * Returns the floors of the building. 
	 * @return
	 */
	public List<Floor> getFloors(){
		return floors;
	}
}


class Floor {
	private int floorNo;
	private FloorType type;
	
	public Floor(int floorNumber, FloorType type) {
		this.floorNo = floorNumber;
		this.type = type;
	}
	
	public int getFloorNo() {
		return floorNo;
	}
	
	public FloorType getType() {
		return type;
	}
}
