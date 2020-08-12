package rail_il;

import java.time.LocalTime;

public class Ride {

	private String destinationStation, departureStation;
	private LocalTime destinationTime, departureTime;
	
	
	public String getDestinatioStation() {
		return destinationStation;
	}
	
	public void setDestinatioStation(String destinatioStation) {
		this.destinationStation = destinatioStation;
	}
	public String getDepartureStation() {
		return departureStation;
	}
	
	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}
	public LocalTime getDestinationTime() {
		return destinationTime;
	}
	
	public void setDestinationTime(LocalTime destinationTime) {
		this.destinationTime = destinationTime;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}


	public String toString() {
		return "Departure Station: " + departureStation + "\tLeaving Time: "
	+ departureTime + "\nDestination Station: " + destinationStation +
	"\tArrival Time: " + destinationTime;
	}	
}