
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


public class FloorSubsystem {
	private static final int NUMFLOORS = 5;
	private int you = 0;
	private List<Floor> floors = new ArrayList<Floor>();
	private ArrayList<FloorButton> buttons = new ArrayList<FloorButton>();

	public FloorSubsystem() {
		for(int i = 1; i <= NUMFLOORS; i++) {
			if (i == NUMFLOORS) { //Penthouse floor
				floors.add(new Floor(i, FloorType.PENTHOUSE));
			} else if (i == 1) { //Ground floor
				floors.add(new Floor(i, FloorType.GROUND));
			} else { //Any other floor
				floors.add(new Floor(i, FloorType.NORMAL));
			}
						//System.out.println("Floor Added");
		}
	}

	/*
	 * 
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
	* Works based off of elapsed time
	*/
	public ArrayList<FloorButton> getRequest(Instant time){
		ArrayList<FloorButton> tempButtons = new ArrayList<FloorButton>();
		int counter = 0;
		for(FloorButton button: buttons){
			// if this param is greater positive value
			// if this param is equal zero value
			// if this param is lesser negative value
			// CHANGED THIS BACK TO if(time.compareTo(button.getTime()) >= 0){ 
			tempButtons.add(button);
			//	this.buttons.remove(counter);
				// We dont increment counter when removing from array because
				// indices change
			//} else {
				// Increment if we didnt
			//	counter+=1;
			//}
		}
		return tempButtons;
	}
}


class Floor {
	private int floorNo;
	private List<FloorButton> buttonList = new ArrayList<FloorButton>();

	public Floor(int floorNumber, FloorType type) {
		this.floorNo = floorNumber;
	}
	
	public int getFloorNo() {
		return floorNo;
	}
}
