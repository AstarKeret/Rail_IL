package rail_il;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class RailProgram {
	public static final Scanner s = new Scanner(System.in);
	
	public static Checkingable uiCheck = new ConsoleUI();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean fKeyOK = false;
		int iKey = 0;
		Managementable uiManager = new ConsoleUI();
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("rail.dat"));
		uiManager.setAllRides((Set<Ride>)inFile.readObject());
		inFile.close();
	while(!fKeyOK) {
			try {
				do {
						System.out.println("\tWellcom\n[1] Add new ride\n[2] Print all rides\n[3] Search\n[4] Save\n[9] Exit");//"menu"
						uiCheck.getChoice(iKey = s.nextInt(), 4);

					switch(iKey) {
					case 1:
						getRideFromUser(uiManager);
						break;
					case 2:
						System.out.println(uiManager.toString() + "\n");
						break;
					case 3:
						getSearchDetails(uiManager);
						break;
					case 4:
						ObjectOutputStream outFile4 = new ObjectOutputStream(new FileOutputStream("rail.dat"));
						outFile4.writeObject(uiManager.getAllRides());
						outFile4.close();
						System.out.println("Rides info saved");
						break;
					case 9:
						System.out.println("Goodbye");
						ObjectOutputStream outFile9 = new ObjectOutputStream(new FileOutputStream("rail.dat"));
						outFile9.writeObject(uiManager.getAllRides());
						outFile9.close();
						fKeyOK = true;
						break;
					}
				}while(iKey!= 9);
			}
			catch(YesOrNoException e) {
					System.out.println(e.getMessage());
			}
			catch(OutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.println("Invaild input");
				s.nextLine();
			}
		}
	}//end main
	

	
	private static void getSearchDetails(Managementable uiManager) {
		
		boolean fKeyOK= false;
		String departureStation = null;
		LocalTime departureTime = null;
		String destinationStation;
		
		while(!fKeyOK) {
			try {
				if(departureStation == null) { 
					System.out.println("Please enter the departure station: ");
					s.nextLine();
					departureStation = s.nextLine();
				}
				
				System.out.println("Please enter a departure time (in format HH:MM)");
				departureTime = LocalTime.parse(s.nextLine());
			
				System.out.println("Please enter the destination station: ");
				destinationStation = s.nextLine();
				uiManager.searchRide(departureStation, departureTime, destinationStation);
				System.out.println("We found " + uiManager.getNumOfResult() + " rides that matching your search..");
				
				for(int i = 0 ; i < uiManager.getNumOfResult() ; i++)
					System.out.println("\nRide #" + (i+1) + ":\n" + uiManager.getSearchResult()[i].toString());
				fKeyOK = true;
			}
			catch(DateTimeParseException e) {
				System.out.println("The time should be in the format HH: MM");
			}
		}
		
	}//end_getSearchDetails

	private static void getRideFromUser(Managementable uiManager) throws OutOfBoundsException, YesOrNoException {
		String departureStation = null ,destinationStation = null;
		LocalTime departureTime = null, destinationTime = null;
		char key1 = 0, key2 = 0;
		boolean continueSign;
		boolean fKeyOK = false;
		
		while(!fKeyOK) {
			try {
				do{
					continueSign = true;
					//scan departure station details
					if(departureStation == null) {
						System.out.println("Please enter departure station:");
						s.nextLine();
						departureStation = s.nextLine();
					}
					
					if(departureTime == null) {
						System.out.println("Please enter a departure time (in format HH:MM)");
						departureTime = LocalTime.parse(s.nextLine());
						}
					
					//scan destination station details
					if(destinationStation == null) {
						System.out.println("Please enter destination station:");
						destinationStation = s.nextLine();
					}
					if(destinationTime == null) {
						System.out.println("Please enter a destination time (in format HH:MM)");
						destinationTime = LocalTime.parse(s.nextLine());
					}				
					
					if(departureTime.isAfter(destinationTime)){		//checks if the destination station time is before departure destination time
						System.out.println("The destination time you entered is erliar then the departure time you entered\n");
						continueSign = false;	//loop start again if time is not fine
						destinationTime = null;
					}
				}while(!continueSign);
		
				if(key2 == 0) {
					System.out.println("Do you want to add an intermediate stations? <y/n>");
					uiCheck.getChoice(key1 = s.next().charAt(0));		//gets from the user if the ride has intermediate stations 

					uiManager.addRide(departureStation, departureTime, destinationStation, destinationTime);		//creates a new ride
					if(key1 == 'y')
						getStopsFromUser(uiManager);		//function that getting from the user the intermediate stations details
					}
				
				System.out.println("Do you want do add another ride? <y/n>");
				uiCheck.getChoice(key2 = s.next().charAt(0));
				
				if(key2 == 'y') {//reset all the variables to get inside the if condition again
					departureStation = null;
					destinationStation = null;
					departureTime = null;
					destinationTime = null;
					key1 = 0;
					key2 = 0;			
				}
				else
					fKeyOK = true;
			}
			catch(DateTimeParseException e) {
				System.out.println("The time should be in the format HH: MM");
				}
			catch(YesOrNoException e){
				System.out.println(e.getMessage());
				}
		}
	}//end_getRideFromUser
	
	private static void getStopsFromUser(Managementable uiManager) throws OutOfBoundsException, YesOrNoException{
		char key = 0;
		int i = 1;
		Iterator<Ride> itr = uiManager.getAllRides().iterator();
		Ride temp = null;
		String tempName = null;
		LocalTime tempTime = null;
		
		while(itr.hasNext())
			temp = itr.next(); 	//get to the lest ride the user add
		
		do {
			try {
				if(tempName == null) {
					s.nextLine();		//clean buffer
					System.out.println("Please enter intermediate station number " + i + " details\nName: ");
					tempName = s.nextLine();		// gets station name
					}
			
				if(key == 0) {
					System.out.println("Departure Time: ");
					tempTime = LocalTime.parse(s.nextLine());		//gets station departure time
			
					SystemManagement.eReason tampEnum = uiManager.checkHourInRange(tempTime, temp);	//checks if the entered time is in range
					if(tampEnum != SystemManagement.eReason.OK){
						System.out.println("The time you entered is " + tampEnum.name() + "\nPlease enter a different time");
						continue;	//skip the adding of the station to insert a new time
						}
				
					temp.addStation(tempName, tempTime, Station.eType.INTERMEDIATE);	//creates and adding a new intermediate station to the current ride  
					i++;
					}
				
				System.out.println("Do you want do add another intermediate station? <y/n>");
				uiCheck.getChoice(key = s.next().charAt(0));
				if(key == 'y') {//reset all the temporary variables to get inside the if condition again
					tempName = null;
					tempTime = null;
					key = 0;
					}
				}
				catch(YesOrNoException e) {
					System.out.println(e.getMessage());
				}
				catch(DateTimeParseException e) {
					System.out.println("The time should be in the format HH: MM");
				}
			}while(key != 'n');
		}
	}//end_getStopFromUser