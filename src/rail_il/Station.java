package rail_il;

import java.io.Serializable;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class Station implements Comparable<Station>, Serializable{

	public static enum eType{DESTINATION, DEPARTURE, INTERMEDIATE}
	
	private String name;
	private eType stationType;
	private LocalTime time;
	
	public Station(String name, LocalTime time, eType stationType){
		setName(name);
		setStationType(stationType);
		setTime(time);
	}
	
	public Station() {
		this(null, null, null);
	}
	
	public Station(Station theStation) {
		this(theStation.name, theStation.time, theStation.stationType);
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

	@Override
	public int compareTo(Station s) {
		return time.compareTo(s.getTime());
	}
	
	@Override
	public String toString() {
		return "\tStation Name: " + name +  "\t\tTime: " + time + "\tStation Type: " + stationType ;
	}
}
