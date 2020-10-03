package rail_il;

@SuppressWarnings("serial")
public class EmptyException extends RideException {

	public EmptyException() {
		super("There is no rides to print");
	}
}
