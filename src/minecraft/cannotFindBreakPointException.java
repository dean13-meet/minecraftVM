package minecraft;

public class cannotFindBreakPointException extends Exception {

	public cannotFindBreakPointException(String port, String threadName,
			String methodName, String variableName) {
		super("Could not find variable to create breakpoint: " + variableName + " in: (port, thread, method)" + port + threadName + methodName);
	}

	
}
