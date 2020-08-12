package rail_il;

import java.time.LocalTime;
import java.util.Scanner;

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
					System.out.println("Do you want do add anothr ride? <y/n>");
					cKey = s.next().charAt(0);
				}while(cKey == 'y' || cKey == 'Y');//loop continue if the user put a wrong key
				break;
			case 2:
				rail.sortByHour();
				for (int i = 0; i < rail.getNumOfRides() ; i++)
					if(rail.getNumOfRides() != 0)
						System.out.println("Ride #" + (i+1) + "\n" + rail.getAllRides()[i].toString());
				break;
			case 9:
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("invalid input");
			}
		}while(iKey!= '9');
	}

	private static void getRideFromUser(SystemManagement Rial) {
		String tampDepTime, tampDesTime ,tampDepStation ,tampDesStation;

		System.out.println("Please enter departure station:");
		s.nextLine();
		tampDepStation = s.nextLine();
		System.out.println("Please enter a departure time (in format HH:MM)");
		tampDepTime = s.nextLine();
		
		System.out.println("Please enter destination station:");
		tampDesStation = s.nextLine();
		System.out.println("Please enter a destination time (in format HH:MM)");
		tampDesTime = s.nextLine();
		
		Rial.addRide(tampDepStation, tampDepTime, tampDesStation, tampDesTime);
		}
	}