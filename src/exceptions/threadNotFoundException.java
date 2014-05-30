package exceptions;

public class threadNotFoundException extends Exception {

	public threadNotFoundException(String port, String threadName) {
		super("In port: " + port + ", could not find thread: " + threadName + " -- timedOut");
	}

	
}
