package rail_il;

import java.time.LocalTime;

public class SystemManagement {
	public static final int MAX_RIDES = 20;
	private Ride[] allRides;
	private int numOfRides;
	
	public SystemManagement() {
		allRides = new Ride[MAX_RIDES];
		numOfRides = 0;
		}

	public void addRide(String departureStation, String departureTime, String destinationStation, String destinationTime) {
		allRides[numOfRides] = new Ride();

		allRides[numOfRides].setDepartureStation(departureStation);
		allRides[numOfRides].setDepartureTime(LocalTime.parse(departureTime));
		allRides[numOfRides].setDestinatioStation(destinationStation);;
		allRides[numOfRides++].setDestinationTime(LocalTime.parse(destinationTime));
		}

	public Ride[] getAllRides() {
		return allRides;
		}

	public int getNumOfRides() {
		return numOfRides;
	}

	public void sortByHour() {
		if(numOfRides ==  1)
			return;
		for(int i = 1 ; i < numOfRides ; i++)
			for(int j = i ; j > 0 && allRides[j-1].getDepartureTime().isAfter(allRides[j].getDepartureTime()) ; j--) {
				Ride tamp = allRides[j];

				allRides[j] = allRides[j-1];
				allRides[j-1] = tamp;
			}
	}
}
