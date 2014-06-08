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

import com.mojang.launcher.OperatingSystem;
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
	private static String port;

	public static void main(final String[] args) throws InterruptedException{

		port = args[0];
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

	public static String getPort(){
		return port;
	}
	public static void main22(final String[] args) throws IOException, IllegalConnectorArgumentsException, IncompatibleThreadStateException, InterruptedException{

		System.out.println(((ObjectReference)connectToMC.getValueOfLocalVarInAnyThread(args[0], "frame")));

/*
		File f = new File("/Users/Dean_Leitersdorf/Documents/workspace/minecraft/bin/");
		ProcessBuilder builder = new ProcessBuilder(new String[] {OperatingSystem.getCurrentPlatform().getJavaDir(), f.getAbsolutePath() + "/minecraft/Main2.class","-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000"});
		Process process = builder.start();

		try {
			System.out.println("RUNNING MAIN1" + ((ArrayReference)connectToMC.getValueOfFieldOfLocalVar(args[0], name, "frame", "remainderArgs")).getValues());
		} catch (threadNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AbsentInformationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (cannotFindBreakPointException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (breakPointNotHitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotLoadedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (couldNotFindVariableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			connectToMC.createConnection(args[0]);
		} catch (alreadyConnectedToVM e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		connectToMC.releaseConnection();
		GUI g = new GUI();

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