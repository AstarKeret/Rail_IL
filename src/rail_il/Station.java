package rail_il;

import java.time.LocalTime;

public class Station {

	public static enum eType{DESTINATION, DEPARTURE, INTERMEDIATE}
	
	private String name;
	private eType stationType;
	private LocalTime time;
	
	public Station(String name, LocalTime time, eType stationType){
		setName(name);
		setStationType(stationType);
		setTime(time);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public eType getStationType() {
		return stationType;
	}
	
	public void setStationType(eType stationType) {
		this.stationType = stationType;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public String toString() {
		return "\tStation Name: " + name + "\tStation Type: " + stationType + "\t\tTime: "
	+ time;
	}	
	
}
