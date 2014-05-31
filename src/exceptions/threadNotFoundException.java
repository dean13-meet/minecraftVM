package exceptions;

public class threadNotFoundException extends Exception {

	public threadNotFoundException(String threadName) {
		super("Could not find thread: " + threadName + " -- timedOut");
	}


	
}
