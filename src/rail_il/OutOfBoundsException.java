package rail_il;

@SuppressWarnings("serial")
public class OutOfBoundsException extends RideException {

	public OutOfBoundsException(int choice) {
		super("Choice " + choice + " does not exist");
	}
	
	public OutOfBoundsException(String msg) {
		super(msg);
	}
	
}
