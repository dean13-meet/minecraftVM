package minecraft;

import com.sun.jdi.IncompatibleThreadStateException;

import exceptions.breakPointNotHitException;

//TODO If you have time, (after doing all other TODOs) change buttons to add inanimates/balls to drop down menus.
public abstract class ButtonCommands {
	Display d;
	ButtonCommands(Display d) {
		this.d = d;
	}

	abstract void execute(int caseNum);
}

class refreshThreadCommand extends ButtonCommands{

	initialDisplay newD;
	refreshThreadCommand(initialDisplay d) {
		super(d);
		this.newD = (initialDisplay)d;
	}

	@Override
	void execute(int caseNum) {
		
			newD.refreshThreads();
			System.out.println("Refereshed Threads");
		
		
	}
	
}