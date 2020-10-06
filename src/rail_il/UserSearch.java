package rail_il;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.Set;

public class UserSearch {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Managementable uiManager = new ConsoleUI();
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("/home/afeka/workspace/Rail_IL/rail.dat"));
		uiManager.setAllRides((Set<Ride>)inFile.readObject());
		inFile.close();

		String time = args[1] + ":" + args[2];
		System.out.println(time + "\n");
	
//		SearchRideByUser(uiManager, args[0], args[1], args[2], args[3]);

	}
	
	public static void SearchRideByUser(Managementable uiManager, String departureStation, String hour, String minute, String destinationStation ){
		
		String time = hour + ":" + minute;
		System.out.println(time + "\n");
		uiManager.searchRide(departureStation, LocalTime.parse(time), destinationStation);
		System.out.println("We found " + uiManager.getNumOfResult() + " rides that matching your search..");
		
		for(int i = 0 ; i < uiManager.getNumOfResult() ; i++)
			System.out.println("\nRide #" + (i+1) + ":\n" + uiManager.getSearchResult()[i].toString());
	}

}
