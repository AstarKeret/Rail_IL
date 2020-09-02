package rail_il;

import java.time.LocalTime;
import java.util.Scanner;

import rail_il.SystemManagement.eReason;

public class RailProgram {
	public static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		char  cKey = 0;
		int iKey;
		SystemManagement rail = new SystemManagement();

		do {
			System.out.println("\tWellcom\n[1] Add new ride\n[2] Print all rides\n[9] Exit");//"menu"
			iKey = s.nextInt();
			switch(iKey) {
			case 1:
				do {
					if(rail.getNumOfRides() == SystemManagement.MAX_RIDES) {
						System.out.println("No room to add a new ride");
						continue;
					}
					getRideFromUser(rail);
					System.out.println("Do you want do add another ride? <y/n>");
					cKey = s.next().charAt(0);
				}while(cKey == 'y' || cKey == 'Y');//loop continue if the user put a wrong key
				break;
			case 2:
				rail.sortByHour();
				System.out.println(rail.toString() + "\n");
				break;
			case 9:
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("invalid input");
			}
		}while(iKey!= 9);
	}

	private static void getRideFromUser(SystemManagement rail) {
		String tampDepTime, tampDesTime ,tampDepStation ,tampDesStation;
		int numOfStops;	
		
		System.out.println("Please enter departure station:");
		s.nextLine();
		tampDepStation = s.nextLine();
		System.out.println("Please enter a departure time (in format HH:MM)");
		tampDepTime = s.nextLine();
		
		System.out.println("Please enter destination station:");
		tampDesStation = s.nextLine();
		System.out.println("Please enter a destination time (in format HH:MM)");
		tampDesTime = s.nextLine();
		
		System.out.println("Do you want to add an intermediate stations? <y/n>");
		int key = s.next().charAt(0);		//gets from the user if the ride has intermediate stations 
		if(key == 'n')
			rail.addRide(tampDepStation, tampDepTime, tampDesStation, tampDesTime, 0);		//creates a new ride with no stops
		else{
			System.out.println("How many intermediate stations do you want to add?");
			numOfStops = s.nextInt();
			rail.addRide(tampDepStation, tampDepTime, tampDesStation, tampDesTime, numOfStops);		//creates a new ride with the entered number of stops
			getStopsFromUser(rail);		//function that getting from the user the intermediate stations details
		}
	}
	
	private static void getStopsFromUser(SystemManagement rail){
		
		s.nextLine();		//clean buffer
		for(int i = 0 ; i < rail.getAllRides()[rail.getNumOfRides() - 1].getAllStations().length - 2 ; i++){		//loop stop when i = current numOfStops 
			System.out.println("Please enter intermediate station number " + (i+1) + " details\nName: ");
			String tampName = s.nextLine();		// gets station name
			System.out.println("Departure Time: ");
			String tampTime = s.nextLine();		//gets station departure time
			
			SystemManagement.eReason tampEnum = rail.checkHourInRange(LocalTime.parse(tampTime));	//checks if the entered time is in range
			if(tampEnum != SystemManagement.eReason.OK){
				System.out.println("The time you entered is " + tampEnum.name() + "\nPlease enter the station details again");
				i--;	//sub i by 1 to insert again the station hour
				continue;	//skip the adding of the station to insert a new time
			}
			rail.getAllRides()[rail.getNumOfRides() - 1].addStation(tampName, LocalTime.parse(tampTime), Station.eType.INTERMEDIATE);	//creates and adding a new intermediate station to the current ride  
		}
	}
}