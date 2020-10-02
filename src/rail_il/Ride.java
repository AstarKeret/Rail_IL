package rail_il;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class Ride implements Serializable{

	private Set<Station> allStations;
	private Station destinationStation, departureStation;
	
	public Ride(Station departureStation, Station destinationStation){
		setDepartureStation(departureStation);		//set the departure station of the ride
		setDestinationStation(destinationStation);			//set the destination station of the ride
		
		allStations = new TreeSet<Station>(new CompareStationByTime());	
		
		addStation(departureStation.getName(), departureStation.getTime(), departureStation.getStationType());			//add the departure station to the station array
		addStation(destinationStation.getName(), destinationStation.getTime(), destinationStation.getStationType());	//add the destination station to the station array
		
	}
	
	public Set<Station> getAllStations() {
		return allStations;
	}
	
	public void addStation(String name, LocalTime time, Station.eType stationType){
		
		allStations.add(new Station(name, time, stationType));
	}
	
	public void addStation(Station newStation) {
		allStations.add(new Station(newStation));
		if(newStation.getStationType() == Station.eType.DEPARTURE)		//checks to set departure station
			setDepartureStation(newStation);
		
		if(newStation.getStationType() == Station.eType.DESTINATION)	//checks to set destination station
			setDestinationStation(newStation);
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

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer("Ride Details:\n");
		Station temp;
		sb.append("Departure Station" + departureStation.toString());
		Iterator<Station> itr = allStations.iterator();
		
		itr.next();	//skip on the departure station in the set 
		for(int i = 1 ; i < allStations.size() - 1 ; i++) {
			temp = itr.next();
			sb.append("\nstop #" + i + "\t\t" + temp.toString());
			}
		sb.append("\nDestination Station" + destinationStation.toString() + "-->(Arrival Time)");
		return sb.toString();
	}
}