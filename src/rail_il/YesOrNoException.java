package rail_il;

@SuppressWarnings("serial")
public class YesOrNoException extends RideException {
	
	public YesOrNoException() {
		super("You must enter y or n only!");
	}
}