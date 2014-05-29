package minecraft;

import java.io.*;
import java.nio.charset.Charset;

import net.minecraft.bootstrap.Bootstrap;

public class Main2 {
	public String nameSet = "HEY DEANNNN";

	
	public static void main(final String[] args) throws FileNotFoundException{
		
		
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

		 System.setOut(new PrintStream(new OutputStream() {
             public void write(int b) {
                 //DO NOTHING
             }
         }));
		t.start();
	}
}
