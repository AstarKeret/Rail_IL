package rail_il;

import java.time.LocalTime;

import rail_il.Station.eType;

public class SystemManagement {
	public static enum eReason{BEFORE_DEPARTURE_TIME, AFTER_DESTINATION_TIME, SAME_TIME_AS_DESTINATION, SAME_TIME_AS_DEPARTURE, SAME_TIME_AS_ANOTHER_INTERMEDIATE,  OK};
	public static final int MAX_RIDES = 20;
	private Ride[] allRides;
	private int numOfRides;
	
	public SystemManagement() {
		allRides = new Ride[MAX_RIDES];
		numOfRides = 0;
		}

	public void addRide(String departureStation, String departureTime, String destinationStation, String destinationTime, int numOfStations) {
		Station departure = new Station(departureStation, LocalTime.parse(departureTime), eType.DEPARTURE);				//creates a new station--> departure
		Station destination = new Station(destinationStation, LocalTime.parse(destinationTime), eType.DESTINATION);		//creates a new station--> destination
		
		allRides[numOfRides++] = new Ride(departure, destination, numOfStations);		//creates & adding a new ride

		}

	public Ride[] getAllRides() {
		return allRides;
		}

	public int getNumOfRides() {
		return numOfRides;
	}

	public void sortByHour() {
		if(numOfRides ==  1){
			allRides[0].sortByHour();		//sort only the intermediate station in the one ride
			
			return;
		}
		
		for(int i = 1 ; i < numOfRides ; i++)
			for(int j = i ; j > 0 && allRides[j-1].getDepartureStation().getTime().isAfter(allRides[j].getDepartureStation().getTime()) ; j--) {//loop ends when all the hours will be sort and when j get to the beginning of the array
				Ride tamp = allRides[j];

				allRides[j] = allRides[j-1];
				allRides[j-1] = tamp;
			}
		for(int i = 0 ; i < numOfRides ; i++)
			allRides[i].sortByHour();		//sort all the intermediate station in all the rides
	}
	
	public eReason checkHourInRange(LocalTime theTime, LocalTime timeRange, eType typeToCheck){
		
		if(typeToCheck.equals(eType.DEPARTURE)){		//only departure station..
			if(timeRange.equals(theTime))				//checks if the sent time is before the departure station time
				return eReason.SAME_TIME_AS_DEPARTURE;
			if(timeRange.isAfter(theTime))	
				return eReason.BEFORE_DEPARTURE_TIME;
		}
		
		if(typeToCheck.equals(eType.DESTINATION)){
			if(timeRange.equals(theTime))	
				return eReason.SAME_TIME_AS_DESTINATION;
			if(timeRange.isBefore(theTime))
				return eReason.AFTER_DESTINATION_TIME;
		}
		
		if(typeToCheck.equals(eType.INTERMEDIATE))
			if(timeRange.equals(theTime))	
				return eReason.SAME_TIME_AS_ANOTHER_INTERMEDIATE;
			
		return eReason.OK;
	}

	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
	
		if(numOfRides == 0){
			sb.append("There is no rides to show");
			return sb.toString();
		}
		
		for (int i = 0; i < numOfRides ; i++)
			sb.append("\n\nRide #" + (i+1) + "\n" + allRides[i].toString());
		
		return sb.toString();
	}
}
