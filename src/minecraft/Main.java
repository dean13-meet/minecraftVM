package minecraft;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import net.minecraft.bootstrap.Bootstrap;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
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
/*
		try {
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("outputMain.txt")),true));
			System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream("outputMain.txt")),true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/		
	
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

		
		



		File ff = new File("/Users/Dean_Leitersdorf/Documents/workspace/minecraft/bin");//This directory
		File f = new File("/Users/Dean_Leitersdorf/Documents/workspace/minecraft/bin/");
		//String[] args1 = {OperatingSystem.getCurrentPlatform().getJavaDir(),"-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000","-Xdock:icon=/Users/Dean_Leitersdorf/Library/Application Support/minecraft/assets/objects/99/991b421dfd401f115241601b2b373140a8d78572", "-Xdock:name=Minecraft", "-Xmx1G", "-Djava.library.path=/Users/Dean_Leitersdorf/Library/Application Support/minecraft/versions/1.7.9/1.7.9-natives-1402530752422390000", "-cp", "/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/java3d/vecmath/1.3.1/vecmath-1.3.1.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/net/sf/trove4j/trove4j/3.0.3/trove4j-3.0.3.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/net/sf/jopt-simple/jopt-simple/4.5/jopt-simple-4.5.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/io/netty/netty-all/4.0.10.Final/netty-all-4.0.10.Final.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/google/guava/guava/15.0/guava-15.0.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/commons-io/commons-io/2.4/commons-io-2.4.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/commons-codec/commons-codec/1.9/commons-codec-1.9.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/com/mojang/authlib/1.5.13/authlib-1.5.13.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/org/apache/logging/log4j/log4j-api/2.0-beta9/log4j-api-2.0-beta9.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/org/apache/logging/log4j/log4j-core/2.0-beta9/log4j-core-2.0-beta9.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.1/lwjgl_util-2.9.1.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/libraries/tv/twitch/twitch/5.16/twitch-5.16.jar:/Users/Dean_Leitersdorf/Library/Application Support/minecraft/versions/1.7.9/1.7.9.jar", "net.minecraft.client.main.Main", "--username", "exitforlife", "--version", "1.7.9", "--gameDir", "/Users/Dean_Leitersdorf/Library/Application Support/minecraft", "--assetsDir", "/Users/Dean_Leitersdorf/Library/Application Support/minecraft/assets", "--assetIndex", "1.7.4", "--uuid", "9e58a3151ca949559045c21ff448d35b", "--accessToken", "2a697c2b435849c6838fc9c4ef6e1909", "--userProperties", "{}", "--userType", "mojang"};
		String classpath = System.getProperty("java.class.path");
		String[] args1 = {OperatingSystem.getCurrentPlatform().getJavaDir(),"-cp",classpath,"-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000", "minecraft.Main2"};
		System.out.println(classpath);
		//ProcessBuilder builder = new ProcessBuilder(new String[] {OperatingSystem.getCurrentPlatform().getJavaDir(), "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000","-Djava.library.path="+f.getAbsolutePath() + "/minecraft/Main2.class","-cp"});
		ProcessBuilder builder = new ProcessBuilder(args1);
		builder.directory(ff);
		Process process = builder.start();
		Thread.sleep(10000);
		System.out.println("RESUMING");
		try {
			connectToMC.createConnection(args[0]);
			//Object o = connectToMC.getVM().classesByName("net.minecraft.launcher.Launcher").get(0).fieldByName("profileManager").declaringType().signature();//fieldByName("gameRunner");//.declaringType().fieldByName("processFactory").declaringType().methodsByName("startGame").get(0).location();
			Iterator<ReferenceType> it = connectToMC.getVM().allClasses().iterator();
			ReferenceType o = null;
			while(it.hasNext()){
				ReferenceType n = it.next();
				//System.out.println("A CLASS: " + n.name());
				if(n.name().contains("DirectGameProcessFactory")){
					o = n;
					break;
				}
			}
			/*
		ObjectReference ref = (ObjectReference) connectToMC.getValueOfFieldOfAnyObjectReference(((ObjectReference)connectToMC.getValueOfFieldOfAnyObjectReference(((ObjectReference)connectToMC.getValueOfFieldOfAnyObjectReference(((ObjectReference)connectToMC.getValueOfLocalVarInAnyThread(args[0], "minecraftLauncher")), "launcher")), "gameRunner")),"processFactory");
		System.out.println("OBJECT LOOKING FOR: " + ref);
		*/
		Location loc = o.methodsByName("startGame").get(0).location();
		EventSet evtSet = null;
		try {
			evtSet = connectToMC.waitUntilBreakPointIsReached(connectToMC.breakPoint(loc));
			ThreadReference thread = null;
			EventIterator evtIter = evtSet.eventIterator();
			while(evtIter.hasNext()){
				Event evt = evtIter.next();
				EventRequest evtReq = evt.request();
				if (evtReq instanceof BreakpointRequest)
				{
					BreakpointRequest bpReq1 = (BreakpointRequest)evtReq;
					if (bpReq1.location().equals(loc))
					{
						BreakpointEvent brEvt = (BreakpointEvent)evt;
						thread = brEvt.thread();
						System.out.println("HIT BREAKPT SECOND TIME");
					}
				}
			}
			
			try {
				ObjectReference obj = (ObjectReference) connectToMC.getValueOfLocalVar(getPort(), thread.name(), "processBuilder", false);
				ReferenceType refType = obj.referenceType();
				
				List<Value> vals = new ArrayList<Value>();
				System.out.println("GOT::: " + obj.invokeMethod(thread, refType.methodsByName("toString").get(0), vals, 0));
				ObjectReference argsOfLauncher = ((ObjectReference)obj.getValue(refType.fieldByName("arguments")));
				ReferenceType refOfArgsOfLauncher = argsOfLauncher.referenceType();
				System.out.println("GOT THE FOLLOWING: " + argsOfLauncher + " " + refOfArgsOfLauncher);
				for(Method m : refOfArgsOfLauncher.allMethods()){
					System.out.println("METHOD: " + m.name() + " " + m.signature());
				}
				Method normalAddMethod = refOfArgsOfLauncher.methodsByName("add", "(Ljava/lang/Object;)Z").get(0);
				Method overloadedAddMethod = refOfArgsOfLauncher.methodsByName("addAll", "(ILjava/util/Collection;)Z").get(0);
				List<Value> vals2 = new ArrayList<Value>();
				ObjectReference v = ((ClassType) argsOfLauncher.type()).newInstance(thread, refOfArgsOfLauncher.methodsByName("<init>", "()V").get(0), vals, 0);
				List<Value> subVals1 = new ArrayList<Value>();
				subVals1.add(connectToMC.getVM().mirrorOf("-Xdebug"));
				v.invokeMethod(thread, normalAddMethod, subVals1, 0);
				List<Value> subVals2 = new ArrayList<Value>();
				subVals2.add(connectToMC.getVM().mirrorOf("-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address="+args[1]));
				v.invokeMethod(thread, normalAddMethod, subVals2, 0);
				vals2.add(connectToMC.getVM().mirrorOf(1));
				vals2.add(v);
				argsOfLauncher.invokeMethod(thread, overloadedAddMethod, vals2, 0);
				//System.out.println("GOT::: " + obj.getValue(refType.fieldByName("processPath")) + " " + obj.getValue(refType.fieldByName("arguments")) + " " + obj.getValue(refType.fieldByName("sysOutFilter"))+ " " + obj.getValue(refType.fieldByName("directory")));
			} catch (threadNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AbsentInformationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (cannotFindBreakPointException e) {
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
			}
		} catch (breakPointNotHitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(evtSet!=null)evtSet.resume();
		}
		} catch (alreadyConnectedToVM e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		/*
		System.out.println(builder.directory());
		process.waitFor();
		System.out.println(process.exitValue());
		*/
/*
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
		}
		*/
		connectToMC.releaseConnection();
		Main.port = args[1];
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