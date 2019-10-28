import java.io.*; 
import java.rmi.*;

public class Client{
   public static void main(String argv[]) {
      try {
		  ServerInterface si = (ServerInterface) Naming.lookup("Server");
		  String response = si.echo("hello");
		  System.out.println(response);
      } catch(Exception e) {
         System.err.println("Server exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}