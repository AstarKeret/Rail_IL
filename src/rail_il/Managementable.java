package rail_il;

import java.time.LocalTime;
import java.util.Set;

import rail_il.SystemManagement.eReason;

public interface Managementable {
	void setAllRides(Set<Ride> allRides);
	
	void addRide(String departureStation, LocalTime departureTime, String destinationStation, LocalTime destinationTime);

	Set<Ride> getAllRides();
	
	Ride[] getSearchResult();
	
	void addSearchResult(Station departure, Station destination, Ride theRide, int numOfStations);
	
	int getNumOfResult();
	
	void searchRide(String departureStation, LocalTime departureTime, String destinationStation);
	
	String toString();

	eReason checkHourInRange(LocalTime tempTime, Ride temp);
}
