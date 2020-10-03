package rail_il;

import java.time.LocalTime;
import java.util.Set;

import rail_il.SystemManagement.eReason;

public class ConsoleUI implements Checkingable, Managementable {

	private SystemManagement manager;
	private Checking check;
	
	public ConsoleUI() {
		manager = new SystemManagement();
		check = new Checking();
	}
	
	@Override
	public boolean getChoice(int choice, int max) throws OutOfBoundsException {
		return check.getChoice(choice, max);
	}

	@Override
	public boolean getChoice(char choice) throws OutOfBoundsException, YesOrNoException {
		return check.getChoice(choice);
	}

	@Override
	public void checkRideIfEmpty(Set<Ride> allRides) throws EmptyException {
		check.checkRideIfEmpty(allRides);
	}

	@Override
	public void setAllRides(Set<Ride> allRides) {
		manager.setAllRides(allRides);
	}

	@Override
	public void addRide(String departureStation, LocalTime departureTime, String destinationStation,
			LocalTime destinationTime) {
		manager.addRide(departureStation, departureTime, destinationStation, destinationTime);
	}

	@Override
	public Set<Ride> getAllRides() {
		return manager.getAllRides();
	}

	@Override
	public Ride[] getSearchResult() {
		return manager.getSearchResult();
	}

	@Override
	public void addSearchResult(Station departure, Station destination, Ride theRide, int numOfStations) {
		manager.addSearchResult(departure, destination, theRide, numOfStations);
	}

	@Override
	public int getNumOfResult() {
		return manager.getNumOfResult();
	}

	@Override
	public void searchRide(String departureStation, LocalTime departureTime, String destinationStation) {
		manager.searchRide(departureStation, departureTime, destinationStation);
	}
	
	@Override
	public String toString() {
		return manager.toString();
	}

	@Override
	public eReason checkHourInRange(LocalTime tempTime, Ride temp) {
		return manager.checkHourInRange(tempTime, temp);
	}
}
