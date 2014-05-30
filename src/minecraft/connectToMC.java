package minecraft;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.ClassNotLoadedException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.InvocationException;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.Method;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.StringReference;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Value;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector.Argument;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;

import exceptions.alreadyConnectedToThisVM;
import exceptions.breakPointNotHitException;
import exceptions.cannotFindBreakPointException;
import exceptions.couldNotFindVariableException;
import exceptions.threadNotFoundException;

public class connectToMC {
	private static VirtualMachine vm;

	public synchronized static Value invokeMethod(String port, String threadName, String methodName, String variableName, String methodToInvoke, List<String> arguments, List<Value> params) throws IOException, IllegalConnectorArgumentsException, threadNotFoundException, IncompatibleThreadStateException, cannotFindBreakPointException, InterruptedException, breakPointNotHitException, InvalidTypeException, ClassNotLoadedException, InvocationException, couldNotFindVariableException{
		try {
			createConnection(port);
		} catch (alreadyConnectedToThisVM e) {
			//Do nothin as this is ok
		}
		List<ThreadReference> threads = null;
		long startTime = System.currentTimeMillis();
		long timeOut = 4000;
		ThreadReference thread = null;
		outer: for(long time = 0; time<timeOut; time = System.currentTimeMillis()-startTime){
			threads = vm.allThreads();
			for(ThreadReference ii : threads){
				if(ii.name().equalsIgnoreCase(threadName)){
					thread = ii;
					break outer;
				}
			}
		}
		if(thread==null){
			vm.dispose();
			vm=null;
			throw new threadNotFoundException(port, threadName);
		}

		thread.suspend();
		List<StackFrame> frames = thread.frames();
		Location breakLoc = null;
		startTime = System.currentTimeMillis();
		timeOut = 4000;//Same as above
		outer: for(long time = 0; time<timeOut; time = System.currentTimeMillis()-startTime){
			for(int i = 0 ; i < frames.size(); i++){
				System.out.println("FRAME: " + frames.get(i));
				if(frames.get(i).location().codeIndex()!=-1){
					breakLoc = frames.get(i).location();
					break outer;
				}
			}
		}
		if(breakLoc==null){
			vm.dispose();
			vm=null;
			throw new cannotFindBreakPointException(port, threadName, methodName, variableName);
		}
		EventRequestManager evReqMan = vm.eventRequestManager();
		BreakpointRequest bpReq = evReqMan.createBreakpointRequest(breakLoc);
		bpReq.enable();
		thread.resume();
		ThreadReference thread2 = null; //Same thread just as above, just after break was hit
		EventQueue evtQueue = vm.eventQueue();
		startTime = System.currentTimeMillis();
		timeOut = 10000;
		outer: for(long time = 0; time<timeOut && thread2==null; time = System.currentTimeMillis()-startTime){
			EventSet evtSet = evtQueue.remove();
			EventIterator evtIter = evtSet.eventIterator();
			while(evtIter.hasNext()&&thread2==null){
				Event evt = evtIter.next();
				EventRequest evtReq = evt.request();
				if (evtReq instanceof BreakpointRequest)
				{
					BreakpointRequest bpReq1 = (BreakpointRequest)evtReq;
					if (bpReq1.location().equals(breakLoc))
					{
						BreakpointEvent brEvt = (BreakpointEvent)evt;
						thread2 = brEvt.thread();
						//evtSet.resume();
						break outer;
					}
				}
			}//evtSet.resume();
		}
		if(thread2==null){
			vm.dispose();
			vm=null;
			throw new breakPointNotHitException(breakLoc);
		}
		List<StackFrame> frames2 = thread2.frames();
		for(StackFrame frame : frames2){
			try {
				for(LocalVariable var : frame.visibleVariables()){
					if(var.name().equalsIgnoreCase(variableName)){
						ObjectReference ref = (ObjectReference)frame.getValue(var);
						List<Method> methods = ref.referenceType().methodsByName(methodToInvoke);
						Method method = null;
						for(Method m : methods){
							if(m.argumentTypeNames().containsAll(arguments)){
								method = m;
								break;
							}
						}
						Value retVal = ref.invokeMethod(thread2, method, params,  0);
						vm.dispose();
						vm=null;
						return retVal;
					}
				}
			} catch (AbsentInformationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		vm.dispose();
		vm=null;
		throw new couldNotFindVariableException(port, threadName, methodName, variableName);
	}
	
	public synchronized static Value getValueOfField(String port, String threadName, String methodName, String variableName, String fieldName) throws IOException, IllegalConnectorArgumentsException, threadNotFoundException, IncompatibleThreadStateException, AbsentInformationException, cannotFindBreakPointException, InterruptedException, breakPointNotHitException, InvalidTypeException, ClassNotLoadedException, InvocationException, couldNotFindVariableException{
		
		
		try {
			createConnection(port);
		} catch (alreadyConnectedToThisVM e) {
			//Do nothing because this is ok
		}
		List<ReferenceType> n = vm.allClasses();
		for(ReferenceType t : n){
			System.out.println(t);
		}
		List<String> args = new ArrayList<String>();
		args.add("java.lang.String");
		List<Value> params = new ArrayList<Value>();
		params.add(vm.mirrorOf(fieldName));
		return invokeMethod(port, threadName, methodName, variableName, "fieldByName", args, params);
	}


	private static void createConnection(String port) throws IOException, IllegalConnectorArgumentsException, alreadyConnectedToThisVM{
		VirtualMachineManager vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
		AttachingConnector atconn = null;
		for(AttachingConnector i: vmm.attachingConnectors()){
			if("dt_socket".equalsIgnoreCase(i.transport().name())){
				atconn = i;
				break;
			}
		}
		Map<String, Argument> prm = atconn.defaultArguments();
		if(vm==null || (vm!=null && !vmm.connectedVirtualMachines().contains(vm))){
		prm.get("port").setValue(port);
		vm = atconn.attach(prm);

		System.out.println("CONNECTED");}
		else{
			throw new alreadyConnectedToThisVM(port);
		}
	}
	
	public static StringReference mirrorOf(String port, String str){
		try {
			createConnection(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalConnectorArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (alreadyConnectedToThisVM e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringReference v = vm.mirrorOf(str);
		vm.dispose();vm=null;
		return v;
	}
}
