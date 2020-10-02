package rail_il;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import rail_il.Station.eType;

public class SystemManagement implements Managementable{
	public final int MAX_RESULT = 3;
	public static enum eReason{BEFORE_DEPARTURE_TIME, AFTER_DESTINATION_TIME, SAME_TIME_AS_DESTINATION, SAME_TIME_AS_DEPARTURE, SAME_TIME_AS_ANOTHER_INTERMEDIATE,  OK};
	private Set<Ride> allRides;
	private Ride[] searchResult;
	private int numOfResult;
	
	public SystemManagement() {
		allRides = new LinkedHashSet<Ride>();
		searchResult = new Ride[MAX_RESULT];
		numOfResult = 0;
		}
	
	public void setAllRides(Set<Ride> allRides) {
		this.allRides = allRides;
	}
	
	public void addRide(String departureStation, LocalTime departureTime, String destinationStation, LocalTime destinationTime) {
		Station departure = new Station(departureStation, departureTime, Station.eType.DEPARTURE);				//creates a new station--> departure
		Station destination = new Station(destinationStation, destinationTime, Station.eType.DESTINATION);		//creates a new station--> destination
		
		allRides.add(new Ride(departure, destination));		//creates & adding a new ride

		}

	public Set<Ride> getAllRides() {
		return allRides;
		}

	public Ride[] getSearchResult() {
		return searchResult;
	}
	
	public void addSearchResult(Station departure, Station destination, Ride theRide, int numOfStations) {

		searchResult[numOfResult++] = new Ride(departure, destination);		//creates & adding a new ride
		Iterator<Station> itr = theRide.getAllStations().iterator();
		itr.next();//skip on the departure station
		
		for(int i = 0 ; i < numOfStations ; i++) //copy all the stops between the found departure station to the found destination
			searchResult[numOfResult - 1].addStation(itr.next());
		
	}
	
	public int getNumOfResult() {
		return numOfResult;
	}

	public void searchRide(String departureStation, LocalTime departureTime, String destinationStation) {
		int counter = 0;
		Station departure = new Station();
		Iterator<Ride> itrRide = allRides.iterator();
		
		for(int i = 0 ; i < allRides.size() && numOfResult < MAX_RESULT ; i++){ //loop stop when it founds 3 result
			boolean bKey = false;
			Ride tempRide = itrRide.next();
			Iterator<Station> itrStation = tempRide.getAllStations().iterator();
			
			for(int j = 0 ; j < tempRide.getAllStations().size() ; j++) {
				counter++;
				Station tempStation = itrStation.next();
				if(tempStation.getName().equals(departureStation) && tempStation.getStationType() != Station.eType.DESTINATION)		//checks for the same departure station
					if(tempStation.getTime().isAfter(departureTime) || tempStation.getTime().equals(departureTime)) { 	//checks if the departure time of the station is in range	
						bKey = true; //key if found the departure station
						departure = new Station(tempStation.getName(), tempStation.getTime(), eType.DEPARTURE);	//create the search result departure station
						counter = j; //count the number of stops between the found departure station to the destination
					}
				if(bKey && tempStation.getName().equals(destinationStation)) 
					addSearchResult(departure, new Station(tempStation.getName(), tempStation.getTime(), eType.DESTINATION), tempRide, counter - 1 );
				
			}
			}
		}
	
	public eReason checkHourInRange(LocalTime theTime, Ride theRide){
		
		if(theRide.getDepartureStation().getTime().equals(theTime))				//checks if the sent time is equal to the departure station time
			return eReason.SAME_TIME_AS_DEPARTURE;
		if(theRide.getDepartureStation().getTime().isAfter(theTime))				//checks if the sent time is before the departure station time
			return eReason.BEFORE_DEPARTURE_TIME;
		
		if(theRide.getDestinationStation().getTime().equals(theTime))				//checks if the sent time is equal to the destination station time
			return eReason.SAME_TIME_AS_DESTINATION;	
		if(theRide.getDestinationStation().getTime().isBefore(theTime))			//checks if the sent time is after the destination station time
			return eReason.AFTER_DESTINATION_TIME;
		
		Iterator<Station> itr = theRide.getAllStations().iterator();
		itr.next();		//skip on the departure station
		
		for(int i = 0 ; i < theRide.getAllStations().size() - 2 ; i++ )						//loop runs only on the intermediate station in the ride
			if(itr.next().getTime().equals(theTime))				//checks if the sent time is equal to another intermediate station time
				return eReason.SAME_TIME_AS_ANOTHER_INTERMEDIATE;
			
		return eReason.OK;
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
	
		if(allRides.isEmpty()){
			sb.append("There is no rides to show");
			return sb.toString();
		}
		
		Iterator<Ride> itr = allRides.iterator();
		for (int i = 0; i < allRides.size() ; i++)
			sb.append("\n\nRide #" + (i+1) + "\n" + itr.next().toString());
		
		return sb.toString();
	}
}
