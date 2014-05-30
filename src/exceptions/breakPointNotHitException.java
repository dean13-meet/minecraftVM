package exceptions;

import com.sun.jdi.Location;

public class breakPointNotHitException extends Exception {

	public breakPointNotHitException(Location breakLoc) {
		super("Break point was not hit until time out. Break point: " + breakLoc.toString());
	}

}
