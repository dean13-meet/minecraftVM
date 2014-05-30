package exceptions;

public class alreadyConnectedToThisVM extends Exception {

	public alreadyConnectedToThisVM(String port) {
		super("Already connected to VM at this port: " + port);
	}

}
