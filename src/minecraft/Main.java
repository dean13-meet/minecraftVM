package minecraft;

import java.io.FileNotFoundException;
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

import exceptions.alreadyConnectedToVM;
import exceptions.breakPointNotHitException;
import exceptions.cannotFindBreakPointException;
import exceptions.couldNotFindVariableException;
import exceptions.threadNotFoundException;

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
		} finally{
			Thread.sleep(1000);

		}
	}

	public static void main22(final String[] args) throws IOException, IllegalConnectorArgumentsException, IncompatibleThreadStateException, InterruptedException{



		System.out.println("RUNNING MAIN1");
		try {
			connectToMC.createConnection(args[0]);
		} catch (alreadyConnectedToVM e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new GUI();

		/*
		ArrayList<String> argos = new ArrayList<String>();
		argos.add("java.lang.String");
		ArrayList<Value> params = new ArrayList<Value>();
		params.add(connectToMC.mirrorOf(args[0], "HHHEYYYEYYEY"));
		try {
			connectToMC.invokeMethod(args[0], name, "net.minecraft.bootstrap.Bootstrap:381", "frame", "setTitle", argos, params);
		} catch (threadNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (cannotFindBreakPointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (breakPointNotHitException e) {
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
		} catch (couldNotFindVariableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("PRINT: " + connectToMC.getValueOfField(args[0], name, "net.minecraft.bootstrap.Bootstrap:381", "frame", "EXIT_ON_CLOSE"));


		 */
	}
}