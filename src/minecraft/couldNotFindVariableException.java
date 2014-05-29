package minecraft;

public class couldNotFindVariableException extends Exception {

	public couldNotFindVariableException(String port, String threadName,
			String methodName, String variableName) {
		super("Could not find variable: " + variableName + " in: (port, thread, method)" + port + threadName + methodName);
	}

}
