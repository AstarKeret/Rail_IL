package rail_il;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RailProgram {


	public static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		final int MAX_RIDES = 20;

		char  cKey = 0;
		int iKey;
		int numOfRides = 0;
		Ride[] allRides = new Ride[MAX_RIDES] ;

		do {
			System.out.println("\tWellcom\n[1] Add new ride\n[2] Print all rides\n[9] Exit");//"menu"
			iKey = s.nextInt();
			switch(iKey) {
			case 1:
				do {
					if(numOfRides == MAX_RIDES) {
						System.out.println("No room to add a new ride");
						continue;
					}
					allRides[numOfRides] = getRideFromUser();
					numOfRides++;
					sortByHour(allRides);
					System.out.println("Do you want do add anothr ride? <y/n>");
					cKey = s.next().charAt(0);		
				}while(cKey == 'y' || cKey == 'Y');//loop continue if the user put a wrong key
				break; 
			case 2:
				for (int i = 0; i < allRides.length; i++) {
					if(allRides[i] != null) {
						System.out.println(allRides[i].toString());
					}

				}
				break;
			case 9: 
				System.out.println("Good bey");
				break;
			default:
				System.out.println("invalid input");
			}
		}while(iKey!= '9');
	}


	private static Ride getRideFromUser() {
		Ride newRide = new Ride();

		String tempTime;

		System.out.println("Please enter departure station:");
		tempTime = s.next();
		newRide.setDepartureStation(tempTime);

		System.out.println("Please enter a departure time (in format HH:MM):");
		tempTime = s.next();
		newRide.setDepartureTime(LocalTime.parse(tempTime));

		System.out.println("Please enter destination station:");
		tempTime = s.next();
		newRide.setDestinatioStation(tempTime);

		System.out.println("Please enter a destination time (in format HH:MM):");
		tempTime = s.next();
		newRide.setDestinationTime(LocalTime.parse(tempTime));

		return newRide;
	}

	private static void sortByHour(Ride[] allRides) {
		for (int i = 1; i < allRides.length; ++i) {
			if(allRides[i] == null)
				break;
			Ride r = allRides[i];
			int j = i-1;

			while(j>=0 && allRides[j].getDepartureTime().isAfter(r.getDepartureTime()) ) {
				allRides[j+1] = allRides[j];
				j=j-1;
			}
			allRides[j+1] = r;
		}
	}
}

