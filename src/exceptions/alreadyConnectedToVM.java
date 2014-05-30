package exceptions;

public class alreadyConnectedToVM extends Exception {

	public alreadyConnectedToVM(String port) {
		super("Already connected to VM at this port: " + port);
	}

}
