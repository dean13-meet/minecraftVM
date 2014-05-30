package minecraft;

import java.io.IOException;

import net.minecraft.bootstrap.Bootstrap;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.jdi.*;
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
import com.sun.tools.*;

import java.io.*;

import javax.swing.JButton;


public class Main {

public final static String name = "Thread-0001";

public static void main(final String[] args) throws InterruptedException{
	try {
		main22(args);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalConnectorArgumentsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IncompatibleThreadStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidTypeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotLoadedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AbsentInformationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		Thread.sleep(10000);
	}
}

	public static void main22(final String[] args) throws IOException, IllegalConnectorArgumentsException, IncompatibleThreadStateException, InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InvalidTypeException, ClassNotLoadedException, InvocationException, AbsentInformationException{

		
		System.out.println("RUNNING MAIN1");
		

		VirtualMachineManager vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
		AttachingConnector atconn = null;
		for(AttachingConnector i: vmm.attachingConnectors()){
			if("dt_socket".equalsIgnoreCase(i.transport().name())){
				atconn = i;
				break;
			}
		}

		int nVariableIWants = 7;
		Object toPrint = null;
		Map<String, Argument> prm = atconn.defaultArguments();
		prm.get("port").setValue(args[0]);
		//prm.get("hostname").setValue("127.0.0.1");
		VirtualMachine vm = atconn.attach(prm);

		System.out.println("CONNECTED");
		boolean gotThread = false;
		outer: while(!gotThread){
		List<ThreadReference> threads = vm.allThreads();
		//vm.resume();
		System.out.println("VM: " + vm.name());
		Thread.sleep(300);
		
		for(ThreadReference ii: threads){
			System.out.println("HAHAHAHA: " + ii.name() + " mmmm " + name);
			Thread.sleep(100);
			if(ii.name().equals(name)){
				ii.suspend();
				gotThread = true;
				System.out.println("SUSPENDED: " + ii.isSuspended());
				List<StackFrame> frames = ii.frames();
				Location n = null;
				for(int i = 0 ; i < frames.size(); i++){
					System.out.println("FRAME: " + i + " " + frames.get(i));
					if(frames.get(i).toString().contains(".")&&frames.get(i).location().codeIndex()!=-1){
						n = frames.get(i).location();
						break;
					}
				}
				
				/*
				n = 173;
				List<ReferenceType> refTypes = ii.allClasses();
				Location breakpointLocation = null;
				for (ReferenceType refType: refTypes)
				{
					if(refType.genericSignature()!=null)System.out.println("GEN SIG: " + refType.genericSignature());
					if(refType.equals(net.minecraft.bootstrap.Bootstrap.class)){
						if (breakpointLocation != null)
						{
							break;
						}
						List<Location> locs = refType.allLineLocations();
						for (Location loc: locs)
						{
							if (loc.lineNumber() == n)
							{
								breakpointLocation = loc;
								System.out.println("BREAKPOINT LOCATION: " + loc);
								break;
							}
						}
					}
				}
				*/

				EventRequestManager evReqMan = vm.eventRequestManager();
				BreakpointRequest bpReq = evReqMan.createBreakpointRequest(n);

				System.out.println("Set break at:" + n);

				bpReq.enable();
				ii.resume();

				System.out.println(1);
				EventQueue evtQueue = vm.eventQueue();
				boolean secondCatch = false;
				while(!secondCatch)
				{
					System.out.println(1111111);
					EventSet evtSet = evtQueue.remove();
					EventIterator evtIter = evtSet.eventIterator();
					System.out.println(11111);
					while (evtIter.hasNext()&&!secondCatch)
					{
						try
						{System.out.println(11);
							Event evt = evtIter.next();
							EventRequest evtReq = evt.request();
							if (evtReq instanceof BreakpointRequest)
							{System.out.println(111);
								BreakpointRequest bpReq1 = (BreakpointRequest)evtReq;
								if (bpReq1.location().equals(n))
								{
									System.out.println("Breakpoint at line " + n + ": ");
									BreakpointEvent brEvt = (BreakpointEvent)evt;
									ii = brEvt.thread();
									secondCatch = true;
									List<StackFrame> framess = ii.frames();
									for(StackFrame f : framess){
										System.out.println(2 + " " + f.toString());
										try {
											List<LocalVariable> ll = f.visibleVariables();
											for(LocalVariable l : ll){
												System.out.println(3 + " " + l.name());

												if(l.name().equals("frame")){
													System.out.println(4);
													Class<?>[] arr = {java.lang.String.class};
													List<Value> obb = new ArrayList<Value>();
													


													ObjectReference localVar = (ObjectReference) f.getValue(l);
													//ObjectReference objectRef = localVar.referenceType().
													//obb.add((new JButton("HEY").getClass()));
													obb.add(vm.mirrorOf("HEY MR. KUZSMAUL!"));
													System.out.println(localVar + " " + ii);
													localVar.invokeMethod(
															ii, 
															localVar.referenceType().
															methodsByName("setTitle").get(0), 
															obb,
															0);
													
													toPrint = localVar.invokeMethod(ii, localVar.referenceType().methodsByName("getTitle").get(0), new ArrayList<Value>(), 0);
													//toPrint = "hi";
													//toPrint = toPrint.getClass();
													//toPrint = f.getValue(l).getClass().getDeclaredField("detectedSpecs");
													//toPrint = l.getValue(f.getValue(l).getClass().getDeclaredMethod(, paramArrayOfClass));
													break outer;
												}
											}
										} catch (AbsentInformationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SecurityException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}

								}
							}
						}



						finally
						{
							evtSet.resume();
							System.out.println("TOPRINT: " + toPrint);
						}
					}
				}


			}
			
		}
		
		}
	}}