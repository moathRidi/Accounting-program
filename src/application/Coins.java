package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public abstract class Coins {
	
	public static double[] readFromWeb() throws Exception{
		File f = new File("test.txt");
		 URL oracle = new URL("https://www.tnb.ps/ar/currency");
		 double[] co = new double[3];
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));
	        
	        String[] s=new String[0];
	        String ok="";
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	        	s = inputLine.split("span");
	        	
	        in.close();
	        for(int i=0 ; i<s.length ; i++) {
	        	ok+=s[i].replaceAll("[<>/]", "")+"\n";
	        	
	        }
	        
	       try(PrintWriter p = new PrintWriter(f)){
	    	   p.print(ok);
	       }
	       Scanner inFile = new Scanner(f);
	       double cc;
	      
	       int counter=0;
	       while(inFile.hasNextLine()) {      
	    	   try{
	    		   cc = Double.parseDouble(inFile.nextLine());
	    		  cc= Math.round((cc*100.0))/100.0;
	    		   if(counter == 1) {
	    			   co[0] = cc;
	    		   }
	    		   else if(counter == 3) {
	    			   co[1] = cc;
	    		   }
	    		   else if(counter == 9) {
	    			   co[2] = cc;
	    		   }
	    		   counter++;
	    	   }
	    	   catch(Exception e) {
	    		   
	    	   }
	       }
	       
	       return co;
	}
}
