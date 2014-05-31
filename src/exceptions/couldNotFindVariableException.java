package exceptions;

public class couldNotFindVariableException extends Exception {

	public couldNotFindVariableException(String threadName, String variableName) {
		super("Could not find variable: " + variableName + " in: (thread)" + threadName);
	}
}
