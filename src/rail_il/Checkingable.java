package rail_il;

import java.util.Set;

public interface Checkingable {

	boolean getChoice(int choice, int max) throws OutOfBoundsException;

	boolean getChoice(char choice) throws OutOfBoundsException, YesOrNoException;

	void checkRideIfEmpty(Set<Ride> allRides) throws EmptyException;	
}
