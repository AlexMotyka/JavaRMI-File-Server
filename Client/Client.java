import java.io.*; 
import java.rmi.*;
import java.util.Scanner;

public class Client{
   public static void main(String argv[]) {
      try {
		  ServerInterface si = (ServerInterface) Naming.lookup("Server");
		  Scanner userIn = new Scanner(System.in);
		  while(true){
			  System.out.println("Enter a string: ");
			  String input = userIn.nextLine();
			  String response = si.echo(input);
			  System.out.println(response);
		  }
      } catch(Exception e) {
         System.err.println("Server exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}