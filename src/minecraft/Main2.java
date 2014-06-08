package minecraft;

import java.io.*;
import java.nio.charset.Charset;

import net.minecraft.bootstrap.Bootstrap;

public class Main2 {
	public String nameSet = "HEY DEANNNN";

	
	public static void main(final String[] args) throws FileNotFoundException{
		
		try {
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")),true));
			System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")),true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread connector = new Thread(new Runnable(){

			public void run() {
				doNothing1();
				
			}
			
			private void doNothing1(){
				while(true){doNothing2();}};
			private void doNothing2(){try {
				Thread.sleep(10);
				//System.out.println("WAITING FOR BREAKPOINT");
				//Above creates a huge print stream :P
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}};
		});
		connector.setName("connector");
		connector.start();
		System.out.println("RUNNING MAIN2");
		Thread t = new Thread(new Runnable(){

			public void run() {
				try {
					Bootstrap.main(args);
					int n = 999;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}

		});


		t.setName(Main.name);

		 
		t.start();
		
		
		
	}
	
}
