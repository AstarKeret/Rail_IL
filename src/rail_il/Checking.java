package rail_il;

import java.util.Set;

public class Checking implements Checkingable{

	@Override
	public boolean getChoice(int choice, int max) throws OutOfBoundsException {
		if(choice != 9 && (choice < 1 || choice > max)) 
			throw new OutOfBoundsException(choice);
		return true;
	}
	
	@Override
	public boolean getChoice(char choice) throws OutOfBoundsException, YesOrNoException {
		if(choice != 'n' && choice != 'y') 
			throw new YesOrNoException();
		return true;
	}
	
	@Override
	public void checkRideIfEmpty(Set<Ride> allRides) throws EmptyException {
		if(allRides.isEmpty())
			throw new EmptyException();
	}

}