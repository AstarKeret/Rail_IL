package rail_il;

import java.time.LocalTime;

public class Ride {

	private Station[] allStations;
	private int numOfStations;
	private Station destinationStation, departureStation;
	
	public Ride(Station departureStation, Station destinationStation, int numOfStops){
		setDepartureStation(departureStation);		//set the departure station of the ride
		setDestinationStation(destinationStation);			//set the destination station of the ride
		
		allStations = new Station[numOfStops + 2];		//set the array to the size of the number of stops plus the destination & departure stations
		numOfStations = 0;
		
		addStation(departureStation.getName(), departureStation.getTime(), departureStation.getStationType());			//add the departure station to the station array
		addStation(destinationStation.getName(), destinationStation.getTime(), destinationStation.getStationType());	//add the destination station to the station array
		
	}
	
	public Station[] getAllStations() {
		return allStations;
	}
	
	public void addStation(String name, LocalTime time, Station.eType stationType){
		
		allStations[numOfStations++] = new Station(name, time, stationType);
		
	}
	
	public int getNumOfStations() {
		return numOfStations;
	}
	
	public Station getDestinationStation() {
		return destinationStation;
	}
	
	public void setDestinationStation(Station destinationStation) {
		this.destinationStation = destinationStation;
	}
	
	public Station getDepartureStation() {
		return departureStation;
	}
	
	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}
	
	public void sortByHour() {
		if(numOfStations ==  2)
			return;
		
		for(int i = 1 ; i < numOfStations ; i++)
			for(int j = i ; j > 0 && allStations[j-1].getTime().isAfter(allStations[j].getTime()) ; j--) {//loop ends when all the hours will be sort and when j get to the beginning of the array
				Station tamp = allStations[j];

				allStations[j] = allStations[j-1];
				allStations[j-1] = tamp;
			}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("Ride Details:\n");
		sb.append("Departure Station" + departureStation.toString());
		for(int i = 1 ; i < numOfStations - 1 ; i++)
			sb.append("\nstop #" + i + "\t\t" + allStations[i].toString());
		sb.append("\nDestination Station" + destinationStation.toString() + "-->(Arrival Time)");
		return sb.toString();
	}
}