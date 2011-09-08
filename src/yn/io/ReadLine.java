package yn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadLine {
	 public static void main(String[] args) throws IOException{
	      String curLine = ""; // Line read from standard in
	      
	      System.out.println("Enter a line of text (type 'quit' to exit): ");
	      InputStreamReader converter = new InputStreamReader(System.in);
	      BufferedReader in = new BufferedReader(converter);

	      
	      while (!(curLine.equals("quit"))){
	          curLine = in.readLine();
	          
	          if (!(curLine.equals("quit"))){
	              System.out.println("You typed: " + curLine);
	          }
	      }
	  }
}
