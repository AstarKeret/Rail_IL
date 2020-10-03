package rail_il;

import java.io.Serializable;
import java.util.Comparator; 

@SuppressWarnings("serial")
public class CompareStationByTime implements Comparator<Station>, Serializable{
	@Override
	public int compare(Station s1, Station s2) {
		return s1.getTime().compareTo(s2.getTime());
	}
}
